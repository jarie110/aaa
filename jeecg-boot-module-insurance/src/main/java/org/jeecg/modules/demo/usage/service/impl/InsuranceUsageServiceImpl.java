package org.jeecg.modules.demo.usage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.usage.entity.InsuranceUsage;
import org.jeecg.modules.demo.usage.mapper.InsuranceUsageMapper;
import org.jeecg.modules.demo.usage.service.IInsuranceUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: insurance_usage
 * @Author: jeecg-boot
 * @Date:   2021-05-24
 * @Version: V1.0
 */
@Service
public class InsuranceUsageServiceImpl extends ServiceImpl<InsuranceUsageMapper, InsuranceUsage> implements IInsuranceUsageService {
    @Autowired
    private InsuranceUsageMapper usageMapper;
    @Override
    public InsuranceUsage selectByUserType(String usageType) {
        QueryWrapper<InsuranceUsage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("usage_type",usageType);
        return usageMapper.selectOne(queryWrapper);
    }

}
