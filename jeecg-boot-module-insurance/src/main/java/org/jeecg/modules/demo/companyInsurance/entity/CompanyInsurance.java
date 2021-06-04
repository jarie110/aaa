package org.jeecg.modules.demo.companyInsurance.entity;

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
 * @Description: 保险公司保单
 * @Author: jeecg-boot
 * @Date:   2021-06-04
 * @Version: V1.0
 */
@Data
@TableName("company_insurance")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="company_insurance对象", description="保险公司保单")
public class CompanyInsurance implements Serializable {
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
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**投保单号*/
	@Excel(name = "投保单号", width = 15)
    @ApiModelProperty(value = "投保单号")
    private String toubaoDh;
	/**保单号 （交强，商业）*/
	@Excel(name = "保单号 （交强，商业）", width = 15)
    @ApiModelProperty(value = "保单号 （交强，商业）")
    private String insureNum;
	/**险类名称*/
	@Excel(name = "险类名称", width = 15)
    @ApiModelProperty(value = "险类名称")
    private String insureTypeName;
	/**险种代码*/
	@Excel(name = "险种代码", width = 15)
    @ApiModelProperty(value = "险种代码")
    private String insureProductCode;
	/**险种*/
	@Excel(name = "险种", width = 15)
    @ApiModelProperty(value = "险种")
    private String insureProductName;
	/**签单日期*/
	@Excel(name = "签单日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "签单日期")
    private Date insureSignDate;
	/**转保单时间（出单时间）*/
	@Excel(name = "转保单时间（出单时间）", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "转保单时间（出单时间）")
    private Date zbTime;
	/**转保单日期（出单日期）*/
	@Excel(name = "转保单日期（出单日期）", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "转保单日期（出单日期）")
    private Date zbDate;
	/**起保日期*/
	@Excel(name = "起保日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "起保日期")
    private Date insureStartDate;
	/**起保小时*/
	@Excel(name = "起保小时", width = 15)
    @ApiModelProperty(value = "起保小时")
    private Integer insureStartHours;
	/**终保小时*/
	@Excel(name = "终保小时", width = 15)
    @ApiModelProperty(value = "终保小时")
    private Integer insureEndHours;
	/**终保日期*/
	@Excel(name = "终保日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "终保日期")
    private Date insureEndDate;
	/**签单保费（含税）*/
	@Excel(name = "签单保费（含税）", width = 15)
    @ApiModelProperty(value = "签单保费（含税）")
    private Double insureFeeIncludeTax;
	/**签单保费（不含税）*/
	@Excel(name = "签单保费（不含税）", width = 15)
    @ApiModelProperty(value = "签单保费（不含税）")
    private Double insureFeeExcludeTax;
	/**已结金额*/
	@Excel(name = "已结金额", width = 15)
    @ApiModelProperty(value = "已结金额")
    private Double alreadyPaymentFee;
	/**未结金额*/
	@Excel(name = "未结金额", width = 15)
    @ApiModelProperty(value = "未结金额")
    private String unpaidFee;
	/**已赚保费*/
	@Excel(name = "已赚保费", width = 15)
    @ApiModelProperty(value = "已赚保费")
    private Double earnMoney;
	/**签单手续费*/
	@Excel(name = "签单手续费", width = 15)
    @ApiModelProperty(value = "签单手续费")
    private Double signServiceHarge;
	/**新续保标志*/
	@Excel(name = "新续保标志", width = 15)
    @ApiModelProperty(value = "新续保标志")
    private String renewalType;
	/**出单员*/
	@Excel(name = "出单员", width = 15)
    @ApiModelProperty(value = "出单员")
    private String salesMan;
	/**投保人名称*/
	@Excel(name = "投保人名称", width = 15)
    @ApiModelProperty(value = "投保人名称")
    private String policyHolder;
	/**被保险人名称*/
	@Excel(name = "被保险人名称", width = 15)
    @ApiModelProperty(value = "被保险人名称")
    private String insurant;
	/**初登日期*/
	@Excel(name = "初登日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "初登日期")
    private Date registerDate;
	/**使用性质*/
	@Excel(name = "使用性质", width = 15)
    @ApiModelProperty(value = "使用性质")
    private String carUsageType;
	/**车辆种类*/
	@Excel(name = "车辆种类", width = 15)
    @ApiModelProperty(value = "车辆种类")
    private String vehiclesType;
	/**三责保额*/
	@Excel(name = "三责保额", width = 15)
    @ApiModelProperty(value = "三责保额")
    private Integer thirdPartyInsured;
	/**三责保费*/
	@Excel(name = "三责保费", width = 15)
    @ApiModelProperty(value = "三责保费")
    private Double thirdPartyFee;
	/**发动机号*/
	@Excel(name = "发动机号", width = 15)
    @ApiModelProperty(value = "发动机号")
    private String engineNumber;
	/**车牌号*/
	@Excel(name = "车牌号", width = 15)
    @ApiModelProperty(value = "车牌号")
    private String vehicleLicense;
	/**车架号*/
	@Excel(name = "车架号", width = 15)
    @ApiModelProperty(value = "车架号")
    private String vehicleIdentity;
	/**渠道码*/
	@Excel(name = "渠道码", width = 15)
    @ApiModelProperty(value = "渠道码")
    private String distributionChannelCode;
	/**渠道名称*/
	@Excel(name = "渠道名称", width = 15)
    @ApiModelProperty(value = "渠道名称")
    private String distributionChannelName;
	/**渠道类型*/
	@Excel(name = "渠道类型", width = 15)
    @ApiModelProperty(value = "渠道类型")
    private String distributionChannelType;
	/**座位*/
	@Excel(name = "座位", width = 15)
    @ApiModelProperty(value = "座位")
    private Integer seatsNum;
	/**吨位*/
	@Excel(name = "吨位", width = 15)
    @ApiModelProperty(value = "吨位")
    private Integer ton;
	/**送修码名称*/
	@Excel(name = "送修码名称", width = 15)
    @ApiModelProperty(value = "送修码名称")
    private String repairName;
	/**送修码*/
	@Excel(name = "送修码", width = 15)
    @ApiModelProperty(value = "送修码")
    private String repairCode;
	/**滞纳金*/
	@Excel(name = "滞纳金", width = 15)
    @ApiModelProperty(value = "滞纳金")
    private Double lateFee;
	/**车主*/
	@Excel(name = "车主", width = 15)
    @ApiModelProperty(value = "车主")
    private String carOwner;
	/**最新归属机构代码*/
	@Excel(name = "最新归属机构代码", width = 15)
    @ApiModelProperty(value = "最新归属机构代码")
    private String newOrganizationCode;
	/**最新归属业务员*/
	@Excel(name = "最新归属业务员", width = 15)
    @ApiModelProperty(value = "最新归属业务员")
    private String newSalesman;
	/**最新渠道码*/
	@Excel(name = "最新渠道码", width = 15)
    @ApiModelProperty(value = "最新渠道码")
    private String newChannalCode;
	/**最新渠道名称*/
	@Excel(name = "最新渠道名称", width = 15)
    @ApiModelProperty(value = "最新渠道名称")
    private String newChannalName;
	/**是否录入过户标志*/
	@Excel(name = "是否录入过户标志", width = 15)
    @ApiModelProperty(value = "是否录入过户标志")
    private Integer isTransfer;
	/**过户日期*/
	@Excel(name = "过户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "过户日期")
    private Date transferDate;
	/**是否微信绑定客户*/
	@Excel(name = "是否微信绑定客户", width = 15)
    @ApiModelProperty(value = "是否微信绑定客户")
    private String bindWechat;
	/**承保险别名称*/
	@Excel(name = "承保险别名称", width = 15)
    @ApiModelProperty(value = "承保险别名称")
    private String insuranceAlias;
	/**微信绑定日期*/
	@Excel(name = "微信绑定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "微信绑定日期")
    private Date bindWechatDate;
	/**车损险保额*/
	@Excel(name = "车损险保额", width = 15)
    @ApiModelProperty(value = "车损险保额")
    private Double carDamageInsured;
	/**是否电子投保单*/
	@Excel(name = "是否电子投保单", width = 15)
    @ApiModelProperty(value = "是否电子投保单")
    private String isElectronicInsurance;
	/**是否使用电子保单*/
	@Excel(name = "是否使用电子保单", width = 15)
    @ApiModelProperty(value = "是否使用电子保单")
    private String isUsedElectronic;
	/**司机责任险保额*/
	@Excel(name = "司机责任险保额", width = 15)
    @ApiModelProperty(value = "司机责任险保额")
    private Integer driverLiabilityInsure;
	/**乘客责任险保额*/
	@Excel(name = "乘客责任险保额", width = 15)
    @ApiModelProperty(value = "乘客责任险保额")
    private Integer passengerLiability;
	/**上年终保日期*/
	@Excel(name = "上年终保日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上年终保日期")
    private Date lastYearInvalid;
}
