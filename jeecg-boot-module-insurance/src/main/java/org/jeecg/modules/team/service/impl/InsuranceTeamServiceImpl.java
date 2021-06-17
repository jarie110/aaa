package org.jeecg.modules.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.team.entity.InsuranceTeam;
import org.jeecg.modules.team.mapper.InsuranceTeamMapper;
import org.jeecg.modules.team.service.IInsuranceTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 车险销售团队
 * @Author: jeecg-boot
 * @Date:   2021-05-24
 * @Version: V1.0
 */
@Service
public class InsuranceTeamServiceImpl extends ServiceImpl<InsuranceTeamMapper, InsuranceTeam> implements IInsuranceTeamService {

    @Autowired
    private InsuranceTeamMapper teamMapper;
    @Override
    public String getTeamNameByCode(Integer code) {

        QueryWrapper<InsuranceTeam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("team_code",code);
        return teamMapper.selectOne(queryWrapper).getTeamName();
    }
}
