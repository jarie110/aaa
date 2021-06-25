package org.jeecg.modules.rebate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.rebate.entity.InsuranceRebateRatio;
import org.jeecg.pojo.RebatePo;

import java.util.Date;
import java.util.List;

/**
 * @Description: 返点比例
 * @Author: jeecg-boot
 * @Date:   2021-06-02
 * @Version: V1.0
 */
public interface IInsuranceRebateRatioService extends IService<InsuranceRebateRatio> {

    List<InsuranceRebateRatio> getInsuranceRebateRatioByTypeAndInsuranceDate(Integer code, Date zbTime);

    InsuranceRebateRatio getInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDate(Integer type, String usageType, Date zbTime);
    boolean insertBatch(List<RebatePo> rebates);

    boolean isAlreadyExist(InsuranceRebateRatio insuranceRebateRatio);

    boolean editCheck(InsuranceRebateRatio insuranceRebateRatio);
}
