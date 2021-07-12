package org.jeecg.modules.companyInsurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.handler.InsuranceHandler;
import org.jeecg.modules.companyInsurance.entity.CompanyInsurance;
import org.jeecg.modules.companyInsurance.mapper.CompanyInsuranceMapper;
import org.jeecg.modules.companyInsurance.service.ICompanyInsuranceService;
import org.jeecg.modules.proxyInsurance.entity.InsuranceInHand;
import org.jeecg.modules.proxyInsurance.mapper.InsuranceInHandMapper;
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
public class CompanyInsuranceServiceImpl extends ServiceImpl<CompanyInsuranceMapper, CompanyInsurance> implements ICompanyInsuranceService, InsuranceHandler {

    @Autowired
    private CompanyInsuranceMapper companyInsuranceMapper;

    @Autowired
    private InsuranceInHandMapper insuranceInHandMapper;
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

    /**
     * 导入表格式判断数据是否重复，如果重复则取出重复数据的保单号
     * @param obj
     */
    public boolean isExistAndSave(Object obj,Integer code) {
        boolean flag = false;
        if(obj instanceof CompanyInsurance && code == 1){
            CompanyInsurance companyInsurance = (CompanyInsurance)obj;
            QueryWrapper<CompanyInsurance> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("insurance_num",companyInsurance.getInsuranceNum());
            List<CompanyInsurance> insurances = companyInsuranceMapper.selectList(queryWrapper);
            if(insurances.size() != 0){
//            数据库已有数据返回false
                flag = true;
            }else{
                this.save(companyInsurance);
                flag = false;
            }
        }
        if(obj instanceof InsuranceInHand && code == 2){
            InsuranceInHand insuranceInHand = (InsuranceInHand)obj;
            String commercialInsurCode = insuranceInHand.getCommercialInsurCode();
            String compulsoryInsurCode = insuranceInHand.getCompulsoryInsurCode();
            InsuranceInHand insurance = insuranceInHandMapper.queryByCompulsoryInsurCodeOrCommercialInsurCode(compulsoryInsurCode,commercialInsurCode);
            if(insurance != null){
//            数据库已有数据返回false
                flag = true;
            }else{
                insuranceInHandMapper.insert(insuranceInHand);
                flag = false;
            }
        }

        return flag;
    }
}
