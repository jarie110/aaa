package org.jeecg.modules.proxyInsurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.exception.InsuranceException;
import org.jeecg.common.util.DateUtils;
import org.jeecg.enumUtil.IsTransfer;
import org.jeecg.enumUtil.RebateType;
import org.jeecg.enumUtil.RenewalTypeEnum;
import org.jeecg.modules.companyInsurance.service.ICompanyInsuranceService;
import org.jeecg.modules.checked.entity.CheckInsurance;
import org.jeecg.modules.checked.service.ICheckInsuranceService;
import org.jeecg.modules.companyInsurance.entity.CompanyInsurance;
import org.jeecg.modules.proxyInsurance.entity.InsuranceInHand;
import org.jeecg.modules.proxyInsurance.entity.RenewalPo;
import org.jeecg.modules.proxyInsurance.mapper.InsuranceInHandMapper;
import org.jeecg.modules.proxyInsurance.service.IInsuranceInHandService;
import org.jeecg.modules.rebate.entity.InsuranceRebateRatio;
import org.jeecg.modules.rebate.service.IInsuranceRebateRatioService;
import org.jeecg.modules.usage.entity.InsuranceUsage;
import org.jeecg.modules.usage.service.IInsuranceUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 录入的保单
 * @Author: jeecg-boot
 * @Date: 2021-05-26
 * @Version: V1.0
 */
@Service
public class InsuranceInHandServiceImpl extends ServiceImpl<InsuranceInHandMapper, InsuranceInHand> implements IInsuranceInHandService {
    @Autowired
    private IInsuranceUsageService usageService;

    @Autowired
    private InsuranceInHandMapper insuranceInHandMapper;

    @Autowired
    private IInsuranceRebateRatioService rebateRatioService;

    @Autowired
    private ICompanyInsuranceService companyInsuranceService;

    @Autowired
    private ICheckInsuranceService checkInsuranceService;

    @Value("${coefficient:1.06}")
    private BigDecimal coefficient;
    /**
     * 计算总返点比例
     *
     * @param insuranceInHand
     */
    @Transactional
    @Override
    public Result<?> serviceTotalFee (InsuranceInHand insuranceInHand) throws ParseException {
        //查询手输保单比对状态
        if (insuranceInHand.getIsChecked() == 1) {
            //        总返点比例
            BigDecimal rebateAll = new BigDecimal("0.00");
//        比对成功，计算
            //        查询保司保单的信息
            String commercialInsurCode = insuranceInHand.getCommercialInsurCode();
            CompanyInsurance companyInsurance = companyInsuranceService.getInstanceByCommerialInsurCode(commercialInsurCode);
            Date zbTime = companyInsurance.getZbTime();
//            String startDate = DateUtils.formatDate(zbTime)+ "00:00:00";
            BigDecimal bonus = new BigDecimal("0.00");
            /*设置商业险返点佣金*/
            List<BigDecimal> percentList = new ArrayList<>();
            //		1.使用性质（根据使用性质类型，查询对应的返点比例）
            if (companyInsurance != null) {
                String usageType = companyInsurance.getCarUsageType();
                InsuranceUsage insuranceUsage = usageService.selectByUserType(usageType);//查询使用性质
                InsuranceRebateRatio rebateRatioBase = rebateRatioService.getInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDate(RebateType.COMMERCIAL_BASIC_REBATE.getType(),usageType,zbTime);
                if(insuranceUsage != null && rebateRatioBase != null){
                    BigDecimal ratio = rebateRatioBase.getRebateRatio();
                    percentList.add(ratio);
                }else {
                    // TODO: 2021/6/11 0011
                    return Result.error(400,"未设置当天商业基础险，请先设置然后再计算",RebateType.COMMERCIAL_BASIC_REBATE.getType());//将返点比例类型传递前台
                }
//        2.获取用户的三者险保额、车损险保额，司机责任险保额，乘客责任险保额
                Double thirdPartyInsured = companyInsurance.getThirdPartyInsured() == null ? 0 : companyInsurance.getThirdPartyInsured();//三者险保额
                Double carDamageInsured = companyInsurance.getCarDamageInsured() == null ? 0 : companyInsurance.getCarDamageInsured();//车损险保额
                Double driverLiabilityInsured = companyInsurance.getDriverLiabilityInsure() == null ? 0 : companyInsurance.getDriverLiabilityInsure();//司机责任险保额
                Double passengerLiabilityInsured = companyInsurance.getPassengerLiability() == null ? 0 : companyInsurance.getPassengerLiability();//乘客责任险保额

//		三者保额比例（使用性质，三者保额，车损保额是否达标）
                List<InsuranceRebateRatio> insuranceRebateRatioList = rebateRatioService.getInsuranceRebateRatioByTypeAndInsuranceDate(RebateType.THIRD_PARTY_REBATE.getType(),zbTime);
                ArrayList<Double> insureds = new ArrayList<>();
                BigDecimal thirdPartyRebate = new BigDecimal(0);
                if (CollectionUtils.isNotEmpty(insuranceRebateRatioList)) {
                    for (InsuranceRebateRatio insuranceRebateRatio : insuranceRebateRatioList) {
//            集合装所有查询到的保额
                        String rebateThirdPatyInsured = insuranceRebateRatio.getThirdPartyInsured();
                        if (!rebateThirdPatyInsured.equals("-")) {
                            insureds.add(Double.valueOf(rebateThirdPatyInsured));
                        }
                    }
                    //输入保单的三者保额小于最小保额
                    if (thirdPartyInsured != null && thirdPartyInsured < Collections.min(insureds) ) {
                        for (InsuranceRebateRatio insuranceRebateRatio : insuranceRebateRatioList) {
                            if (!insuranceRebateRatio.getThirdPartyInsured().equals("-")
                                    && Double.valueOf(insuranceRebateRatio.getThirdPartyInsured()).toString().equals(Collections.min(insureds).toString())
                                    && Integer.parseInt(usageType) == Integer.valueOf(insuranceRebateRatio.getUsageType())) {
                                thirdPartyRebate = insuranceRebateRatio.getRebateRatio();
                            }
                        }
                    }

                    //保单的三者保额大于或等于最大保额,且车损险为0
                    if (carDamageInsured == 0 && thirdPartyInsured >= Collections.max(insureds)) {
                        for (InsuranceRebateRatio insuranceRebateRatio : insuranceRebateRatioList) {
                            if (!insuranceRebateRatio.getCarDamageInsured().equals("-")
                                    && Double.valueOf(insuranceRebateRatio.getCarDamageInsured()) == 0
                                    && Double.valueOf(insuranceRebateRatio.getThirdPartyInsured()) == Collections.max(insureds)
                                    && Integer.parseInt(usageType) == Integer.valueOf(insuranceRebateRatio.getUsageType())) {
                                thirdPartyRebate = insuranceRebateRatio.getRebateRatio();
                            }
                        }
                    }

                    //保单的三者保额大于或等于最大保额,且车损险不为0
                    if (carDamageInsured != 0 && thirdPartyInsured >= Collections.max(insureds)) {
                        for (InsuranceRebateRatio insuranceRebateRatio : insuranceRebateRatioList) {
                            if (!insuranceRebateRatio.getCarDamageInsured().equals("-")
                                    && Double.valueOf(insuranceRebateRatio.getCarDamageInsured()) != 0
                                    && Double.valueOf(insuranceRebateRatio.getThirdPartyInsured()) == Collections.max(insureds)
                                    && Integer.parseInt(usageType) == Integer.valueOf(insuranceRebateRatio.getUsageType())) {
                                thirdPartyRebate = insuranceRebateRatio.getRebateRatio();
                            }
                        }
                    }

//                    todo
                    //保单的三者保额处于中间段,且车损险不为0
                    if (carDamageInsured != 0 && thirdPartyInsured > Collections.min(insureds) && thirdPartyInsured < Collections.max(insureds)) {
                        for (InsuranceRebateRatio insuranceRebateRatio : insuranceRebateRatioList) {
                            if (!insuranceRebateRatio.getCarDamageInsured().equals("-")
                                    && Double.valueOf(insuranceRebateRatio.getCarDamageInsured()) != 0
                                /*&& Double.valueOf(insuranceRebateRatio.getThirdPartyInsured()) == Collections.max(insureds)*/
                                    && Integer.parseInt(usageType) == Integer.valueOf(insuranceRebateRatio.getUsageType())) {
                                thirdPartyRebate = insuranceRebateRatio.getRebateRatio();
                            }
                        }
                    }
                }else{
                    return Result.error(400,"未设置当天三者责任险返点比例，请先设置然后再计算");
                }

                percentList.add(thirdPartyRebate);
//		3.判断该车是否为新车标志
                String renewalType = companyInsurance.getRenewalType();

//        判断时间
                int y = diffDate(companyInsurance);

                if (Integer.parseInt(renewalType) == RenewalTypeEnum.NEW_CAR_RENEWAL.getType() && y <= 1) {
//            新车返点比
                    try {
                        addPercent(percentList, RebateType.NEW_CAR_REBATE.getType(),zbTime);
                    } catch (InsuranceException e) {
                        // TODO: 2021/6/11 0011  
                      return Result.error(400,"未设置新车返点比例，请先设置在计算",RebateType.NEW_CAR_REBATE.getType());
                    }
                } else if (Integer.parseInt(renewalType) == RenewalTypeEnum.LAST_YEAR_RENEWAL.getType() && y > 1 && y <= 2) {
//            次新车返点比
                    try {
                        addPercent(percentList, RebateType.LAST_YEAR_CAR_REBATE.getType(),zbTime);
                    }catch (InsuranceException e){
                        // TODO: 2021/6/11 0011
                        return Result.error(400,"未设置次新车返点比例，请先设置在计算",RebateType.LAST_YEAR_CAR_REBATE.getType());
                    }
                }
//		4.判断该车是否为竞回标志
                if (Integer.parseInt(renewalType) == RenewalTypeEnum.COMPETITION_RENEWAL.getType()) {
//            竞回返点比
                    try {
                        addPercent(percentList, RebateType.COMPETITION_REBATE.getType(),zbTime);
                    }catch (InsuranceException e){
                        // TODO: 2021/6/11 0011
                        return Result.error(400,"未设置竟回返点比例，请先设置在计算",RebateType.COMPETITION_REBATE.getType());
                    }
                }
//		5.判断该车是否过户
                if (companyInsurance.getIsTransfer() == IsTransfer.TRANSFER.getType()) {
//            已过户
                    try {
                        addPercent(percentList, RebateType.IS_TRANSFER_REBATE.getType(),zbTime);
                    } catch (InsuranceException e) {
                        // TODO: 2021/6/11 0011
                        return Result.error(400,"未设置过户返点比例，请先设置在计算",RebateType.IS_TRANSFER_REBATE.getType());
                    }
                }
//		6.判断该车是否为交叉比例
//        1.市公司续保
//        2.未过户
//        3.不是新车和次新车
                if (Integer.parseInt(renewalType) == RenewalTypeEnum.COMPANY_RENEWAL.getType()
                        && companyInsurance.getIsTransfer() == IsTransfer.NO_TRANSFER.getType()
                        && y > 2
                ) {
                    try {
                        addPercent(percentList, RebateType.OVERLAPPING_REBATE.getType(),zbTime);
                    } catch (InsuranceException e) {
                        // TODO: 2021/6/11 0011
                        return Result.error(400,"未设置交叉返点比例，请先设置在计算",RebateType.OVERLAPPING_REBATE.getType());
                    }
                }
//		7.判断是否为跟单0比例(保险公司提供的数据中签单手续费为0)
//        根据商业险保单判断
                if (companyInsurance.getSignServiceHarge() == 0) {
                    try {
                        addPercent(percentList, RebateType.FOLLOW_UP_REBATE.getType(),zbTime);
                    } catch (InsuranceException e) {
                        // TODO: 2021/6/11 0011
                        return Result.error(400,"未设置跟单零返点比例，请先设置在计算",RebateType.FOLLOW_UP_REBATE.getType());
                    }
                }
//		8.判断是否为座位保（使用性质，司机责任保额，乘客责任保额，是否达标）
//                如果乘客险和司机险不为零则查询座位保返点
                if(driverLiabilityInsured != 0 && passengerLiabilityInsured != 0){
                    Integer seatsNum = companyInsurance.getSeatsNum();//获取车座位数

                    if (passengerLiabilityInsured >= (seatsNum - 1) * driverLiabilityInsured) {
                        List<InsuranceRebateRatio> rebateRatioList = rebateRatioService.getInsuranceRebateRatioByTypeAndInsuranceDate(RebateType.SEAT_INSURANCE.getType(),zbTime);
                        for (InsuranceRebateRatio rebateRatio : rebateRatioList) {
//                      这里需要客户自定根据使用性质设置座位保奖励（需要对每种汽车使用性质进行设置）
                            if (!rebateRatio.getUsageType().equals("-") && Integer.parseInt(usageType) == Integer.valueOf(rebateRatio.getUsageType())) {
                                //座位保奖金
                                bonus = rebateRatio.getBonus();
                                insuranceInHand.setSeatBonus(bonus);
                                try {
                                    addPercent(percentList,RebateType.SEAT_INSURANCE.getType(),zbTime);
                                } catch (InsuranceException e) {
                                    return Result.error(400,"未设置座位保返点比例，请先设置再计算",RebateType.SEAT_INSURANCE.getType());
                                }
                            }
                        }
                    } else {
//            修改数据时，不满足则奖金为零
                        insuranceInHand.setSeatBonus(bonus);
                    }
                }

                //		9.其他或转入 todo
//        根据新续保标志判断是否为转入保单
                if (Integer.parseInt(renewalType) == RenewalTypeEnum.BRANCH_RENEWAL.getType()) {
                    try {
                        addPercent(percentList, RebateType.BATCH_REBATE.getType(),zbTime);
                    } catch (InsuranceException e) {
                        // TODO: 2021/6/11 0011
                        return Result.error(400,"未设置支公司续保返点比例，请先设置在计算",RebateType.BATCH_REBATE.getType());
                    }
                }

//                10.支公司续保返点
                if (Integer.parseInt(renewalType) == RenewalTypeEnum.REPLACEMENT_RENEWAL.getType()) {
                    try {
                        addPercent(percentList, RebateType.CHANGE_INTO_INSURANCE.getType(),zbTime);
                    } catch (InsuranceException e) {
                        // TODO: 2021/6/11 0011
                        return Result.error(400,"未设置转入保单返点比例，请先设置在计算",RebateType.CHANGE_INTO_INSURANCE.getType());
                    }
                }


//        计算返点比例总和
                rebateAll = new BigDecimal(percentList.stream().collect(Collectors.summingDouble(BigDecimal::doubleValue)));
//        比对成功计算手续费总额
//                根据商业保单号查询checkInsurance
                CheckInsurance checkInsurance = checkInsuranceService.selectByCommercialInsurCode(insuranceInHand.getCommercialInsurCode());


                BigDecimal insureCommercialFeeIncludeTax = checkInsurance.getCommercialInsurFee();//获取商业保费
                BigDecimal insureCompulsoryFeeIncludeTax = checkInsurance.getCompulsoryInsurFee();//获取交强保费

                BigDecimal totalServiceFee = new BigDecimal("0.00");//总服务费
//                商业险保费 ÷ 1.06
                BigDecimal  res1 = new BigDecimal(String.valueOf(insureCommercialFeeIncludeTax.doubleValue()+"0"))
                        .divide(coefficient,2);

//                商业险总返点百分比
                BigDecimal res3 = rebateAll.divide(new BigDecimal("100"),2,BigDecimal.ROUND_UNNECESSARY);


                //                交强险保费 ÷ 1.06
                BigDecimal  res2 = new BigDecimal(String.valueOf(insureCompulsoryFeeIncludeTax.doubleValue())+"0")
                        .divide(coefficient,2);

                //交强险返点比

                BigDecimal res4 = insuranceInHand.getCompulsoryInsuranceRebate().divide(new BigDecimal("100"));
                try{
                    //               设置总返点比
                    checkInsurance.setTotalRebate(rebateAll.add(insuranceInHand.getCompulsoryInsuranceRebate()).setScale(2,BigDecimal.ROUND_UNNECESSARY));
                }catch (NullPointerException e){
                    return Result.error("比对后的保单不存在");
                }

                totalServiceFee = res1.multiply(res3).add(res2.multiply(res4)).add(bonus).setScale(2,BigDecimal.ROUND_HALF_UP);
                checkInsurance.setTotalServiceFee(totalServiceFee);

                //设置返点支付方式
                checkInsurance.setRebateWay(insuranceInHand.getPaymentWay());

                boolean flag = checkInsuranceService.updateById(checkInsurance);
                if(flag){
                    return Result.OK("计算成功");
                }else {
                    return Result.error("比对保单更新失败，请重新比对");
                }
            }
        }
        return Result.error("计算失败请先比对保单");
    }

        /**
         * 判断是否修改过数据
         * @param insuranceInHandFromForm
         * @return
         */
        @Override
        public boolean isEquals (InsuranceInHand insuranceInHandFromForm){
            InsuranceInHand insuranceInHand = insuranceInHandMapper.selectById(insuranceInHandFromForm.getId());
            return insuranceInHand.equals(insuranceInHandFromForm);
        }

    /**
     * 比对后的保单数
     * @return
     */
    @Override
    public int queryByIsCheck(String uid) {
        QueryWrapper<InsuranceInHand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_checked",1);
        queryWrapper.eq("uid",uid);
        Integer count = insuranceInHandMapper.selectCount(queryWrapper);
        return count;
    }

    /**
     * 已支付的保单
     * @return
     */
    @Override
    public int queryByPaid(String uid) {
        QueryWrapper<InsuranceInHand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_pay_commission",1);
        queryWrapper.eq("uid",uid);
        Integer count = insuranceInHandMapper.selectCount(queryWrapper);
        return count;
    }

    /**
     * 计算当前用户下所有保单数
     * @param uid
     * @return
     */
    @Override
    public int countByUser(String uid) {
        QueryWrapper<InsuranceInHand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        Integer count = insuranceInHandMapper.selectCount(queryWrapper);
        return count;

    }

    /**
     * 计算该用户所有保单总保费
     * @param uid
     * @return
     */
    @Override
    public BigDecimal totalInsuranceFee(String uid) {
        QueryWrapper<InsuranceInHand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        List<InsuranceInHand> insuranceInHandList = insuranceInHandMapper.selectList(queryWrapper);
        BigDecimal totalFee = new BigDecimal("0.00");

        for (InsuranceInHand insuranceInHand : insuranceInHandList) {
            BigDecimal insuranceFee = insuranceInHand.getInsuranceTotalFee();
            String insuranceFeeStr = null;
            if(insuranceFee != null){
                insuranceFeeStr = insuranceFee.toString();
            }else {
                insuranceFeeStr = "0.00";
            }
            BigDecimal insuranceFee2 = new BigDecimal(insuranceFeeStr);
            totalFee = totalFee.add(insuranceFee2).setScale(2,BigDecimal.ROUND_HALF_UP);
        }
        return totalFee;
    }

    /**
     * 已返点总金额
     * @param uid
     * @return
     */
    @Override
    public BigDecimal totalInsurancePaidFee(String uid) {
        QueryWrapper<InsuranceInHand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid).eq("is_pay_commission",1);
        List<InsuranceInHand> insuranceInHandList = insuranceInHandMapper.selectList(queryWrapper);
        BigDecimal totalFee = new BigDecimal("0.00");
        for (InsuranceInHand insuranceInHand : insuranceInHandList) {
            String insuranceFeeStr = insuranceInHand.getTotalServiceFee().toString();
            BigDecimal insuranceFee = new BigDecimal(insuranceFeeStr);
            totalFee = totalFee.add(insuranceFee).setScale(2,BigDecimal.ROUND_HALF_UP);
        }
        return totalFee;
    }

    @Override
    public List<RenewalPo> insuranceRenewalList() {
        ArrayList<RenewalPo> renewalList = new ArrayList<>();
        renewalList.add( new RenewalPo(1,"新车续保"));
        renewalList.add( new RenewalPo(2,"次新车续保"));
        renewalList.add( new RenewalPo(5,"市公司续保"));
        renewalList.add( new RenewalPo(3,"转入或其他"));
        renewalList.add( new RenewalPo(4,"竞回续保"));
        renewalList.add( new RenewalPo(0,"脱保续保"));
        renewalList.add( new RenewalPo(6,"系统内续保"));
        renewalList.add( new RenewalPo(7,"支公司续保"));
        return renewalList;
    }

    @Override
    public List<InsuranceInHand> sortByInsuranceDay() {
        QueryWrapper<InsuranceInHand> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("insurance_date");
        return insuranceInHandMapper.selectList(queryWrapper);
    }

    @Override
    public List<InsuranceInHand> sortByInputTime() {
        QueryWrapper<InsuranceInHand> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return insuranceInHandMapper.selectList(queryWrapper);
    }

    @Override
    public boolean queryByCompulsoryInsurCodeOrCommercialInsurCode(InsuranceInHand insuranceInHand) {
        InsuranceInHand insurance = insuranceInHandMapper.queryByCompulsoryInsurCodeOrCommercialInsurCode(insuranceInHand.getCompulsoryInsurCode(), insuranceInHand.getCommercialInsurCode());
        if(insurance != null){
            return true;
        }
        return false;
    }

    /**
         * 返点比例计算
         *
         * @param percentList
         * @param code
         */
        private void addPercent (List<BigDecimal> percentList, Integer code, Date zbTime) throws InsuranceException{
            List<InsuranceRebateRatio> rebateRatioList = rebateRatioService.getInsuranceRebateRatioByTypeAndInsuranceDate(code,zbTime);
            if (CollectionUtils.isNotEmpty(rebateRatioList)) {
                percentList.add(rebateRatioList.get(0).getRebateRatio());
            } else {
                throw new InsuranceException();
            }
        }

        /**
         * 时间间隔
         *
         * @param companyInsurance
         * @return
         */
        private int diffDate(CompanyInsurance companyInsurance){
            Calendar registerDate = Calendar.getInstance();
            registerDate.setTime(companyInsurance.getRegisterDate());
            Calendar nowDate = Calendar.getInstance();
//            nowDate.setTime(companyInsurance.getZbDate());
            int y = DateUtils.dateDiff('y', nowDate,registerDate);
            return y;
        }
}
