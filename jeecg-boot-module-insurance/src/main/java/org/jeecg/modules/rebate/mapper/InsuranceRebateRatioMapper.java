package org.jeecg.modules.rebate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.rebate.entity.InsuranceRebateRatio;

import java.util.Date;
import java.util.List;

/**
 * @Description: 返点比例
 * @Author: jeecg-boot
 * @Date:   2021-06-02
 * @Version: V1.0
 */
public interface InsuranceRebateRatioMapper extends BaseMapper<InsuranceRebateRatio> {

    List<InsuranceRebateRatio> selectInsuranceRebateRatioByTypeAndInsuranceDate(@Param("type") Integer type, @Param("zbTime") Date zbTime);

    InsuranceRebateRatio selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDate(@Param("type") Integer type,@Param("usageType") String usageType, @Param("zbTime") Date zbTime);

    InsuranceRebateRatio selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredZeroAndThirdPartyInsured(@Param("type") Integer type,@Param("usageType") String usageType,@Param("thirdPartyInsured") String thirdPartyInsured, @Param("zbTime") Date zbTime);

    InsuranceRebateRatio selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredNotZeroAndThirdPartyInsured(@Param("type") Integer type,@Param("usageType") String usageType,@Param("thirdPartyInsured") String thirdPartyInsured, @Param("zbTime") Date zbTime);

//    InsuranceRebateRatio selectInsuranceRebateRatioByTypeAndUsageTypeAndSeatNumAndInsuranceDate(@Param("type") Integer rebateType, @Param("usageType")String usageType, @Param("seatNum") Integer seatNum,@Param("zbTime") Date createTime);

    InsuranceRebateRatio selectByUsageTypeAndRebateType(@Param("usageType") String usageType, @Param("rebateRatioType") Integer rebateRatioType);

    InsuranceRebateRatio selectByUsageTypeAndRebateTypeAndCarDamageInsuredAndThirdPartyInsuredAndDate(@Param("usageType") String usageType, @Param("rebateRatioType") Integer rebateRatioType, @Param("carDamageInsured") String carDamageInsured, @Param("thirdPartyInsured") String thirdPartyInsured,@Param("zbTime") Date zbTime);

    InsuranceRebateRatio selectByUsageTypeAndRebateTypeAndDriverLiabilityInsuredAndPassengerLiabilityAndSeatNum(@Param("usageType") String usageType,  @Param("rebateRatioType")Integer rebateRatioType, @Param("driverLiabilityInsured") String driverLiabilityInsured, @Param("passengerLiability") String passengerLiability,@Param("seatNum") Integer seatNum);

    InsuranceRebateRatio selectByUsageTypeAndRebateTypeAndDriverLiabilityInsuredAndPassengerLiabilityAndSeatNumAndInsuranceDate(@Param("usageType") String usageType,  @Param("rebateRatioType")Integer rebateRatioType, @Param("driverLiabilityInsured") String driverLiabilityInsured, @Param("passengerLiability") String passengerLiability,@Param("seatNum") Integer seatNum, @Param("zbTime") Date zbTime);
}
