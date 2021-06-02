package org.jeecg.modules.demo.checked.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.enumUtil.IsTransfer;
import org.jeecg.modules.demo.checked.entity.CheckInsurance;
import org.jeecg.modules.demo.checked.mapper.CheckInsuranceMapper;
import org.jeecg.modules.demo.checked.service.ICheckInsuranceService;
import org.jeecg.modules.demo.companyInsurance.entity.CompanyInsurance;
import org.jeecg.modules.demo.companyInsurance.service.ICompanyInsuranceService;
import org.jeecg.modules.demo.proxyInsurance.entity.InsuranceInHand;
import org.jeecg.modules.demo.team.service.IInsuranceTeamService;
import org.apache.commons.collections.CollectionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 * 底层共通业务接口说明（新创建Maven模块项目）
 *
 * jeecg-boot-module-system 项目禁止其他独立模块直接引用，如果需要共通API或者调用系统信息接口请使用
 *
 * 统一的业务接口： jeecg-boot-base-common/org.jeecg.common.system.api.ISysBaseAPI(所有子模块需要的接口都在这里定义)
 * 接口实现类在system里： jeecg-boot-module-system/org.jeecg.modules.system.service.impl.SysBaseApiImpl
 * jeecg-boot-module-system不让直接引用的目的是，防止相互调用，另外让子模块项目更轻量，如果调用system，随着项目累计，会导致乱也不好维护。
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
    public int checkAndSaveInsuracne(InsuranceInHand insuranceInHand) {
//        根据录入保单和保司保单比较生成对比结果数据保单
        CheckInsurance checkInsurance = new CheckInsurance();
        String compulsoryInsurCode = insuranceInHand.getCompulsoryInsurCode();//交强险保单号
        String commercialInsurCode = insuranceInHand.getCommercialInsurCode();//商业险保单号
        Date insuranceDate = insuranceInHand.getInsuranceDate();//出单日期
        String salesman = insuranceInHand.getSalesman();//出单员
        Double vesselTax = insuranceInHand.getVehicleVesselTax();//车船税
        Double CompulsoryServiceHarge = 0.0;//交强险手续费
        Double commercialServiceHarge = 0.0;//商业险手续费
//        交强险签单保费（含税）
        double insureCompulsoryFeeIncludeTax = 0.0;
//        商业险签单保费（含税）
        double insureCommercialFeeIncludeTax = 0.0;
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
                    if (compulsoryInsurCode.equals(companyInsurance.getInsureNum())) {//如果是交强险保单
//                        封装数据，从保司保单中获取数据
                        if (insuranceDate.equals(companyInsurance.getZbDate()) && salesman.equals(companyInsurance.getSalesMan())) {
                            //交强险保单号
                            checkInsurance.setCompulsoryInsurCode(companyInsurance.getInsureNum());
//                            交强险保费
                            insureCompulsoryFeeIncludeTax = companyInsurance.getInsureFeeIncludeTax();
                            checkInsurance.setCompulsoryInsurFee(insureCompulsoryFeeIncludeTax);
//                            交强险签单手续费
                            CompulsoryServiceHarge = companyInsurance.getSignServiceHarge();
                        }
                    }
//                    2.核对商业险保单号
                    if (commercialInsurCode.equals(companyInsurance.getInsureNum())) {//如果是商业险保单
//                        封装数据，从保司保单中获取数据
                        if (insuranceDate.equals(companyInsurance.getZbDate()) && salesman.equals(companyInsurance.getSalesMan())) {
                            //商业险保单号
                            checkInsurance.setCommercialInsurCode(companyInsurance.getInsureNum());
//                            商业险保费
                            insureCommercialFeeIncludeTax = companyInsurance.getInsureFeeIncludeTax();
//                          签单手续费 = 交强险手续费+商业险手续费
                            commercialServiceHarge = companyInsurance.getSignServiceHarge();
//                     封装数据checkInsurance
//                        出单日期
                            checkInsurance.setInsuranceDate(companyInsurance.getZbDate());
//                           出单员
                            checkInsurance.setSalesman(companyInsurance.getSalesMan());
//                           团队
                            String teamName = teamService.getTeamNameByCode(insuranceInHand.getInsuranceTeam());
                            checkInsurance.setInsuranceTeam(teamName);
//                           车牌号
                            checkInsurance.setVehicleLicense(companyInsurance.getVehicleLicense());
//                           客户名称
                            checkInsurance.setCustomerName(companyInsurance.getCarOwner());
//                           车架号
                            checkInsurance.setVehicleIdentity(companyInsurance.getVehicleIdentity());
//                           车船税
                            checkInsurance.setVehicleVesselTax(vesselTax);
//
//                           商业险保费
                            checkInsurance.setCommercialInsurFee(insureCommercialFeeIncludeTax);
//
//                           交强险手续费比例
                            checkInsurance.setCompulsoryInsuranceRebate(insuranceInHand.getCompulsoryInsuranceRebate());
//                           商业险手续费比例
                            checkInsurance.setCommercialInsuranceRebate(insuranceInHand.getCommercialInsuranceRebate());
//                           额外奖励金额
                            checkInsurance.setBonus(insuranceInHand.getSeatBonus());
//                           手续费总额
                            checkInsurance.setTotalServiceFee(insuranceInHand.getTotalServiceFee());
//                           渠道名称
                            checkInsurance.setDistributionChannelId(companyInsurance.getDistributionChannelName());
//                           起保日期
                            checkInsurance.setInsureStartDate(companyInsurance.getInsureStartDate());
//                           初登日期
                            checkInsurance.setRegisterDate(companyInsurance.getRegisterDate());
//                           新续保标志
                            checkInsurance.setRenewalType(companyInsurance.getRenewalType());
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
//                           三责保额
                            checkInsurance.setThirdPartyInsured(companyInsurance.getThirdPartyInsured());
//                           车损险保额
                            checkInsurance.setCarDamageInsured(companyInsurance.getCarDamageInsured());
//                           承保险别名称
                            checkInsurance.setInsuranceAlias(companyInsurance.getInsuranceAlias());
//                           座位
                            checkInsurance.setSeatsNum(companyInsurance.getSeatsNum());
//                           司机责任险保额
                            checkInsurance.setDriverLiabilityInsure(companyInsurance.getDriverLiabilityInsure());
//                           乘客责任险保额
                            checkInsurance.setPassengerLiability(companyInsurance.getPassengerLiability());
//                           备注
                            checkInsurance.setRemarks(insuranceInHand.getRemark());
                            res = true;
                        }
                    }
                }
                if (res) {
//                保费总额=交强险签单保费+商业险签单保费+车船税（从录入保单中取）
                    checkInsurance.setInsuranceTotalFee(insureCompulsoryFeeIncludeTax + insureCommercialFeeIncludeTax + vesselTax);
//                签单手续费
                    checkInsurance.setSignServiceHarge(CompulsoryServiceHarge + commercialServiceHarge);
//                保存数据
                   insert = checkInsuranceMapper.insert(checkInsurance);
                }
            }
        }
        return insert;
    }
}
