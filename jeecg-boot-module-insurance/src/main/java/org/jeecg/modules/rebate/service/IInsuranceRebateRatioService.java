package org.jeecg.modules.rebate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.rebate.entity.InsuranceRebateRatio;
import org.jeecg.modules.rebate.entity.RebatePojo;
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

    Result<?> isAlreadyExist(RebatePojo rebatePojo);

    boolean editCheck(InsuranceRebateRatio insuranceRebateRatio);

    List<InsuranceRebateRatio> getInsuranceRebateRatioByTypeAndCarDamageInsuredIsZeroInsuranceDate(Integer type, String usageType,Date zbTime);

    List<InsuranceRebateRatio> getInsuranceRebateRatioByTypeAndCarDamageInsuredIsNotZeroInsuranceDate(Integer type, String usageType, Date zbTime);

}
