package org.jeecg.modules.demo.proxyInsurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.demo.proxyInsurance.entity.InsuranceInHand;

import java.text.ParseException;

/**
 * @Description: 录入的保单
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
public interface IInsuranceInHandService extends IService<InsuranceInHand> {
    Result<?> serviceTotalFee(InsuranceInHand insuranceInHand) throws ParseException;
    boolean isEquals(InsuranceInHand OldInsuranceInHand);
}
