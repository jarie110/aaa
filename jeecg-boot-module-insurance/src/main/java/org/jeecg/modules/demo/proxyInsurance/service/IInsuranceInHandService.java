package org.jeecg.modules.demo.proxyInsurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.proxyInsurance.entity.InsuranceInHand;

/**
 * @Description: 录入的保单
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
public interface IInsuranceInHandService extends IService<InsuranceInHand> {
    void setAllArgs(InsuranceInHand insuranceInHand);
}
