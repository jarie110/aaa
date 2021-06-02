package org.jeecg.modules.demo.rebate.service;

import org.jeecg.modules.demo.rebate.entity.InsuranceRebateRatio;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 返点比例
 * @Author: jeecg-boot
 * @Date:   2021-06-02
 * @Version: V1.0
 */
public interface IInsuranceRebateRatioService extends IService<InsuranceRebateRatio> {

    List<InsuranceRebateRatio> getInsuranceRebateRatioByType(Integer type);
}
