package org.jeecg.modules.rebate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.BeanUtils.MyBeanUtil;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.rebate.entity.InsuranceRebateRatio;
import org.jeecg.modules.rebate.mapper.InsuranceRebateRatioMapper;
import org.jeecg.modules.rebate.service.IInsuranceRebateRatioService;
import org.jeecg.pojo.RebatePo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description: 返点比例
 * @Author: jeecg-boot
 * @Date:   2021-06-02
 * @Version: V1.0
 */
@Service
public class InsuranceRebateRatioServiceImpl extends ServiceImpl<InsuranceRebateRatioMapper, InsuranceRebateRatio> implements IInsuranceRebateRatioService {

   @Resource
    private InsuranceRebateRatioMapper rebateRatioMapper;

    /**
     * 根据类型code和时间查询返点类型
      * @param type
     * @param zbTime
     * @return
     */
   @Override
    public List<InsuranceRebateRatio> getInsuranceRebateRatioByTypeAndInsuranceDate(Integer type, Date zbTime) {
        return rebateRatioMapper.selectInsuranceRebateRatioByTypeAndInsuranceDate(type,zbTime);
    }

    @Override
    public InsuranceRebateRatio getInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDate(Integer type, String usageType, Date zbTime) {
        return rebateRatioMapper.selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDate(type,usageType,zbTime);
    }

    @Override
    public boolean insertBatch(List<RebatePo> rebates) {
//        String sql ="INSERT INTO insurance_rebate_ratio (`create_time`,`rebate_ratio_type`,`insurance_usage`,`third_party_insured`,`car_damage_insured`,`driver_liability_insured`,`passenger_liability`,`rebate_ratio`,`bonus`)VALUES(?,?,?,?,?,?,?,?,?)";
        ArrayList<InsuranceRebateRatio> rebateRatios = new ArrayList<>();
        for (RebatePo rebate : rebates) {
            Calendar timeBegin = Calendar.getInstance();
            timeBegin.setTime(rebate.getCreateTimeBegin());

            Calendar timeEnd = Calendar.getInstance();
            timeEnd.setTime(rebate.getCreateTimeEnd());

            int days = DateUtils.dateDiff('d', timeBegin, timeEnd);
            Calendar createTime = Calendar.getInstance();
            Long millis = null;

            InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();

            Long oneDay = 24 * 60 * 60 * 1000l;

            for (int i = 0; i < days; i++) {
                createTime.setTime(rebate.getCreateTime());
                millis = DateUtils.getMillis(createTime) + oneDay ;
                Date date = new Date(DateUtils.formatDate(millis));
                rebate.setCreateTime(date);
                MyBeanUtil.copyPropertiesIgnoreNull(rebate, rebateRatio);
                rebateRatios.add(rebateRatio);
            }
        }
        boolean save = this.saveBatch(rebateRatios);
        if(save){
            return true;
        }
        return false;
    }
}

