package org.jeecg.modules.proxyInsurance.entity;

import org.springframework.stereotype.Component;

@Component
public class RenewalPo {
    private Integer type;
    private String RenewalName;

    public RenewalPo() {
    }

    public RenewalPo(Integer type, String renewalName) {
        this.type = type;
        RenewalName = renewalName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRenewalName() {
        return RenewalName;
    }

    public void setRenewalName(String renewalName) {
        RenewalName = renewalName;
    }
}
