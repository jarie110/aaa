package org.jeecg.modules.salesChannel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.salesChannel.entity.DistributionChannel;
import org.jeecg.modules.salesChannel.mapper.DistributionChannelMapper;
import org.jeecg.modules.salesChannel.service.IDistributionChannelService;
import org.springframework.stereotype.Service;

/**
 * @Description: 渠道表
 * @Author: jeecg-boot
 * @Date:   2021-05-24
 * @Version: V1.0
 */
@Service
public class DistributionChannelServiceImpl extends ServiceImpl<DistributionChannelMapper, DistributionChannel> implements IDistributionChannelService {

    @Override
    public boolean isExist(DistributionChannel distributionChannel) {
        QueryWrapper<DistributionChannel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("channel_type",distributionChannel.getChannelType());
        DistributionChannel channel = this.getOne(queryWrapper);
        if(channel != null){
            return true;
        }
        return false;
    }
}
