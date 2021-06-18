package org.jeecg.quartzUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.message.entity.InsuranceMessage;
import org.jeecg.modules.message.service.IInsuranceMessageService;
import org.jeecg.modules.companyInsurance.entity.CompanyInsurance;
import org.jeecg.modules.companyInsurance.service.impl.CompanyInsuranceServiceImpl;
import org.jeecg.modules.proxyInsurance.entity.InsuranceInHand;
import org.jeecg.modules.proxyInsurance.service.impl.InsuranceInHandServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Slf4j
public class InsuranceJob implements Job {
	@Autowired
	private IInsuranceMessageService messageService;

	public static final Integer DAYS = 30;

	@Autowired
	CompanyInsuranceServiceImpl companyInsuranceService;
	@Autowired
	InsuranceInHandServiceImpl insuranceInHandService;

	private String parameter;

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		List<CompanyInsurance> insurances = selectListByDateDiff();
		InsuranceMessage message = new InsuranceMessage();
		String msg = null;
		if(CollectionUtils.isNotEmpty(insurances)){
			for (CompanyInsurance insurance : insurances) {
				String insuranceNum = insurance.getInsuranceNum();
				log.info(String.format("温馨提示： %s! " + DateUtils.now(), this.parameter)+"保单号为："+ insuranceNum);
				message.setCreateTime(new Date());
//				根据保单号查询录入保单的uid，保存到message中
				QueryWrapper<InsuranceInHand> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("compulsory_insur_code",insuranceNum);
				InsuranceInHand insuranceInHand = insuranceInHandService.getOne(queryWrapper);
				if(insuranceInHand != null){
					String uid = insuranceInHand.getUid();
					msg = String.format("温馨提示："+this.parameter)+"交强险保单号为："+ insuranceNum + new Date();
					message.setMsg(msg);
					message.setUid(uid);
					messageService.save(message);
				}else {
					queryWrapper.eq("commercial_insur_code",insuranceNum);
					InsuranceInHand one = insuranceInHandService.getOne(queryWrapper);
					if(one != null){
						String uid = one.getUid();
						msg = String.format("温馨提示："+this.parameter)+"商业险保单号为："+ insuranceNum + new Date();
						message.setMsg(msg);
						message.setUid(uid);
						messageService.save(message);
					}
				}
			}
		}
	}

	private List<CompanyInsurance> selectListByDateDiff(){
		List<CompanyInsurance> insuranceList = companyInsuranceService.selectListByDateDiff(DAYS);
		return insuranceList;
	}
}