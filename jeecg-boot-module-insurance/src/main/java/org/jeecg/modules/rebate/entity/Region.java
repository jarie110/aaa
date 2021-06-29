package org.jeecg.modules.rebate.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Region implements Serializable {
    private String thirdPartyInsuredStart;
    private String thirdPartyInsuredEnd;
    private String rebateRatioThird;
}
