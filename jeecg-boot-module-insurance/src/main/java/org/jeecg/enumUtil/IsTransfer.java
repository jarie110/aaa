package org.jeecg.enumUtil;

import org.springframework.stereotype.Component;


public enum IsTransfer {
    TRANSFER(1,"过户"),
    NO_TRANSFER(0,"未过户")
    ;
    private Integer type;
    private String text;

    IsTransfer() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    IsTransfer(Integer type, String text) {
        this.type = type;
        this.text = text;
    }
}
