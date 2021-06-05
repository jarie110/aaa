package org.jeecg.modules.demo.checked.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 核对的保单
 * @Author: jeecg-boot
 * @Date:   2021-06-05
 * @Version: V1.0
 */
@Data
@TableName("check_insurance")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="check_insurance对象", description="核对的保单")
public class CheckInsurance implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
	/**保单编号*/
	@Excel(name = "保单编号", width = 15)
    @ApiModelProperty(value = "保单编号")
    private String insureNum;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入日期")
    private Date inputInsuranceDate;
	/**比对日期*/
	@Excel(name = "比对日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "比对日期")
    private Date checkDate;
	/**出单日期*/
	@Excel(name = "出单日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出单日期")
    private Date insuranceDate;
	/**出单员*/
	@Excel(name = "出单员", width = 15)
    @ApiModelProperty(value = "出单员")
    private String salesman;
	/**所属团队*/
	@Excel(name = "所属团队", width = 15)
    @ApiModelProperty(value = "所属团队")
    private String insuranceTeam;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String customerName;
	/**车架号*/
	@Excel(name = "车架号", width = 15)
    @ApiModelProperty(value = "车架号")
    private String vehicleIdentity;
	/**交强险保单号*/
	@Excel(name = "交强险保单号", width = 15)
    @ApiModelProperty(value = "交强险保单号")
    private String compulsoryInsurCode;
	/**商业险保单号*/
	@Excel(name = "商业险保单号", width = 15)
    @ApiModelProperty(value = "商业险保单号")
    private String commercialInsurCode;
	/**交强险保费*/
	@Excel(name = "交强险保费", width = 15)
    @ApiModelProperty(value = "交强险保费")
    private Double compulsoryInsurFee;
	/**保费总额*/
	@Excel(name = "保费总额", width = 15)
    @ApiModelProperty(value = "保费总额")
    private Double insuranceTotalFee;
	/**交强险手续费比例*/
	@Excel(name = "交强险手续费比例", width = 15)
    @ApiModelProperty(value = "交强险手续费比例")
    private Double compulsoryInsuranceRebate;
	/**商业险手续费比例*/
	@Excel(name = "商业险手续费比例", width = 15)
    @ApiModelProperty(value = "商业险手续费比例")
    private Double commercialInsuranceRebate;
	/**额外奖励金额*/
	@Excel(name = "额外奖励金额", width = 15)
    @ApiModelProperty(value = "额外奖励金额")
    private Double bonus;
	/**渠道名称*/
	@Excel(name = "渠道名称", width = 15)
    @ApiModelProperty(value = "渠道名称")
    private String distributionChannelId;
	/**起保日期*/
	@Excel(name = "起保日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "起保日期")
    private Date insureStartDate;
	/**初登日期*/
	@Excel(name = "初登日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "初登日期")
    private Date registerDate;
	/**签单手续费*/
	@Excel(name = "签单手续费", width = 15)
    @ApiModelProperty(value = "签单手续费")
    private Double signServiceHarge;
	/**新续保标志*/
	@Excel(name = "新续保标志", width = 15)
    @ApiModelProperty(value = "新续保标志")
    private String renewalType;
	/**使用性质*/
	@Excel(name = "使用性质", width = 15)
    @ApiModelProperty(value = "使用性质")
    private String carUsageType;
	/**车辆种类*/
	@Excel(name = "车辆种类", width = 15)
    @ApiModelProperty(value = "车辆种类")
    private String vehiclesType;
	/**是否录入过户标志*/
	@Excel(name = "是否录入过户标志", width = 15)
    @ApiModelProperty(value = "是否录入过户标志")
    private String isTransfer;
	/**三责保额*/
	@Excel(name = "三责保额", width = 15)
    @ApiModelProperty(value = "三责保额")
    private Integer thirdPartyInsured;
	/**车损险保额*/
	@Excel(name = "车损险保额", width = 15)
    @ApiModelProperty(value = "车损险保额")
    private Double carDamageInsured;
	/**承保险别名称*/
	@Excel(name = "承保险别名称", width = 15)
    @ApiModelProperty(value = "承保险别名称")
    private String insuranceAlias;
	/**座位*/
	@Excel(name = "座位", width = 15)
    @ApiModelProperty(value = "座位")
    private Integer seatsNum;
	/**司机责任险保额*/
	@Excel(name = "司机责任险保额", width = 15)
    @ApiModelProperty(value = "司机责任险保额")
    private Integer driverLiabilityInsure;
	/**乘客责任险保额*/
	@Excel(name = "乘客责任险保额", width = 15)
    @ApiModelProperty(value = "乘客责任险保额")
    private Integer passengerLiability;
	/**手续费总额*/
	@Excel(name = "手续费总额", width = 15)
    @ApiModelProperty(value = "手续费总额")
    private Double totalServiceFee;
	/**是否返点*/
	@Excel(name = "是否返点", width = 15, dicCode = "is_paid_rebate")
	@Dict(dicCode = "is_paid_rebate")
    @ApiModelProperty(value = "是否返点")
    private Integer isRebate;
	/**返点支付时间*/
	@Excel(name = "返点支付时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "返点支付时间")
    private Date rebatePaymentDate;
	/**返点收取方式*/
	@Excel(name = "返点收取方式", width = 15, dicCode = "rebate_way")
	@Dict(dicCode = "rebate_way")
    @ApiModelProperty(value = "返点收取方式")
    private Integer rebateWay;
	/**车牌号*/
	@Excel(name = "车牌号", width = 15)
    @ApiModelProperty(value = "车牌号")
    private String vehicleLicense;
	/**商业险保费*/
	@Excel(name = "商业险保费", width = 15)
    @ApiModelProperty(value = "商业险保费")
    private Double commercialInsurFee;
	/**车船税*/
	@Excel(name = "车船税", width = 15)
    @ApiModelProperty(value = "车船税")
    private Double vehicleVesselTax;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
}
