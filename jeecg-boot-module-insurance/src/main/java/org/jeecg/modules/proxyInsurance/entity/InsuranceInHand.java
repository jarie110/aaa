package org.jeecg.modules.proxyInsurance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @Description: 手输保单
 * @Author: jeecg-boot
 * @Date:   2021-06-09
 * @Version: V1.0
 */
@Data
@TableName("insurance_in_hand")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="insurance_in_hand对象", description="手输保单")
public class InsuranceInHand implements Serializable {

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof InsuranceInHand)) return false;
		InsuranceInHand that = (InsuranceInHand) o;
		return Objects.equals(getId(), that.getId()) &&
//				Objects.equals(getCreateBy(), that.getCreateBy()) &&
//				Objects.equals(getCreateTime(), that.getCreateTime()) &&
//				Objects.equals(getUpdateBy(), that.getUpdateBy()) &&
//				Objects.equals(getUpdateTime(), that.getUpdateTime()) &&
				Objects.equals(getInsuranceDate(), that.getInsuranceDate()) &&
				Objects.equals(getSalesman(), that.getSalesman()) &&
				Objects.equals(getVehicleLicense(), that.getVehicleLicense()) &&
				Objects.equals(getCustomer(), that.getCustomer()) &&
				Objects.equals(getVehicleIdentity(), that.getVehicleIdentity()) &&
				Objects.equals(getCompulsoryInsurCode(), that.getCompulsoryInsurCode()) &&
				Objects.equals(getCompulsoryInsurFee(), that.getCompulsoryInsurFee()) &&
				Objects.equals(getCommercialInsurCode(), that.getCommercialInsurCode()) &&
				Objects.equals(getCommercialInsurFee(), that.getCommercialInsurFee()) &&
				Objects.equals(getVehicleVesselTax(), that.getVehicleVesselTax()) &&
				Objects.equals(getInsuranceTotalFee(), that.getInsuranceTotalFee()) &&
				Objects.equals(getTotalServiceFee(), that.getTotalServiceFee()) &&
				Objects.equals(getRenewalType(), that.getRenewalType()) &&
				Objects.equals(getCarUsageType(), that.getCarUsageType()) &&
				Objects.equals(getDistributionChannelId(), that.getDistributionChannelId()) &&
				Objects.equals(getRemark(), that.getRemark()) &&
				Objects.equals(getInsuranceTeam(), that.getInsuranceTeam()) &&
				Objects.equals(getThirdPartyInsured(), that.getThirdPartyInsured()) &&
				Objects.equals(getDriverLiabilityInsure(), that.getDriverLiabilityInsure()) &&
				Objects.equals(getCarDamageInsured(), that.getCarDamageInsured()) &&
				Objects.equals(getPassengerLiability(), that.getPassengerLiability()) &&
				Objects.equals(getRegisterDate(), that.getRegisterDate()) &&
				Objects.equals(getIsTransfer(), that.getIsTransfer()) &&
				Objects.equals(getSeatsNum(), that.getSeatsNum()) &&
				Objects.equals(getCompulsoryInsuranceRebate(), that.getCompulsoryInsuranceRebate()) &&
				Objects.equals(getCommercialInsuranceRebate(), that.getCommercialInsuranceRebate()) &&
				Objects.equals(getIsPayCommission(), that.getIsPayCommission()) &&
				Objects.equals(getIsChecked(), that.getIsChecked()) &&
				Objects.equals(getSeatBonus(), that.getSeatBonus()) &&
				Objects.equals(getPaymentWay(), that.getPaymentWay());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(),  /*getCreateBy(), getCreateTime(), getUpdateBy(),getUpdateTime(),*/ getInsuranceDate(), getSalesman(), getVehicleLicense(), getCustomer(), getVehicleIdentity(), getCompulsoryInsurCode(), getCompulsoryInsurFee(), getCommercialInsurCode(), getCommercialInsurFee(), getVehicleVesselTax(), getInsuranceTotalFee(), getTotalServiceFee(), getRenewalType(), getCarUsageType(), getDistributionChannelId(), getRemark(), getInsuranceTeam(), getThirdPartyInsured(), getDriverLiabilityInsure(), getCarDamageInsured(), getPassengerLiability(), getRegisterDate(), getIsTransfer(), getSeatsNum(), getCompulsoryInsuranceRebate(), getCommercialInsuranceRebate(), getIsPayCommission(), getIsChecked(), getSeatBonus(), getPaymentWay());
	}

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
    private String billMan;
	/**业务员*/
	@Excel(name = "业务员", width = 15)
    @ApiModelProperty(value = "业务员")
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
    private String renewalType;
	/**使用性质*/
	@Excel(name = "使用性质", width = 15, dictTable = "insurance_usage", dicText = "usage_name", dicCode = "usage_type")
	@Dict(dictTable = "insurance_usage", dicText = "usage_name", dicCode = "usage_type")
    @ApiModelProperty(value = "使用性质")
    private String carUsageType;
	/**渠道类型*/
	@Excel(name = "渠道类型", width = 15, dictTable = "distribution_channel", dicText = "channel_name", dicCode = "channel_type")
	@Dict(dictTable = "distribution_channel", dicText = "channel_name", dicCode = "channel_type")
    @ApiModelProperty(value = "渠道类型")
    private String distributionChannelId;
	/**所属团队*/
	@Excel(name = "所属团队", width = 15, dictTable = "insurance_team", dicText = "team_name", dicCode = "team_code")
	@Dict(dictTable = "insurance_team", dicText = "team_name", dicCode = "team_code")
    @ApiModelProperty(value = "所属团队")
    private Integer insuranceTeam;
	/**三者责任险保额*/
	@Excel(name = "三者责任险保额", width = 15)
    @ApiModelProperty(value = "三者责任险保额")
    private Double thirdPartyInsured;
	/**司机责任险保额*/
	@Excel(name = "司机责任险保额", width = 15)
    @ApiModelProperty(value = "司机责任险保额")
    private Double driverLiabilityInsure;
	/**车损险保额*/
	@Excel(name = "车损险保额", width = 15)
    @ApiModelProperty(value = "车损险保额")
    private Double carDamageInsured;
	/**乘客责任险保额*/
	@Excel(name = "乘客责任险保额", width = 15)
    @ApiModelProperty(value = "乘客责任险保额")
    private Double passengerLiability;
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
	/**座位数*/
	@Excel(name = "座位数", width = 15)
    @ApiModelProperty(value = "座位数")
    private Integer seatsNum;
	/**交强险返点比*/
	@Excel(name = "交强险返点比", width = 15)
    @ApiModelProperty(value = "交强险返点比")
    private BigDecimal compulsoryInsuranceRebate;
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
    private BigDecimal seatBonus;
	/**返点支付方式*/
	@Excel(name = "返点支付方式", width = 15, dicCode = "rebate_way")
	@Dict(dicCode = "rebate_way")
    @ApiModelProperty(value = "返点支付方式")
    private Integer paymentWay;

	/**用户id*/
	@Excel(name = "用户id", width = 15)
	@ApiModelProperty(value = "用户id")
	private String uid;
//
//	/**排序标识*/
//	@Excel(name = "排序标识", width = 15)
//	@ApiModelProperty(value = "排序标识")
//	private Integer sortBy;
//
//	/**查询开始时间*/
//	@Excel(name = "查询开始时间", width = 15)
//	@ApiModelProperty(value = "查询开始时间")
//	private Date  startDate;
//
//
//	/**查询结束时间*/
//	@Excel(name = "查询结束时间", width = 15)
//	@ApiModelProperty(value = "查询结束时间")
//	private Date  endDate;

	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remark;
}