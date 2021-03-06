package org.jeecg.modules.salesChannel.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.salesChannel.entity.DistributionChannel;

/**
 * @Description: 渠道表
 * @Author: jeecg-boot
 * @Date:   2021-05-24
 * @Version: V1.0
 */
public interface IDistributionChannelService extends IService<DistributionChannel> {

    boolean isExist(DistributionChannel distributionChannel);
}
