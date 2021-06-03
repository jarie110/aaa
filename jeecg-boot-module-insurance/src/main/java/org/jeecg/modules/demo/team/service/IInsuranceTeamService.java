package org.jeecg.modules.demo.team.service;

import org.jeecg.modules.demo.team.entity.InsuranceTeam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 车险销售团队
 * @Author: jeecg-boot
 * @Date:   2021-06-03
 * @Version: V1.0
 */
public interface IInsuranceTeamService extends IService<InsuranceTeam> {

    String getTeamNameByCode(Integer insuranceTeam);
}
