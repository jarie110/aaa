package org.jeecg.modules.message.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.message.entity.InsuranceMessage;

import java.util.Date;
import java.util.List;

/**
 * @Description: 保单消息
 * @Author: jeecg-boot
 * @Date:   2021-06-18
 * @Version: V1.0
 */
public interface IInsuranceMessageService extends IService<InsuranceMessage> {

    List<InsuranceMessage> findByDayAndUid(String uid, Date createDate);
}
