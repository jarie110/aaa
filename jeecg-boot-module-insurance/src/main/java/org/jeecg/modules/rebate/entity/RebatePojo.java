package org.jeecg.modules.rebate.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class RebatePojo implements Serializable {
    private String id;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer rebateRatioType;

    private String isTransfer;

    private String renewalType;

    private String usageType;

    private String  thirdPartyInsureds;

    private String carDamageInsured;

    private String driverLiabilityInsured;

    private String passengerLiability;

    private BigDecimal rebateRatio;

    private BigDecimal bonus;

    private BigDecimal signFee;

    private List<Region> regions;
}
