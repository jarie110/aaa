package org.jeecg.modules.demo.rebate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.demo.rebate.entity.InsuranceRebateRatio;
import org.jeecg.modules.demo.rebate.mapper.InsuranceRebateRatioMapper;
import org.jeecg.modules.demo.rebate.service.IInsuranceRebateRatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 返点比例
 * @Author: jeecg-boot
 * @Date:   2021-06-02
 * @Version: V1.0
 */
@Service
public class InsuranceRebateRatioServiceImpl extends ServiceImpl<InsuranceRebateRatioMapper, InsuranceRebateRatio> implements IInsuranceRebateRatioService {

   @Resource
    private InsuranceRebateRatioMapper rebateRatioMapper;
    @Override
    public List<InsuranceRebateRatio> getInsuranceRebateRatioByType(Integer type) {
        QueryWrapper<InsuranceRebateRatio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rebate_ratio_type",type);
        return rebateRatioMapper.selectList(queryWrapper);
    }
}
