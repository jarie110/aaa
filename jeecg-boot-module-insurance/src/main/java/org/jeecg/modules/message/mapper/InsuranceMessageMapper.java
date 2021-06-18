package org.jeecg.modules.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.message.entity.InsuranceMessage;

import java.util.Date;
import java.util.List;

/**
 * @Description: 保单消息
 * @Author: jeecg-boot
 * @Date:   2021-06-18
 * @Version: V1.0
 */
public interface InsuranceMessageMapper extends BaseMapper<InsuranceMessage> {

    List<InsuranceMessage> findByDayAndUid(@Param("uid") String uid, @Param("createDate") Date createDate);
}
