package org.jeecg.modules.demo.team.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.team.entity.InsuranceTeam;

/**
 * @Description: 车险销售团队
 * @Author: jeecg-boot
 * @Date:   2021-05-24
 * @Version: V1.0
 */
public interface IInsuranceTeamService extends IService<InsuranceTeam> {

    String getTeamNameByCode(Integer insuranceTeam);
}
