package org.jeecg.enumUtil;

import lombok.Data;


public enum IsChecked {
    CHECKED(1,"已比对"),
    NO_CHECKED(0,"未比对")
    ;
    private Integer code;
    private String checkName;

    IsChecked(Integer code, String checkName) {
        this.code = code;
        this.checkName = checkName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }
}
