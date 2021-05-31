package org.jeecg.modules.demo.proxyInsurance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.common.util.DateUtils;
import org.jeecg.enumUtil.IsTransfer;
import org.jeecg.enumUtil.RebateType;
import org.jeecg.enumUtil.RenewalTypeEnum;
import org.jeecg.modules.demo.proxyInsurance.entity.InsuranceInHand;
import org.jeecg.modules.demo.proxyInsurance.mapper.InsuranceInHandMapper;
import org.jeecg.modules.demo.proxyInsurance.service.IInsuranceInHandService;
import org.jeecg.modules.demo.rebate.entity.InsuranceRebateRatio;
import org.jeecg.modules.demo.rebate.service.IInsuranceRebateRatioService;
import org.jeecg.modules.demo.supplemental.service.ISupplementalInsuranceService;
import org.jeecg.modules.demo.usage.entity.InsuranceUsage;
import org.jeecg.modules.demo.usage.service.IInsuranceUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public final Integer FIXED_VALUE = 20000;//司机责任险最小值
    @Autowired
    private IInsuranceUsageService usageService;

    @Autowired
    private ISupplementalInsuranceService supplementalInsuranceService;

    @Autowired
    private IInsuranceRebateRatioService rebateRatioService;

    /**
     * 计算总返点比例
     *
     * @param insuranceInHand
     */
    @Transactional
    @Override
    public void setAllArgs(InsuranceInHand insuranceInHand) {
        /*设置商业险返点佣金*/
        List<Double> percentList = new ArrayList<>();
        //		1.使用性质（根据使用性质类型，查询对应的返点比例）
        Integer usageType = insuranceInHand.getCarUsageType();
        InsuranceUsage insuranceUsage = usageService.selectByUserType(usageType);
        Double usagePercent = insuranceUsage.getUsagePercent();
        percentList.add(usagePercent);

//        2.获取用户的三者险保额、车损险保额，司机责任险保额，乘客责任险保额
        Integer thirdPartyInsured = insuranceInHand.getThirdPartyInsured();//三者险保额
        Integer carDamageInsured = insuranceInHand.getCarDamageInsured();//车损险保额
        Integer driverLiabilityInsured = insuranceInHand.getDriverLiabilityInsure();//司机责任险保额
        Integer passengerLiabilityInsured = insuranceInHand.getPassengerLiability();//乘客责任险保额
//		三者保额比例（使用性质，三者保额，车损保额是否达标）
        List<InsuranceRebateRatio> insuranceRebateRatioList = rebateRatioService.getInsuranceRebateRatioByType(RebateType.THIRD_PARTY_REBATE.getType());
        ArrayList<Integer> insureds = new ArrayList<>();
        Double thirdPartyRebate = 0.0;
        for (InsuranceRebateRatio insuranceRebateRatio : insuranceRebateRatioList) {
//            集合装所有查询到的保额
            String rebateThirdPatyInsured = insuranceRebateRatio.getThirdPartyInsured();
            if (!rebateThirdPatyInsured.equals("-")) {
                insureds.add(Integer.valueOf(rebateThirdPatyInsured));
            }
        }
        //输入保单的三者保额小于最小保额
        if (thirdPartyInsured != null && thirdPartyInsured < Collections.min(insureds)) {
            for (InsuranceRebateRatio insuranceRebateRatio : insuranceRebateRatioList) {
                if (!insuranceRebateRatio.getThirdPartyInsured().equals("-") && Integer.valueOf(insuranceRebateRatio.getThirdPartyInsured()) == Collections.min(insureds)) {
                    thirdPartyRebate = insuranceRebateRatio.getRebateRatio();
                }
            }
        }
        //保单的三者保额大于或等于最大保额,且车损险为0
        if (carDamageInsured == 0 && thirdPartyInsured >= Collections.max(insureds)) {
            for (InsuranceRebateRatio insuranceRebateRatio : insuranceRebateRatioList) {
                if (!insuranceRebateRatio.getCarDamageInsured().equals("-") && Integer.valueOf(insuranceRebateRatio.getCarDamageInsured()) == 0 && Integer.valueOf(insuranceRebateRatio.getThirdPartyInsured()) == Collections.max(insureds)) {
                    thirdPartyRebate = insuranceRebateRatio.getRebateRatio();
                }
            }
        }
        //保单的三者保额大于或等于最大保额,且车损险不为0
        if (carDamageInsured != 0 && thirdPartyInsured >= Collections.max(insureds)) {
            for (InsuranceRebateRatio insuranceRebateRatio : insuranceRebateRatioList) {
                if (!insuranceRebateRatio.getCarDamageInsured().equals("-") && Integer.valueOf(insuranceRebateRatio.getCarDamageInsured()) != 0 && Integer.valueOf(insuranceRebateRatio.getThirdPartyInsured()) == Collections.max(insureds)) {
                    thirdPartyRebate = insuranceRebateRatio.getRebateRatio();
                }
            }
        }
        percentList.add(thirdPartyRebate);
//		3.判断该车是否为新车标志
        Integer renewalType = insuranceInHand.getRenewalType();

//        判断时间
        int y = diffDate(insuranceInHand);

        if (renewalType == RenewalTypeEnum.NEW_CAR_RENEWAL.getType() && y <= 1) {
//            新车返点比
            addPercent(percentList, RebateType.NEW_CAR_REBATE.getType());
        } else if (renewalType == RenewalTypeEnum.LAST_YEAR_RENEWAL.getType() && y > 1 && y <= 2) {
//            次新车返点比
            addPercent(percentList, RebateType.LAST_YEAR_CAR_REBATE.getType());
        }
//		4.判断该车是否为竞回标志
        if (renewalType == RenewalTypeEnum.COMPETITION_RENEWAL.getType()) {
//            竞回返点比
            addPercent(percentList, RebateType.COMPETITION_REBATE.getType());
        }
//		5.判断该车是否过户
        if (insuranceInHand.getIsTransfer() == IsTransfer.TRANSFER.getType()) {
//            已过户
            addPercent(percentList, RebateType.IS_TRANSFER_REBATE.getType());
        }
//		6.判断该车是否为交叉比例
//        1.市公司续保
//        2.未过户
//        3.不是新车和次新车
        if (renewalType == RenewalTypeEnum.COMPANY_RENEWAL.getType()
                && insuranceInHand.getIsTransfer() == IsTransfer.NO_TRANSFER.getType()
                && y > 2
        ) {
            addPercent(percentList, RebateType.OVERLAPPING_REBATE.getType());
        }
//		7.判断是否为跟单0比例(签单手续费为0)
        if (insuranceInHand.getSignFee() == 0) {
            addPercent(percentList, RebateType.FOLLOW_UP_REBATE.getType());
        }
//		8.判断是否为座位保（使用性质，司机责任保额，乘客责任保额，是否达标）
        Integer seatsNum = insuranceInHand.getSeatsNum();//获取车座位数
        double bonus = 0;
        if (driverLiabilityInsured >= FIXED_VALUE
                && passengerLiabilityInsured > (seatsNum - 1) * driverLiabilityInsured) {
            List<InsuranceRebateRatio> rebateRatioList = rebateRatioService.getInsuranceRebateRatioByType(RebateType.SEAT_INSURANCE.getType());
            for (InsuranceRebateRatio rebateRatio : rebateRatioList) {
                if (!rebateRatio.getUsageType().equals("-") && usageType == Integer.valueOf(rebateRatio.getUsageType())) {
                    //座位保奖金
                    bonus = rebateRatio.getBonus();
                    insuranceInHand.setSeatBonus(bonus);
                }
            }
        }
//        计算返点比例总和
//        Double rebateAll = percentList.stream().collect(Collectors.summingDouble(Double::doubleValue));
        Double rebateAll = 0.0;
        for (Double aDouble : percentList) {
            rebateAll += aDouble;
        }
        insuranceInHand.setCommercialInsuranceRebate(rebateAll);



        /* 计算总保费 = 交强险 + 商业险 + 车船税*/
        Double commercialInsurFee = insuranceInHand.getCommercialInsurFee();
        Double compulsoryInsurFee = insuranceInHand.getCompulsoryInsurFee();
        Double vehicleVesselTax = insuranceInHand.getVehicleVesselTax();
        insuranceInHand.setInsuranceTotalFee(commercialInsurFee + compulsoryInsurFee + vehicleVesselTax);

        /* 计算返点佣金 = 商业险 * 商业险返点 + 交强险 * 交强险返点比 + 座位保奖金*/
        double totalServiceFee = commercialInsurFee * (rebateAll / 100) + compulsoryInsurFee * (insuranceInHand.getCompulsoryInsuranceRebate() / 100) + bonus;
        insuranceInHand.setTotalServiceFee(totalServiceFee);

    }

    /**
     * 返点比例计算
     *
     * @param percentList
     * @param code
     */
    private void addPercent(List<Double> percentList, Integer code) {
        List<InsuranceRebateRatio> rebateRatioList = rebateRatioService.getInsuranceRebateRatioByType(code);
        if (CollectionUtils.isNotEmpty(rebateRatioList)) {
            percentList.add(rebateRatioList.get(0).getRebateRatio());
        }
    }

    /**
     * 时间间隔
     *
     * @param insuranceInHand
     * @return
     */
    private int diffDate(InsuranceInHand insuranceInHand) {
        Calendar registerDate = Calendar.getInstance();
        registerDate.setTime(insuranceInHand.getInsuranceDate());
        Calendar nowDate = Calendar.getInstance();
        nowDate.setTime(new Date());
        int y = DateUtils.dateDiff('y', registerDate, nowDate);
        return y;
    }
}
