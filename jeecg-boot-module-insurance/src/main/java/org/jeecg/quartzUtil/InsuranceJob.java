package org.jeecg.quartzUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.companyInsurance.entity.CompanyInsurance;
import org.jeecg.modules.companyInsurance.service.impl.CompanyInsuranceServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class InsuranceJob implements Job {
	public static final Integer DAYS = 30;

	@Autowired
	CompanyInsuranceServiceImpl companyInsuranceService;

	private String parameter;

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		if(CollectionUtils.isNotEmpty(selectListByDateDiff())){
			log.info(String.format("温馨提示： %s! " + DateUtils.now(), this.parameter));
		}
	}

	private List<CompanyInsurance> selectListByDateDiff(){
		List<CompanyInsurance> insuranceList = companyInsuranceService.selectListByDateDiff(DAYS);
		return insuranceList;
	}
}