package org.jeecg.modules.demo.companyInsurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.companyInsurance.entity.CompanyInsurance;
import org.jeecg.modules.demo.companyInsurance.mapper.CompanyInsuranceMapper;
import org.jeecg.modules.demo.companyInsurance.service.ICompanyInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 保险公司保单
 * @Author: jeecg-boot
 * @Date: 2021-05-27
 * @Version: V1.0
 */
@Service
public class CompanyInsuranceServiceImpl extends ServiceImpl<CompanyInsuranceMapper, CompanyInsurance> implements ICompanyInsuranceService {

    @Autowired
    private CompanyInsuranceMapper companyInsuranceMapper;

    @Override
    public List<CompanyInsurance> getCompanyInsuranceByVehicleIdentity(String vehicleIdentity) {
        QueryWrapper<CompanyInsurance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vehicle_identity", vehicleIdentity);
        return companyInsuranceMapper.selectList(queryWrapper);

    }
}
