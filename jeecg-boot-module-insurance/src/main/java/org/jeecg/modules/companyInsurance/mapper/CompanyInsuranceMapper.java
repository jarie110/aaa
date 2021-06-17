package org.jeecg.modules.companyInsurance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.companyInsurance.entity.CompanyInsurance;

import java.util.List;

/**
 * @Description: 保险公司保单
 * @Author: jeecg-boot
 * @Date:   2021-06-04
 * @Version: V1.0
 */
public interface CompanyInsuranceMapper extends BaseMapper<CompanyInsurance> {

    List<CompanyInsurance> selectListByDateDiff(@Param("days") Integer days);
}
