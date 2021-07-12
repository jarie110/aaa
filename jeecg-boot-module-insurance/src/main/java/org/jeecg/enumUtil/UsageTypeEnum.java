package org.jeecg.enumUtil;

public enum UsageTypeEnum {
    HOME_USE(0,"家庭自用"),
    NO_BUSINESS_CAR(1,"非营业企业客车"),
    BUSINESS_TRUCK(2,"营业货车"),
    NO_BUSINESS_TRUCK(3,"非营业货车"),
    OTHER_USAGE(9,"其他用途"),
    RENT_OUT(4,"出租或租赁"),
    BUSINESS_GROUP_BUS(5,"非营业机关，事业团体客车"),
    OTHER_NON_BUSINESS(6,"其他非营业车辆"),
    OTHER_BUSINESS(7,"其他营业车辆"),
    BUSINESS_TRAILER(8,"营业挂车"),
    BOOKING_TAXI(10,"预约出租车"),
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
