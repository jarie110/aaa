package org.jeecg.modules.companyInsurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.companyInsurance.entity.CompanyInsurance;
import org.jeecg.modules.companyInsurance.mapper.CompanyInsuranceMapper;
import org.jeecg.modules.companyInsurance.service.ICompanyInsuranceService;
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

    /**
     * 根据车架号查询
     * @param vehicleIdentity
     * @return
     */
    @Override
    public List<CompanyInsurance> getCompanyInsuranceByVehicleIdentity(String vehicleIdentity) {
        QueryWrapper<CompanyInsurance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vehicle_identity", vehicleIdentity);
        return companyInsuranceMapper.selectList(queryWrapper);
    }

    @Override
    public CompanyInsurance getInstanceByCommerialInsurCode(String commercialInsurCode) {
        QueryWrapper<CompanyInsurance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("insurance_num",commercialInsurCode);
        return companyInsuranceMapper.selectOne(queryWrapper);
    }

    @Override
    public List<CompanyInsurance> selectListByDateDiff(Integer days) {
        List<CompanyInsurance> companyInsuranceList = companyInsuranceMapper.selectListByDateDiff(days);
        return companyInsuranceList;
    }
}
