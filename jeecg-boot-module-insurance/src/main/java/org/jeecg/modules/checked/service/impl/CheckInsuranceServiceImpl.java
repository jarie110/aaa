package org.jeecg.modules.checked.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.BeanUtils.MyBeanUtil;
import org.jeecg.common.api.vo.Result;
import org.jeecg.enumUtil.IsTransfer;
import org.jeecg.modules.checked.entity.CheckInsurance;
import org.jeecg.modules.checked.mapper.CheckInsuranceMapper;
import org.jeecg.modules.checked.service.ICheckInsuranceService;
import org.jeecg.modules.companyInsurance.entity.CompanyInsurance;
import org.jeecg.modules.companyInsurance.service.ICompanyInsuranceService;
import org.jeecg.modules.proxyInsurance.entity.InsuranceInHand;
import org.jeecg.modules.proxyInsurance.service.impl.InsuranceInHandServiceImpl;
import org.jeecg.modules.team.service.IInsuranceTeamService;
import org.jeecg.snowFlake.SnowFlakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 底层共通业务接口说明（新创建Maven模块项目）
 * <p>
 * jeecg-boot-module-system 项目禁止其他独立模块直接引用，如果需要共通API或者调用系统信息接口请使用
 * <p>
 * 统一的业务接口： jeecg-boot-base-common/org.jeecg.common.system.api.ISysBaseAPI(所有子模块需要的接口都在这里定义)
 * 接口实现类在system里： jeecg-boot-module-system/org.jeecg.modules.system.service.impl.SysBaseApiImpl
 * jeecg-boot-module-system不让直接引用的目的是，防止相互调用，另外让子模块项目更轻量，如果调用system，随着项目累计，会导致乱也不好维护。
 *
 * @Description: 核对的保单
 * @Author: jeecg-boot
 * @Date: 2021-05-27
 * @Version: V1.0
 */
@Service
public class CheckInsuranceServiceImpl extends ServiceImpl<CheckInsuranceMapper, CheckInsurance> implements ICheckInsuranceService {
    @Autowired
    private ICompanyInsuranceService companyInsuranceService;
    @Autowired
    private IInsuranceTeamService teamService;
    @Autowired
    private CheckInsuranceMapper checkInsuranceMapper;
    @Autowired
    private InsuranceInHandServiceImpl insuranceInHandService;

//    @Autowired
//    private IInsuranceUsageService usageService;
//
//    @Autowired
//    private IInsuranceRebateRatioService rebateRatioService;
//
//    @Autowired
//    private InsuranceInHandMapper insuranceInHandMapper;

    public final Integer FIXED_VALUE = 20000;//司机责任险最小值
    private final double COEFFICIENT = 1.06; //系数


    /**
     * 比对并构建checkInsurance对象
     * 比对重点信息：团队录入的表：出单日期（转保单日期）、出单员、交强险保单号、商业险保单号
     * 保司的表：转保单时间、出单员、保单号
     *
     * @param insuranceInHand
     * @return
     */
    @Override
    @Transactional
    public synchronized Result<?> checkAndSaveInsuracne(InsuranceInHand insuranceInHand) {

//        根据录入保单和保司保单比较生成对比结果数据保单
        CheckInsurance checkInsurance = new CheckInsurance();
        String compulsoryInsurCode = insuranceInHand.getCompulsoryInsurCode();//交强险保单号
        String commercialInsurCode = insuranceInHand.getCommercialInsurCode();//商业险保单号
        Date insuranceDate = insuranceInHand.getInsuranceDate();//出单日期
//        String billMan = insuranceInHand.getBillMan();//出单员
        Double vesselTax = insuranceInHand.getVehicleVesselTax();//车船税
        Double compulsoryServiceHarge = 0.0;//交强险手续费
        Double commercialServiceHarge = 0.0;//商业险手续费
//        boolean flag = false;//标志是否更新或插入成功
//        交强险签单保费（含税）
        Double insureCompulsoryFeeIncludeTax = null;
//        商业险签单保费（含税）
        Double insureCommercialFeeIncludeTax = null;
//        根据手输保单车架号，查询保司保单的交强险保单号和商业险保单号
//        比对的字段在两张表中不能为空
//        1.查到则获取保司保单，比对数据
//        1.1 获取车架号
        String vehicleIdentity = insuranceInHand.getVehicleIdentity();
        int insert = 0;
        boolean res = false;//比对结果
        if (vehicleIdentity != null) { //
//            查询保司
            List<CompanyInsurance> companyInsuranceList = companyInsuranceService.getCompanyInsuranceByVehicleIdentity(vehicleIdentity);

            if (CollectionUtils.isNotEmpty(companyInsuranceList)) {
//                1.核对交强险保单号
                for (CompanyInsurance companyInsurance : companyInsuranceList) {
                    if (companyInsurance.getInsuranceNum().equals(compulsoryInsurCode)) {//如果是交强险保单
//                        封装数据，从保司保单中获取数据
                            //交强险保单号
                            checkInsurance.setCompulsoryInsurCode(companyInsurance.getInsuranceNum());
//                            交强险保费
                            insureCompulsoryFeeIncludeTax = companyInsurance.getInsureFeeIncludeTax();
                            checkInsurance.setCompulsoryInsurFee(new BigDecimal(insureCompulsoryFeeIncludeTax));
//                            交强险签单手续费
                            compulsoryServiceHarge = companyInsurance.getSignServiceHarge();
                            //                            如果只有交强险
                        if(companyInsuranceList.size() == 1){
                            insuranceInHand.setCompulsoryInsurCode(companyInsurance.getInsuranceNum());//交强险保单号
                            insuranceInHand.setCompulsoryInsurFee(companyInsurance.getInsureFeeIncludeTax());//交强险保费
                            //                      封装数据
                            createCheckedInsurance(insuranceInHand, checkInsurance, vesselTax, companyInsurance);
                        }
                        res = true;
                    }
//                    2.核对商业险保单号
                    if (companyInsurance.getInsuranceNum().equals(commercialInsurCode)) {//如果是商业险保单
//                        封装数据，从保司保单中获取数据
//                        if (DateUtils.formatDate(insuranceDate, "yyyy-MM-dd").equals(DateUtils.formatDate(companyInsurance.getZbTime(), "yyyy-MM-dd")) && billMan.equals(companyInsurance.getSalesMan())) {
                            //商业险保单号
                            checkInsurance.setCommercialInsurCode(companyInsurance.getInsuranceNum());
//                            商业险保费
                            insureCommercialFeeIncludeTax = companyInsurance.getInsureFeeIncludeTax();

//                        商业险保费
                        checkInsurance.setCommercialInsurFee(new BigDecimal(insureCommercialFeeIncludeTax));
//                          商业签单手续费
                            commercialServiceHarge = companyInsurance.getSignServiceHarge();
//                     封装数据checkInsurance
                        createCheckedInsurance(insuranceInHand, checkInsurance, vesselTax, companyInsurance);
                        insuranceInHand.setCommercialInsurCode(companyInsurance.getInsuranceNum());//商业险保单号
                            res = true;
                        }

                    }
                }
                if (res) {
//                保费总额=交强险签单保费+商业险签单保费
                    if(insureCompulsoryFeeIncludeTax != null && insureCommercialFeeIncludeTax != null){
                        checkInsurance.setInsuranceTotalFee(insureCompulsoryFeeIncludeTax + insureCommercialFeeIncludeTax);
                        checkInsurance.setSignServiceHarge(compulsoryServiceHarge + commercialServiceHarge);//保险公司提供的手续费总额
                    }
                    if(insureCompulsoryFeeIncludeTax == null && insureCommercialFeeIncludeTax != null){//没有交强险，有商业险
                        checkInsurance.setInsuranceTotalFee(insureCommercialFeeIncludeTax);//取商业险保费
                        checkInsurance.setSignServiceHarge(commercialServiceHarge);//保险公司提供的手续费总额
                    }
                    if(insureCompulsoryFeeIncludeTax != null && insureCommercialFeeIncludeTax == null){//有交强险，没有商业险
                        checkInsurance.setInsuranceTotalFee(insureCompulsoryFeeIncludeTax);//取交强险保费
                        checkInsurance.setSignServiceHarge(compulsoryServiceHarge);//保险公司提供的手续费总额
                    }
//                    设置比对时间
                    checkInsurance.setCheckDate(new Date());
//                   录入日期
                    checkInsurance.setInputInsuranceDate(insuranceInHand.getCreateTime());
//                    保单编号(雪花算法)
                    checkInsurance.setInsureNum(SnowFlakeUtil.getId().toString());
//                保存数据
//                判断比对过的数据库是否存在该数据(根据车架号、商业保单号、交强保单号)
//                  1. 存在则更新(判断是否是今年的保单)
                    CheckInsurance checkObj = this.selectByVehicleIdAndCommercialInsurCodeAndCompulsoryInsurCode(checkInsurance);
                    if (checkObj != null) {
//                        将第二次比对的数据拷贝到数据库的记录中更新数据
                        MyBeanUtil.copyPropertiesIgnoreNull(checkInsurance, checkObj);
                        if (checkInsuranceMapper.updateById(checkObj) == 1) {
//                           flag = true;
                            insuranceInHand.setIsChecked(1);//0：未比对  1：已比对
                            // TODO: 2021/6/18 0018

//                                        是否需要用保司保单的数据覆盖手输保单的其他数据
                            insuranceInHandService.updateById(insuranceInHand);
                            return Result.OK("比对成功", insuranceInHand);
                        }
                    } else {
//                  2. 不存在则新增
                        insert = checkInsuranceMapper.insert(checkInsurance);
                    }
//                 比对成功修改insuranceInhand的比对状态为已比对
                    if (insert != 0) {
                        insuranceInHand.setIsChecked(1);//0：未比对  1：已比对
                        insuranceInHandService.updateById(insuranceInHand);
                        return Result.OK("比对成功", insuranceInHand);
                    }
                }
            }
//        }
        return Result.error(400, "请检查保单号、车架号是否正确或保司保单是否已录入");
    }

    /**
     * 封装比对后的数据
     * @param insuranceInHand
     * @param checkInsurance
     * @param vesselTax
     * @param companyInsurance
     */
    private void createCheckedInsurance(InsuranceInHand insuranceInHand, CheckInsurance checkInsurance, Double vesselTax, CompanyInsurance companyInsurance) {
//        出单日期
        checkInsurance.setInsuranceDate(companyInsurance.getZbDate());
//                           出单员
        checkInsurance.setBillMan(companyInsurance.getSalesMan());
//                           业务员
        checkInsurance.setSalesman(insuranceInHand.getSalesman());
//                           车牌号
        checkInsurance.setVehicleLicense(companyInsurance.getVehicleLicense());
//                           客户名称
        checkInsurance.setCustomerName(companyInsurance.getCarOwner());
//                           车架号
        checkInsurance.setVehicleIdentity(companyInsurance.getVehicleIdentity());
//                           车船税
        checkInsurance.setVehicleVesselTax(vesselTax);
//
//                           渠道名称
        checkInsurance.setDistributionChannelId(companyInsurance.getDistributionChannelName());
//                           起保日期
        checkInsurance.setInsureStartDate(companyInsurance.getInsureStartDate());
//                           初登日期
        checkInsurance.setRegisterDate(companyInsurance.getRegisterDate());
//                           新续保标志
        checkInsurance.setRenewalType(companyInsurance.getRenewalType());
        //                           承保险别名称
        checkInsurance.setInsuranceAlias(companyInsurance.getInsuranceAlias());
        //                           座位
        checkInsurance.setSeatsNum(companyInsurance.getSeatsNum());
        //                           备注
        checkInsurance.setRemarks(insuranceInHand.getRemark());

//                           使用性质
        checkInsurance.setCarUsageType(companyInsurance.getCarUsageType());
//                           车辆种类
        checkInsurance.setVehiclesType(companyInsurance.getVehiclesType());
//                           是否录入过户标志
        if (Integer.valueOf(companyInsurance.getIsTransfer()) == IsTransfer.TRANSFER.getType()) {
            checkInsurance.setIsTransfer(IsTransfer.TRANSFER.getText());
        } else {
            checkInsurance.setIsTransfer(IsTransfer.NO_TRANSFER.getText());
        }
//                      覆盖录入保单信息
//                      起保日期
//        insuranceInHand.setInsureStartDate(companyInsurance.getInsureStartDate());
            insuranceInHand.setInsuranceDate(companyInsurance.getZbTime());

        insuranceInHand.setVehicleLicense(companyInsurance.getVehicleLicense());
        insuranceInHand.setCustomer(companyInsurance.getCarOwner());
        insuranceInHand.setVehicleIdentity(companyInsurance.getVehicleIdentity());
        insuranceInHand.setDistributionChannelId(companyInsurance.getDistributionChannelName());


        //                           三责保额
        checkInsurance.setThirdPartyInsured(companyInsurance.getThirdPartyInsured());
//                           车损险保额
        checkInsurance.setCarDamageInsured(companyInsurance.getCarDamageInsured());
//                           司机责任险保额
        checkInsurance.setDriverLiabilityInsure(companyInsurance.getDriverLiabilityInsure());
//                           乘客责任险保额
        checkInsurance.setPassengerLiability(companyInsurance.getPassengerLiability());
    }

    @Override
    public CheckInsurance selectByVehicleIdentity(String vehicleIdentity) {
        QueryWrapper<CheckInsurance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vehicle_identity", vehicleIdentity);
        return checkInsuranceMapper.selectOne(queryWrapper);
    }

    @Override
    public List<CheckInsurance> sortByInsuranceDay() {
        QueryWrapper<CheckInsurance> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("insurance_date");
        return checkInsuranceMapper.selectList(queryWrapper);
    }

    @Override
    public List<CheckInsurance> sortByInputTime() {
        QueryWrapper<CheckInsurance> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("input_insurance_date");
        return checkInsuranceMapper.selectList(queryWrapper);
    }


    /**
     * 根据车架号，商业保单号、交强险保单号
     *
     * @param checkInsurance
     * @return
     */
    private CheckInsurance selectByVehicleIdAndCommercialInsurCodeAndCompulsoryInsurCode(CheckInsurance checkInsurance) {
        String vehicleIdentity = checkInsurance.getVehicleIdentity();
        String compulsoryInsurCode = checkInsurance.getCompulsoryInsurCode();
        String commercialInsurCode = checkInsurance.getCommercialInsurCode();
        return checkInsuranceMapper.selectByVehicleIdAndCommercialInsurCodeAndCompulsoryInsurCode(vehicleIdentity,compulsoryInsurCode,commercialInsurCode);
    }


}
