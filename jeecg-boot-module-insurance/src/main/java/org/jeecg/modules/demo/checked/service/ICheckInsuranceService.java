package org.jeecg.modules.demo.checked.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.checked.entity.CheckInsurance;
import org.jeecg.modules.demo.proxyInsurance.entity.InsuranceInHand;

/**
 * @Description: 核对的保单
 * @Author: jeecg-boot
 * @Date:   2021-05-27
 * @Version: V1.0
 */
public interface ICheckInsuranceService extends IService<CheckInsurance> {

    int checkAndSaveInsuracne(InsuranceInHand insuranceInHand);

}
