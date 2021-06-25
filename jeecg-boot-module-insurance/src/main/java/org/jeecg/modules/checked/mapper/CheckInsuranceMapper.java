package org.jeecg.modules.checked.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.checked.entity.CheckInsurance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 核对的保单
 * @Author: jeecg-boot
 * @Date:   2021-06-04
 * @Version: V1.0
 */
public interface CheckInsuranceMapper extends BaseMapper<CheckInsurance> {

    CheckInsurance selectByVehicleIdAndCommercialInsurCodeAndCompulsoryInsurCode(@Param("vehicleIdentity") String vehicleIdentity, @Param("compulsoryInsurCode") String compulsoryInsurCode, @Param("commercialInsurCode") String commercialInsurCode);
}
