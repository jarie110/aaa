package org.jeecg.modules.checked.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.checked.entity.CheckInsurance;
import org.jeecg.modules.proxyInsurance.entity.InsuranceInHand;

import java.util.List;

/**
 * @Description: 核对的保单
 * @Author: jeecg-boot
 * @Date:   2021-05-27
 * @Version: V1.0
 */
public interface ICheckInsuranceService extends IService<CheckInsurance> {

   boolean checkAndSaveInsuracne(InsuranceInHand insuranceInHand);

    CheckInsurance selectByVehicleIdentity(String VehicleIdentity);
    List<CheckInsurance> sortByInsuranceDay();

    List<CheckInsurance> sortByInputTime();

    boolean changeInsuranceInHand(String id);

    List<String> changeInsuranceInHandBatch(List<String> idList);
}
