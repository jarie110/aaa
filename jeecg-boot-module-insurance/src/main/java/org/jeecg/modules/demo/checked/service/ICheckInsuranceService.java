package org.jeecg.modules.demo.checked.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.demo.checked.entity.CheckInsurance;
import org.jeecg.modules.demo.proxyInsurance.entity.InsuranceInHand;

import java.util.List;

/**
 * @Description: 核对的保单
 * @Author: jeecg-boot
 * @Date:   2021-05-27
 * @Version: V1.0
 */
public interface ICheckInsuranceService extends IService<CheckInsurance> {

    Result<?> checkAndSaveInsuracne(InsuranceInHand insuranceInHand);

    CheckInsurance selectByCommercialInsurCode(String commercialInsurCode);
    List<CheckInsurance> sortByInsuranceDay();

    List<CheckInsurance> sortByInputTime();
}
