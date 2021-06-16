package org.jeecg.modules.demo.companyInsurance.service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.companyInsurance.entity.CompanyInsurance;

import java.util.List;

/**
 * @Description: 保险公司保单
 * @Author: jeecg-boot
 * @Date:   2021-05-27
 * @Version: V1.0
 */
public interface ICompanyInsuranceService extends IService<CompanyInsurance> {

    List<CompanyInsurance> getCompanyInsuranceByVehicleIdentity(String vehicleIdentity);

    CompanyInsurance getInstanceByCommerialInsurCode(String commercialInsurCode);

    List<CompanyInsurance> selectListByDateDiff(Integer days);
}
