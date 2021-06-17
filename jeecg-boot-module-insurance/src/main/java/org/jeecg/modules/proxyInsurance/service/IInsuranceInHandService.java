package org.jeecg.modules.proxyInsurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.proxyInsurance.entity.InsuranceInHand;
import org.jeecg.modules.proxyInsurance.entity.RenewalPo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

/**
 * @Description: 录入的保单
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
public interface IInsuranceInHandService extends IService<InsuranceInHand> {
    Result<?> serviceTotalFee(InsuranceInHand insuranceInHand) throws ParseException;
    boolean isEquals(InsuranceInHand OldInsuranceInHand);
    int queryByPaid(String uid);

    int countByUser(String uid);

    BigDecimal totalInsuranceFee(String uid);

    BigDecimal totalInsurancePaidFee(String uid);

    int queryByIsCheck(String uid);

    List<RenewalPo> insuranceRenewalList();

    List<InsuranceInHand> sortByInsuranceDay();

    List<InsuranceInHand> sortByInputTime();
}
