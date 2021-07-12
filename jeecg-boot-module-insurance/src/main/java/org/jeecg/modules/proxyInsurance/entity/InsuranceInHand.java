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
 * @Description: insurance_in_hand
 * @Author: jeecg-boot
 * @Date:   2021-07-02
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
//				Objects.equals(getSalesman(), that.getSalesman()) &&
				Objects.equals(getVehicleLicense(), that.getVehicleLicense()) &&
				Objects.equals(getCustomer(), that.getCustomer()) &&
				Objects.equals(getVehicleIdentity(), that.getVehicleIdentity()) &&
				Objects.equals(getCompulsoryInsurCode(), that.getCompulsoryInsurCode()) &&
				Objects.equals(getCompulsoryInsurFee(), that.getCompulsoryInsurFee()) &&
				Objects.equals(getCommercialInsurCode(), that.getCommercialInsurCode()) &&
				Objects.equals(getCommercialInsurFee(), that.getCommercialInsurFee()) &&
				Objects.equals(getVehicleVesselTax(), that.getVehicleVesselTax()) &&
//				Objects.equals(getInsuranceTotalFee(), that.getInsuranceTotalFee()) &&
//				Objects.equals(getTotalServiceFee(), that.getTotalServiceFee()) &&
//				Objects.equals(getRenewalType(), that.getRenewalType()) &&
//				Objects.equals(getCarUsageType(), that.getCarUsageType()) &&
				Objects.equals(getDistributionChannelId(), that.getDistributionChannelId()) &&
				Objects.equals(getRemark(), that.getRemark()) &&
				Objects.equals(getUid(), that.getUid()) &&
//				Objects.equals(getInsuranceTeam(), that.getInsuranceTeam()) &&
//				Objects.equals(getThirdPartyInsured(), that.getThirdPartyInsured()) &&
//				Objects.equals(getDriverLiabilityInsure(), that.getDriverLiabilityInsure()) &&
//				Objects.equals(getCarDamageInsured(), that.getCarDamageInsured()) &&
//				Objects.equals(getPassengerLiability(), that.getPassengerLiability()) &&
//				Objects.equals(getRegisterDate(), that.getRegisterDate()) &&
//				Objects.equals(getIsTransfer(), that.getIsTransfer()) &&
//				Objects.equals(getSeatsNum(), that.getSeatsNum()) &&
//				Objects.equals(getCompulsoryInsuranceRebate(), that.getCompulsoryInsuranceRebate()) &&
//				Objects.equals(getCommercialInsuranceRebate(), that.getCommercialInsuranceRebate()) &&
//				Objects.equals(getIsPayCommission(), that.getIsPayCommission()) &&
				Objects.equals(getIsChecked(), that.getIsChecked());
//				Objects.equals(getSeatBonus(), that.getSeatBonus()) &&
//				Objects.equals(getPaymentWay(), that.getPaymentWay());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(),  /*getCreateBy(), getCreateTime(), getUpdateBy(),getUpdateTime(),*/ getInsuranceDate() /*getSalesman()*/, getVehicleLicense(), getCustomer(), getVehicleIdentity(), getCompulsoryInsurCode(), getCompulsoryInsurFee(), getCommercialInsurCode(), getCommercialInsurFee(), getVehicleVesselTax(),/*, getInsuranceTotalFee(), getTotalServiceFee(), getRenewalType(), getCarUsageType(),*/ getDistributionChannelId(), getRemark(),/* getInsuranceTeam(), getThirdPartyInsured(), getDriverLiabilityInsure(), getCarDamageInsured(), getPassengerLiability(), getRegisterDate(), getIsTransfer(), getSeatsNum(), getCompulsoryInsuranceRebate(), getCommercialInsuranceRebate(), getIsPayCommission(),*/ getIsChecked()/* getSeatBonus(), getPaymentWay()*/,getUid());
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
    private BigDecimal compulsoryInsurFee;
	/**商业险保单号*/
	@Excel(name = "商业险保单号", width = 15)
    @ApiModelProperty(value = "商业险保单号")
    private String commercialInsurCode;
	/**商业险保费*/
	@Excel(name = "商业险保费", width = 15)
    @ApiModelProperty(value = "商业险保费")
    private BigDecimal commercialInsurFee;
	/**车船税*/
	@Excel(name = "车船税", width = 15)
    @ApiModelProperty(value = "车船税")
    private BigDecimal vehicleVesselTax;
	/**保费总额*/
//	@Excel(name = "保费总额", width = 15)
    @ApiModelProperty(value = "保费总额")
    private BigDecimal insuranceTotalFee;
	/**手续费总额*/
//	@Excel(name = "手续费总额", width = 15)
    @ApiModelProperty(value = "手续费总额")
    private BigDecimal totalServiceFee;
	/**渠道类型*/
	@Excel(name = "渠道类型", width = 15, dictTable = "distribution_channel", dicText = "channel_name", dicCode = "channel_type")
	@Dict(dictTable = "distribution_channel", dicText = "channel_name", dicCode = "channel_type")
	@ApiModelProperty(value = "渠道类型")
	private String distributionChannelId;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remark;
	/**是否比对*/
	@Excel(name = "是否比对", width = 15, dicCode = "is_checked")
	@Dict(dicCode = "is_checked")
    @ApiModelProperty(value = "是否比对")
    private Integer isChecked;
//	/**业务员*/
//	@Excel(name = "业务员", width = 15)
//    @ApiModelProperty(value = "业务员")
//    private String salesman;
	/**用户id*/
	@Excel(name = "业务员", width = 15, dictTable = "sys_user",dicCode = "id",dicText = "realname")
	@Dict(dictTable = "sys_user",dicCode = "id",dicText = "realname")
    @ApiModelProperty(value = "用户id")
    private String uid;
	/**总返点比*/
//	@Excel(name = "总返点比", width = 15)
    @ApiModelProperty(value = "总返点比")
    private BigDecimal totalServiceRebate;
}
