package org.jeecg.common.exceptionEnum;

/**
 * 异常枚举
 */
public enum InsuranceExceptionEnum {
// 自定义异常信息
    INSURANCE_EXCEPTION(400,"保单错误"),
    UNDEFINED_NATURE_OF_USE(400,"未设置当天使用性质，请先设置然后再计算"),


    ;
    private Integer exceptionCode;
    private String msg;

    InsuranceExceptionEnum(Integer exceptionCode, String msg) {
        this.exceptionCode = exceptionCode;
        this.msg = msg;
    }

    public Integer getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(Integer exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
