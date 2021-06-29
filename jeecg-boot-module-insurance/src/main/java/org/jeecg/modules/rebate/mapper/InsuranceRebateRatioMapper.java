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

    InsuranceRebateRatio selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredZeroAndThirdPartyInsured(@Param("type") Integer type,@Param("usageType") String usageType,@Param("thirdPartyInsuredStart") String thirdPartyInsuredStart, @Param("thirdPartyInsuredEnd") String thirdPartyInsuredEnd, @Param("zbTime") Date zbTime);

    InsuranceRebateRatio selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredNotZeroAndThirdPartyInsured(@Param("type") Integer type,@Param("usageType") String usageType,@Param("thirdPartyInsuredStart") String thirdPartyInsuredStart, @Param("thirdPartyInsuredEnd") String thirdPartyInsuredEnd,@Param("zbTime") Date zbTime);

    InsuranceRebateRatio selectByUsageTypeAndRebateType(@Param("usageType") String usageType, @Param("rebateRatioType") Integer rebateRatioType);

    InsuranceRebateRatio selectByUsageTypeAndRebateTypeAndCarDamageInsuredAndThirdPartyInsuredAndDate(@Param("usageType") String usageType, @Param("rebateRatioType") Integer rebateRatioType, @Param("carDamageInsured") String carDamageInsured, @Param("thirdPartyInsuredStart") String thirdPartyInsuredStart, @Param("thirdPartyInsuredEnd") String thirdPartyInsuredEnd,@Param("zbTime") Date zbTime);


    InsuranceRebateRatio selectByUsageTypeAndRebateTypeAndInsuranceDate(@Param("usageType") String usageType,  @Param("rebateRatioType")Integer rebateRatioType,@Param("zbTime") Date zbTime);

    List<InsuranceRebateRatio> selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredZero(@Param("type")Integer type,@Param("usageType") String usageType, @Param("zbTime")Date zbTime);

    List<InsuranceRebateRatio> selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredNotZero(@Param("type")Integer type,@Param("usageType") String usageType, @Param("zbTime")Date zbTime);
}
