package org.jeecg.modules.rebate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.common.util.DateUtils;
import org.jeecg.enumUtil.RebateType;
import org.jeecg.modules.rebate.entity.InsuranceRebateRatio;
import org.jeecg.modules.rebate.mapper.InsuranceRebateRatioMapper;
import org.jeecg.modules.rebate.service.IInsuranceRebateRatioService;
import org.jeecg.pojo.RebatePo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
public static  Integer DRIVER_LIABILITY_INSURED = 20000;
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

    /**
     * 批量插入
     * @param rebates
     * @return
     */
    @Override
    public boolean insertBatch(List<RebatePo> rebates) {
//        String sql ="INSERT INTO insurance_rebate_ratio (`create_time`,`rebate_ratio_type`,`insurance_usage`,`third_party_insured`,`car_damage_insured`,`driver_liability_insured`,`passenger_liability`,`rebate_ratio`,`bonus`)VALUES(?,?,?,?,?,?,?,?,?)";
        ArrayList<InsuranceRebateRatio> rebateRatios = new ArrayList<>();

        for (RebatePo rebatePo : rebates) {
//            遍历传进来的集合
//            获取开始时间
            Calendar timeBegin = Calendar.getInstance();
            timeBegin.setTime(rebatePo.getCreateTimeBegin());

//            获取结束时间
            Calendar timeEnd = Calendar.getInstance();
            timeEnd.setTime(rebatePo.getCreateTimeEnd());

//            计算有多少天
            int days = DateUtils.dateDiff('d', timeBegin, timeEnd);

            Calendar createTime = Calendar.getInstance();
            Long millis = null;

            InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();

            Long oneDay = 24 * 60 * 60 * 1000l; //一天有多少秒

            for (int i = 0; i < days; i++) {
                createTime.setTime(rebatePo.getCreateTimeBegin());
                millis = DateUtils.getMillis(createTime) + oneDay * i ;
                Date date = new Date(DateUtils.formatDate(millis));
                rebateRatio.setCreateTime(date);//设置创建时间
//商业基础险
                if(rebatePo.getRebateType() == 0){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                    rebateRatio.setUsageType(rebatePo.getUsageType());
                }
//                三者险
                if(rebatePo.getRebateType() == 1 && rebatePo.getCarDamageInsuredZero().equals("0")){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                    rebateRatio.setUsageType(rebatePo.getUsageType());
                    rebateRatio.setCarDamageInsured("0");
                    rebateRatio.setThirdPartyInsured(rebatePo.getThirdPartyInsuredZero());
                }else if (rebatePo.getRebateType() == 1 && Integer.valueOf(rebatePo.getCarDamageInsuredZero()) != 0){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                    rebateRatio.setUsageType(rebatePo.getUsageType());
                    rebateRatio.setCarDamageInsured(rebatePo.getCarDamageInsured());
                    rebateRatio.setThirdPartyInsured(rebatePo.getThirdPartyInsured());
                }
//次新车返点数据
                if(rebatePo.getRebateType() == 2){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                }
//                新车
                if(rebatePo.getRebateType() == 3){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                }
//                 竞回返点比
                if(rebatePo.getRebateType() == 4){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                }

                //过户返点比
                if(rebatePo.getRebateType() == 5){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                    rebateRatio.setIsTransfer(rebatePo.getIsTransfer());
                }
                // 交叉返点比
                if(rebatePo.getRebateType() == 6){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                    rebateRatio.setIsTransfer(rebatePo.getIsTransfer());
                }
                // 跟单0返点比
                if(rebatePo.getRebateType() == 7){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                    rebateRatio.setSignFee(new BigDecimal(rebatePo.getSignFee()));
                }

                // 座位保
                if(rebatePo.getRebateType() == 8){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                    rebateRatio.setUsageType(rebatePo.getUsageType());
                    rebateRatio.setPassengerLiability(rebatePo.getPassengerLiabilityInsured());
                    rebateRatio.setBonus(rebatePo.getBonus());
                }
                // 其他
                if(rebatePo.getRebateType() == 9){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                }
//                MyBeanUtil.copyPropertiesIgnoreNull(rebatePo, rebateRatio);
                rebateRatios.add(rebateRatio);
            }
        }
        boolean save = this.saveBatch(rebateRatios);
        if(save){
            return true;
        }
        return false;
    }

    @Override
    public boolean isAlreadyExist(InsuranceRebateRatio insuranceRebateRatio) {
        //		根据出单日期、返点类型、使用性质、判断数据库是否存在
        Date createTime = insuranceRebateRatio.getCreateTime();
        Integer rebateType = insuranceRebateRatio.getRebateRatioType();

        String carDamageInsured = insuranceRebateRatio.getCarDamageInsured();
        String usageType = insuranceRebateRatio.getUsageType();

        //		商业基础险判断
//1.根据返点类型和使用性质判断
        if(usageType != null && rebateType == RebateType.COMMERCIAL_BASIC_REBATE.getType()){
            if (checkRebateIsExists(createTime, rebateType, usageType)) return true;
        }
//       三者险判断
        if(rebateType == RebateType.THIRD_PARTY_REBATE.getType() && !carDamageInsured.equals("-") && Integer.parseInt(carDamageInsured) != 0){
            InsuranceRebateRatio rebateRatioFromSql = rebateRatioMapper.selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredNotZero(rebateType,usageType,createTime);
            if(rebateRatioFromSql != null){
                return true;
            }
        }
        if(rebateType == RebateType.THIRD_PARTY_REBATE.getType() && (!carDamageInsured.equals("-") && Integer.parseInt(carDamageInsured) == 0)){
            InsuranceRebateRatio rebateRatioFromSql = rebateRatioMapper.selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredZero(rebateType,usageType,createTime);
            if(rebateRatioFromSql != null){
                return true;
            }
        }
//座位保判断
        if(rebateType == RebateType.SEAT_INSURANCE.getType()){
            InsuranceRebateRatio rebateRatioFromSql = rebateRatioMapper.selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDate(rebateType,usageType,createTime);
            if(rebateRatioFromSql != null){
                return true;
            }
        }
//        其他但返点类型判断
//        竞回返点
        if(rebateType == RebateType.COMPETITION_REBATE.getType()){
            if (checkRebateIsExits(createTime, rebateType)) return true;
        }
//跟单零
        if(rebateType == RebateType.FOLLOW_UP_REBATE.getType()){
            if (checkRebateIsExits(createTime, rebateType)) return true;
        }
//过户
        if(rebateType == RebateType.IS_TRANSFER_REBATE.getType()){
            if (checkRebateIsExits(createTime, rebateType)) return true;
        }
//        交叉
        if(rebateType == RebateType.OVERLAPPING_REBATE.getType()){
            if (checkRebateIsExits(createTime, rebateType)) return true;
        }

        //        新车
        if(rebateType == RebateType.NEW_CAR_REBATE.getType()){
            if (checkRebateIsExits(createTime, rebateType)) return true;
        }

        //        次新车
        if(rebateType == RebateType.LAST_YEAR_CAR_REBATE.getType()){
            if (checkRebateIsExits(createTime, rebateType)) return true;
        }

        //        其他
        if(rebateType == RebateType.CHANGE_INTO_INSURANCE.getType()){
            if (checkRebateIsExits(createTime, rebateType)) return true;
        }
        return false;
    }

    private boolean checkRebateIsExits(Date createTime, Integer rebateType) {
        List<InsuranceRebateRatio> rebateRatioFromSqls = rebateRatioMapper.selectInsuranceRebateRatioByTypeAndInsuranceDate(rebateType, createTime);
        if (CollectionUtils.isNotEmpty(rebateRatioFromSqls)) {
            return true;
        }
        return false;
    }

    private boolean checkRebateIsExists(Date createTime, Integer rebateType, String usageType) {
        InsuranceRebateRatio rebateRatioFromSql = rebateRatioMapper.selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDate(rebateType,usageType,createTime);
        if(rebateRatioFromSql != null){
            return true;
        }
        return false;
    }
}

