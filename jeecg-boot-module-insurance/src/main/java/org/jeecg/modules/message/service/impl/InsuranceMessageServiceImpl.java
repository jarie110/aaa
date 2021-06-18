package org.jeecg.modules.message.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.message.entity.InsuranceMessage;
import org.jeecg.modules.message.mapper.InsuranceMessageMapper;
import org.jeecg.modules.message.service.IInsuranceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 保单消息
 * @Author: jeecg-boot
 * @Date:   2021-06-18
 * @Version: V1.0
 */
@Service
public class InsuranceMessageServiceImpl extends ServiceImpl<InsuranceMessageMapper, InsuranceMessage> implements IInsuranceMessageService {
    @Autowired
    private InsuranceMessageMapper messageMapper;
    @Override
    public List<InsuranceMessage> findByDayAndUid(String uid, Date createDate) {
        return messageMapper.findByDayAndUid(uid,createDate);
    }
}
