package org.jeecg.modules.usage.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.usage.entity.InsuranceUsage;

/**
 * @Description: insurance_usage
 * @Author: jeecg-boot
 * @Date:   2021-05-24
 * @Version: V1.0
 */
public interface IInsuranceUsageService extends IService<InsuranceUsage> {

    InsuranceUsage selectByUserType(String usageType);
}
