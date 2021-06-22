package org.jeecg.modules.proxyInsurance.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.proxyInsurance.entity.InsuranceInHand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 手输保单
 * @Author: jeecg-boot
 * @Date:   2021-06-09
 * @Version: V1.0
 */
public interface InsuranceInHandMapper extends BaseMapper<InsuranceInHand> {

    InsuranceInHand queryByCompulsoryInsurCodeOrCommercialInsurCode(@Param("compulsoryInsurCode") String compulsoryInsurCode, @Param("commercialInsurCode") String commercialInsurCode);
}
