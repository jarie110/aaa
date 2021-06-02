package org.jeecg.enumUtil;

/**
 * 支付方式：
 * 1：微信
 * 2：支付宝
 * 3：银行卡
 */
public enum PaymentWay {

    WECHAT_PAY(1,"微信"),
    ALIPAY_PAY(2,"支付宝"),
    BANK_PAY(3,"银行卡")
    ;
    private Integer code;
    private String payment_name;

    PaymentWay( Integer code,String payment_name) {
        this.code = code;
        this.payment_name = payment_name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }
}
