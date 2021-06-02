package org.jeecg.modules.demo.proxyInsurance.entity;

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
 * @Description: 手输保单
 * @Author: jeecg-boot
 * @Date:   2021-06-02
 * @Version: V1.0
 */
@Data
@TableName("insurance_in_hand")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="insurance_in_hand对象", description="手输保单")
public class InsuranceInHand implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**录入日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
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
	/**车牌号*/
	@Excel(name = "车牌号", width = 15)
    @ApiModelProperty(value = "车牌号")
    private String vehicleLicense;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String customer;
	/**车架号*/
	@Excel(name = "车架号", width = 15)
    @ApiModelProperty(value = "车架号")
    private String vehicleIdentity;
	/**交强险保单号*/
	@Excel(name = "交强险保单号", width = 15)
    @ApiModelProperty(value = "交强险保单号")
    private String compulsoryInsurCode;
	/**交强险保费*/
	@Excel(name = "交强险保费", width = 15)
    @ApiModelProperty(value = "交强险保费")
    private Double compulsoryInsurFee;
	/**商业险保单号*/
	@Excel(name = "商业险保单号", width = 15)
    @ApiModelProperty(value = "商业险保单号")
    private String commercialInsurCode;
	/**商业险保费*/
	@Excel(name = "商业险保费", width = 15)
    @ApiModelProperty(value = "商业险保费")
    private Double commercialInsurFee;
	/**车船税*/
	@Excel(name = "车船税", width = 15)
    @ApiModelProperty(value = "车船税")
    private Double vehicleVesselTax;
	/**保费总额*/
	@Excel(name = "保费总额", width = 15)
    @ApiModelProperty(value = "保费总额")
    private Double insuranceTotalFee;
	/**手续费总额*/
	@Excel(name = "手续费总额", width = 15)
    @ApiModelProperty(value = "手续费总额")
    private Double totalServiceFee;
	/**新续保标志*/
	@Excel(name = "新续保标志", width = 15, dicCode = "renewal_symbol")
	@Dict(dicCode = "renewal_symbol")
    @ApiModelProperty(value = "新续保标志")
    private Integer renewalType;
	/**使用性质*/
	@Excel(name = "使用性质", width = 15, dictTable = "insurance_usage", dicText = "usage_name", dicCode = "usage_type")
	@Dict(dictTable = "insurance_usage", dicText = "usage_name", dicCode = "usage_type")
    @ApiModelProperty(value = "使用性质")
    private Integer carUsageType;
	/**渠道名称*/
	@Excel(name = "渠道名称", width = 15, dictTable = "distribution_channel", dicText = "channel_name", dicCode = "channel_type")
	@Dict(dictTable = "distribution_channel", dicText = "channel_name", dicCode = "channel_type")
    @ApiModelProperty(value = "渠道名称")
    private Integer distributionChannelId;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remark;
	/**所属团队*/
	@Excel(name = "所属团队", width = 15, dictTable = "insurance_team", dicText = "team_name", dicCode = "team_code")
	@Dict(dictTable = "insurance_team", dicText = "team_name", dicCode = "team_code")
    @ApiModelProperty(value = "所属团队")
    private Integer insuranceTeam;
	/**三者责任险保额*/
	@Excel(name = "三者责任险保额", width = 15)
    @ApiModelProperty(value = "三者责任险保额")
    private Integer thirdPartyInsured;
	/**司机责任险保额*/
	@Excel(name = "司机责任险保额", width = 15)
    @ApiModelProperty(value = "司机责任险保额")
    private Integer driverLiabilityInsure;
	/**车损险保额*/
	@Excel(name = "车损险保额", width = 15)
    @ApiModelProperty(value = "车损险保额")
    private Integer carDamageInsured;
	/**乘客责任险保额*/
	@Excel(name = "乘客责任险保额", width = 15)
    @ApiModelProperty(value = "乘客责任险保额")
    private Integer passengerLiability;
	/**初登日期*/
	@Excel(name = "初登日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "初登日期")
    private Date registerDate;
	/**是否过户*/
	@Excel(name = "是否过户", width = 15, dicCode = "is_transfer")
	@Dict(dicCode = "is_transfer")
    @ApiModelProperty(value = "是否过户")
    private Integer isTransfer;
	/**签单手续费*/
	@Excel(name = "签单手续费", width = 15)
    @ApiModelProperty(value = "签单手续费")
    private Double signFee;
	/**座位数*/
	@Excel(name = "座位数", width = 15)
    @ApiModelProperty(value = "座位数")
    private Integer seatsNum;
	/**交强险返点比*/
	@Excel(name = "交强险返点比", width = 15)
    @ApiModelProperty(value = "交强险返点比")
    private Double compulsoryInsuranceRebate;
	/**商业险返点比*/
	@Excel(name = "商业险返点比", width = 15)
    @ApiModelProperty(value = "商业险返点比")
    private Double commercialInsuranceRebate;
	/**是否返佣*/
	@Excel(name = "是否返佣", width = 15, dicCode = "is_paid_rebate")
	@Dict(dicCode = "is_paid_rebate")
    @ApiModelProperty(value = "是否返佣")
    private Integer isPayCommission;
	/**是否比对*/
	@Excel(name = "是否比对", width = 15, dicCode = "is_checked")
	@Dict(dicCode = "is_checked")
    @ApiModelProperty(value = "是否比对")
    private Integer isChecked;
	/**座位保奖励金*/
	@Excel(name = "座位保奖励金", width = 15)
    @ApiModelProperty(value = "座位保奖励金")
    private Double seatBonus;
	/**返点支付方式*/
	@Excel(name = "返点支付方式", width = 15, dicCode = "rebate_way")
	@Dict(dicCode = "rebate_way")
    @ApiModelProperty(value = "返点支付方式")
    private Integer paymentWay;
}
