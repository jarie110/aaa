package org.jeecg.common.exception;

import org.jeecg.common.exceptionEnum.InsuranceExceptionEnum;
import org.springframework.stereotype.Component;

/**
 * 自定义异常处理类
 */

@Component
public class InsuranceException extends Exception {
    private InsuranceExceptionEnum exceptionEnum;
    private String msg;
    private Integer exceptionCode;
    InsuranceException(InsuranceExceptionEnum exceptionEnum){
        this.exceptionCode = exceptionEnum.getExceptionCode();
        this.msg = exceptionEnum.getMsg();
    }

    public InsuranceException() {
    }

    public InsuranceException(String msg, Integer exceptionCode) {
        this.msg = msg;
        this.exceptionCode = exceptionCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(Integer exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
