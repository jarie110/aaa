package org.jeecg.enumUtil;

import org.springframework.stereotype.Component;

public enum RenewalTypeEnum {
    NEW_CAR_RENEWAL(1,"新车续保"),
    LAST_YEAR_RENEWAL(2,"次新车续保"),
    COMPANY_RENEWAL(5,"市公司续保"),
    REPLACEMENT_RENEWAL(3,"转保"),
    COMPETITION_RENEWAL(4,"竞回续保"),
    OUT_OF_INSURANCE_RENEWAL(0,"脱保续保")
    ;
    private Integer type;
    private String renewalName;

    RenewalTypeEnum(Integer type, String renewalName) {
        this.type = type;
        this.renewalName = renewalName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRenewalName() {
        return renewalName;
    }

    public void setRenewalName(String renewalName) {
        this.renewalName = renewalName;
    }
}
