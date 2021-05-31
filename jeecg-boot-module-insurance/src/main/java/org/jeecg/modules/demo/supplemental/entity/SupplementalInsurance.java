package org.jeecg.modules.demo.supplemental.entity;

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
 * @Description: 附加险
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Data
@TableName("supplemental_insurance")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="supplemental_insurance对象", description="附加险")
public class SupplementalInsurance implements Serializable {
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
	/**附加险种类*/
	@Excel(name = "附加险种类", width = 15)
    @ApiModelProperty(value = "附加险种类")
    private Integer supplementalInsuranceType;
	/**附加险名称*/
	@Excel(name = "附加险名称", width = 15)
    @ApiModelProperty(value = "附加险名称")
    private String supplementalInsuranceName;
	/**附加险保额*/
	@Excel(name = "附加险保额", width = 15)
    @ApiModelProperty(value = "附加险保额")
    private Integer supplementalAmount;
}
