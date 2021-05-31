package org.jeecg.modules.demo.rebate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.rebate.entity.InsuranceRebateRatio;

import java.util.List;

/**
 * @Description: 返点比例
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
public interface IInsuranceRebateRatioService extends IService<InsuranceRebateRatio> {

    List<InsuranceRebateRatio> getInsuranceRebateRatioByType(Integer type);
}
