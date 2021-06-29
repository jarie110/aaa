package org.jeecg.modules.rebate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.BeanUtils.MyBeanUtil;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.DateUtils;
import org.jeecg.enumUtil.RebateType;
import org.jeecg.modules.rebate.entity.InsuranceRebateRatio;
import org.jeecg.modules.rebate.entity.RebatePojo;
import org.jeecg.modules.rebate.entity.Region;
import org.jeecg.modules.rebate.mapper.InsuranceRebateRatioMapper;
import org.jeecg.modules.rebate.service.IInsuranceRebateRatioService;
import org.jeecg.pojo.RebatePo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
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

    /**
     * 批量插入
     * @param rebates
     * @return
     */
    @Override
    public boolean insertBatch(List<RebatePo> rebates) {
        ArrayList<InsuranceRebateRatio> rebateRatios = new ArrayList<>();
        Date timeStart = null;
        Date timeEnd = null;
        for (RebatePo rebatePo : rebates) {
            try {
               timeStart = DateUtils.parseDate(rebatePo.getCreateTimeBegin(),"yyyy-MM-dd");
               timeEnd = DateUtils.parseDate(rebatePo.getCreateTimeEnd(),"yyyy-MM-dd");

            } catch (ParseException e) {
                e.printStackTrace();
            }
//            遍历传进来的集合
//            获取开始时间
            Calendar timeBegin = Calendar.getInstance();
            timeBegin.setTime(timeStart);

//            获取结束时间
            Calendar timeOff = Calendar.getInstance();
            timeOff.setTime(timeEnd);

//            计算有多少天
            int days = DateUtils.dateDiff('d', timeOff,timeBegin);

            Calendar createTime = Calendar.getInstance();
            Long millis = null;

            InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();

            Long oneDay = 24 * 60 * 60 * 1000l; //一天有多少秒

            for (int i = 0; i < days; i++) {
                createTime.setTime(timeStart);
                millis = DateUtils.getMillis(createTime) + oneDay * i ;
                Date date = DateUtils.getDate(millis);
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
//                    rebateRatio.setThirdPartyInsured(rebatePo.getThirdPartyInsuredZero());
                }else if (rebatePo.getRebateType() == 1 && Integer.valueOf(rebatePo.getCarDamageInsuredZero()) != 0){
                    rebateRatio.setRebateRatioType(rebatePo.getRebateType());
                    rebateRatio.setRebateRatio(rebatePo.getRebateRatio());
                    rebateRatio.setUsageType(rebatePo.getUsageType());
                    rebateRatio.setCarDamageInsured(rebatePo.getCarDamageInsured());
//                    rebateRatio.setThirdPartyInsured(rebatePo.getThirdPartyInsured());
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
    public Result<?> isAlreadyExist(RebatePojo rebatePojo) {
        //		根据出单日期、返点类型、使用性质、判断数据库是否存在
        Date createTime = rebatePojo.getCreateTime();
        Integer rebateType = rebatePojo.getRebateRatioType();
        ArrayList<InsuranceRebateRatio> rebateRatioList = new ArrayList<>();
        String carDamageInsured = rebatePojo.getCarDamageInsured();//车损险
        List<Region> thirdPartyInsureds = rebatePojo.getRegions();//三者险
        String usageTypes = rebatePojo.getUsageType();
        String[] usages = usageTypes.split(",");
        for (String usage : usages) {
            //		商业基础险判断
//1.根据返点类型和使用性质判断
            if(usage != null && rebateType == RebateType.COMMERCIAL_BASIC_REBATE.getType()){
                InsuranceRebateRatio rebateRatioFromSql = checkRebateIsExists(createTime, rebateType, usage);
                if (rebateRatioFromSql == null) {//数据库中没有数据
                    InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                    MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                    rebateRatio.setUsageType(usage);
                    rebateRatioList.add(rebateRatio);
                }
            }

//       三者险判断
//            1.车损险是否为0

//                遍历三者险的区间集合
        for (Region region : thirdPartyInsureds) {
             if(rebateType == RebateType.THIRD_PARTY_REBATE.getType() && !carDamageInsured.equals("-") && Integer.parseInt(carDamageInsured) != 0){
                InsuranceRebateRatio rebateRatioFromSql = rebateRatioMapper.selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredNotZeroAndThirdPartyInsured(rebateType,usage,region.getThirdPartyInsuredStart(),region.getThirdPartyInsuredEnd(),createTime);
                if (rebateRatioFromSql == null) {//数据库中没有数据
                    InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                    MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                    rebateRatio.setRebateRatio(new BigDecimal(region.getRebateRatioThird()));//获取返点比例
                    rebateRatio.setThirdPartyInsuredStart(region.getThirdPartyInsuredStart());
                    rebateRatio.setThirdPartyInsuredEnd(region.getThirdPartyInsuredEnd());
                    rebateRatio.setUsageType(usage);
                    rebateRatioList.add(rebateRatio);
                }
             }

            if(rebateType == RebateType.THIRD_PARTY_REBATE.getType() && !carDamageInsured.equals("-") && Integer.parseInt(carDamageInsured) == 0){
                InsuranceRebateRatio rebateRatioFromSql = rebateRatioMapper.selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredZeroAndThirdPartyInsured(rebateType,usage,region.getThirdPartyInsuredStart(),region.getThirdPartyInsuredEnd(),createTime);
                if (rebateRatioFromSql == null) {//数据库中没有数据
                    InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                    MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                    rebateRatio.setRebateRatio(new BigDecimal(region.getRebateRatioThird()));//获取返点比例
                    rebateRatio.setThirdPartyInsuredStart(region.getThirdPartyInsuredStart());
                    rebateRatio.setThirdPartyInsuredEnd(region.getThirdPartyInsuredEnd());
                    rebateRatio.setUsageType(usage);
                    rebateRatioList.add(rebateRatio);
                }
            }
        }
//座位保判断
        if(rebateType == RebateType.SEAT_INSURANCE.getType()){
                InsuranceRebateRatio rebateRatioFromSql = rebateRatioMapper.selectByUsageTypeAndRebateTypeAndInsuranceDate(usage,rebateType,createTime);
                if (rebateRatioFromSql == null) {//数据库中没有数据
                    InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                    MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                    rebateRatio.setUsageType(usage);
                    rebateRatioList.add(rebateRatio);
                }
            }

        }

//        其他返点类型判断
//        竞回返点
        List<InsuranceRebateRatio> rebateRatioFromSqls = null;
        if(rebateType == RebateType.COMPETITION_REBATE.getType()){
            rebateRatioFromSqls = checkRebateIsExits(createTime, rebateType);
            if (rebateRatioFromSqls.get(0) == null) {//数据库中没有数据
                InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                rebateRatioList.add(rebateRatio);
            }
        }
//跟单零
        if(rebateType == RebateType.FOLLOW_UP_REBATE.getType()){
            rebateRatioFromSqls = checkRebateIsExits(createTime, rebateType);
            if (rebateRatioFromSqls.get(0) == null) {//数据库中没有数据
                InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                rebateRatioList.add(rebateRatio);
            }
        }
//过户
        if(rebateType == RebateType.IS_TRANSFER_REBATE.getType()){
            rebateRatioFromSqls = checkRebateIsExits(createTime, rebateType);
            if (rebateRatioFromSqls.get(0) == null) {//数据库中没有数据
                InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                rebateRatioList.add(rebateRatio);
            }
        }
//        交叉
        if(rebateType == RebateType.OVERLAPPING_REBATE.getType()){
            rebateRatioFromSqls = checkRebateIsExits(createTime, rebateType);
            if (rebateRatioFromSqls.get(0) == null) {//数据库中没有数据
                InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                rebateRatioList.add(rebateRatio);
            }
        }

        //        新车
        if(rebateType == RebateType.NEW_CAR_REBATE.getType()){
            rebateRatioFromSqls = checkRebateIsExits(createTime, rebateType);
            if (rebateRatioFromSqls.get(0) == null) {//数据库中没有数据
                InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                rebateRatioList.add(rebateRatio);
            }
        }

        //        次新车
        if(rebateType == RebateType.LAST_YEAR_CAR_REBATE.getType()){
            rebateRatioFromSqls = checkRebateIsExits(createTime, rebateType);
            if (rebateRatioFromSqls.get(0) == null) {//数据库中没有数据
                InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                rebateRatioList.add(rebateRatio);
            }
        }

        //        其他
        if(rebateType == RebateType.CHANGE_INTO_INSURANCE.getType()){
            rebateRatioFromSqls = checkRebateIsExits(createTime, rebateType);
            if (rebateRatioFromSqls.get(0) == null) {//数据库中没有数据
                InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                rebateRatioList.add(rebateRatio);
            }
        }
//     交强险保返点比
        if(rebateType == RebateType.COMPULSORY_REBATE.getType()){
            rebateRatioFromSqls = checkRebateIsExits(createTime, rebateType);
            if (rebateRatioFromSqls.get(0) == null) {//数据库中没有数据
                InsuranceRebateRatio rebateRatio = new InsuranceRebateRatio();
                MyBeanUtil.copyPropertiesIgnoreNull(rebatePojo,rebateRatio);
                rebateRatioList.add(rebateRatio);
            }
        }
        boolean saveBatch = this.saveBatch(rebateRatioList);
        if(saveBatch){
            return Result.OK("保存成功");
        }
        return  Result.error("已存在该返点比例");

    }

//    修改数据时三者险和座位保的数据相同时应该区别
    @Override
    public boolean editCheck(InsuranceRebateRatio insuranceRebateRatio) {
        String usageType = insuranceRebateRatio.getUsageType();
        String id = insuranceRebateRatio.getId();
        Integer rebateRatioType = insuranceRebateRatio.getRebateRatioType();
//        Integer seatNum = insuranceRebateRatio.getSeatNum();
        Date createTime = insuranceRebateRatio.getCreateTime();

//商业基础险
        if(rebateRatioType == RebateType.COMMERCIAL_BASIC_REBATE.getType()){
            InsuranceRebateRatio insuranceRebate = rebateRatioMapper.selectByUsageTypeAndRebateTypeAndInsuranceDate(usageType,rebateRatioType,createTime);
            if(insuranceRebate !=null && !insuranceRebate.getId().equals(id)){//查到的数据不是本条数据
                return true;
            }
        }

        String carDamageInsured = insuranceRebateRatio.getCarDamageInsured();//车损险
        String thirdPartyInsuredStart = insuranceRebateRatio.getThirdPartyInsuredStart();//三者险起始值
        String thirdPartyInsuredEnd = insuranceRebateRatio.getThirdPartyInsuredEnd();//三者险起始值


//          三者险返点比判断
        if(rebateRatioType == RebateType.THIRD_PARTY_REBATE.getType()){
//            根据使用性质、返点比、三者险保额、车损险保额查询
            InsuranceRebateRatio insuranceRebate = rebateRatioMapper.selectByUsageTypeAndRebateTypeAndCarDamageInsuredAndThirdPartyInsuredAndDate(usageType,rebateRatioType,carDamageInsured,thirdPartyInsuredStart,thirdPartyInsuredEnd,createTime);
            if(insuranceRebate !=null && !insuranceRebate.getId().equals(id)){
                return true;
            }
        }
//          座位保
        if(rebateRatioType == RebateType.SEAT_INSURANCE.getType()){
//             根据使用性质、返点比、乘客保额、司机保额查询
            InsuranceRebateRatio insuranceRebate = rebateRatioMapper.selectByUsageTypeAndRebateTypeAndInsuranceDate(usageType,rebateRatioType,createTime);
            if(insuranceRebate != null && !insuranceRebate.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<InsuranceRebateRatio> getInsuranceRebateRatioByTypeAndCarDamageInsuredIsZeroInsuranceDate(Integer type, String usageType,Date zbTime) {
        return rebateRatioMapper.selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredZero(type,usageType,zbTime);
    }

    @Override
    public List<InsuranceRebateRatio> getInsuranceRebateRatioByTypeAndCarDamageInsuredIsNotZeroInsuranceDate(Integer type, String usageType, Date zbTime) {
        return rebateRatioMapper.selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDateAndCarDamageInsuredNotZero(type,usageType,zbTime);
    }

    private List<InsuranceRebateRatio> checkRebateIsExits(Date createTime, Integer rebateType) {
        List<InsuranceRebateRatio> rebateRatioFromSqls = rebateRatioMapper.selectInsuranceRebateRatioByTypeAndInsuranceDate(rebateType, createTime);
        return rebateRatioFromSqls;
    }

    private InsuranceRebateRatio checkRebateIsExists(Date createTime, Integer rebateType, String usageType) {
        return rebateRatioMapper.selectInsuranceRebateRatioByTypeAndUsageTypeAndInsuranceDate(rebateType,usageType,createTime);
    }
}

