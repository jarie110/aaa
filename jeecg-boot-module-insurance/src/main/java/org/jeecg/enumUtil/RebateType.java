package org.jeecg.enumUtil;

public enum RebateType {
    COMMERCIAL_BASIC_REBATE(0,"商业险基础返点"),
    THIRD_PARTY_REBATE(1,"三责保额返点"),
    LAST_YEAR_CAR_REBATE(2,"次新车返点"),
    NEW_CAR_REBATE(3,"新车返点比"),
    COMPETITION_REBATE(4,"竞回返点"),
    IS_TRANSFER_REBATE(5,"过户返点比"),
    OVERLAPPING_REBATE(6,"交叉返点比"),
    FOLLOW_UP_REBATE(7,"跟单零返点比"),
    SEAT_INSURANCE(8,"座位保返点比"),
    CHANGE_INTO_INSURANCE(9,"转入或其他"),
    COMPULSORY_REBATE(10,"交强险返点比"),
    ;
    private Integer type;
    private String RebateName;

    RebateType(Integer type, String rebateName) {
        this.type = type;
        RebateName = rebateName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRebateName() {
        return RebateName;
    }

    public void setRebateName(String rebateName) {
        RebateName = rebateName;
    }
}
