package org.jeecg.enumUtil;

import org.springframework.stereotype.Component;

public enum UsageTypeEnum {
    HOME_USE(0,"家庭自用"),
    NO_BUSINESS_CAR(1,"非营业企业客车"),
    BUSINESS_TRUCK(2,"营业货车"),
    NO_BUSINESS_TRUCK(3,"非营业货车"),
    OTHER_USAGE(9,"其他用途")
    ;
    private Integer type;
    private String usageName;
    UsageTypeEnum(Integer usageCode, String usageName){
        this.type = usageCode;
        this.usageName = usageName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsageName() {
        return usageName;
    }

    public void setUsageName(String usageName) {
        this.usageName = usageName;
    }
}
