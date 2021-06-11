package org.jeecg.modules.demo.rebate.entity;

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

/**
 * @Description: 返点比例
 * @Author: jeecg-boot
 * @Date:   2021-06-11
 * @Version: V1.0
 */
@Data
@TableName("insurance_rebate_ratio")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="insurance_rebate_ratio对象", description="返点比例")
public class InsuranceRebateRatio implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**返点类型*/
	@Excel(name = "返点类型", width = 15, dicCode = "rebate_ratio_type")
	@Dict(dicCode = "rebate_ratio_type")
    @ApiModelProperty(value = "返点类型")
    private Integer rebateRatioType;
	/**是否过户*/
	@Excel(name = "是否过户", width = 15, dicCode = "is_transfer")
	@Dict(dicCode = "is_transfer")
    @ApiModelProperty(value = "是否过户")
    private String isTransfer;
	/**续保类型*/
	@Excel(name = "续保类型", width = 15, dicCode = "renewal_symbol")
	@Dict(dicCode = "renewal_symbol")
    @ApiModelProperty(value = "续保类型")
    private String renewalType;
	/**使用性质*/
	@Excel(name = "使用性质", width = 15, dictTable = "insurance_usage", dicText = "usage_name", dicCode = "usage_type")
	@Dict(dictTable = "insurance_usage", dicText = "usage_name", dicCode = "usage_type")
    @ApiModelProperty(value = "使用性质")
    private String usageType;
	/**三责保额档*/
	@Excel(name = "三责保额档", width = 15)
    @ApiModelProperty(value = "三责保额档")
    private String thirdPartyInsured;
	/**车损险保额档*/
	@Excel(name = "车损险保额档", width = 15)
    @ApiModelProperty(value = "车损险保额档")
    private String carDamageInsured;
	/**司机责任险保额档*/
	@Excel(name = "司机责任险保额档", width = 15)
    @ApiModelProperty(value = "司机责任险保额档")
    private String driverLiabilityInsured;
	/**乘客责任险保额档*/
	@Excel(name = "乘客责任险保额档", width = 15)
    @ApiModelProperty(value = "乘客责任险保额档")
    private String passengerLiability;
	/**返点比例*/
	@Excel(name = "返点比例", width = 15)
    @ApiModelProperty(value = "返点比例")
    private BigDecimal rebateRatio;
	/**奖金*/
	@Excel(name = "奖金", width = 15)
    @ApiModelProperty(value = "奖金")
    private BigDecimal bonus;
	/**签单手续费*/
	@Excel(name = "签单手续费", width = 15)
    @ApiModelProperty(value = "签单手续费")
    private BigDecimal signFee;
}
