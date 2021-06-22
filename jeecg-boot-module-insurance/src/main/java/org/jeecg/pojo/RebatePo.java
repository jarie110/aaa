package org.jeecg.pojo;


import java.math.BigDecimal;
import java.util.Date;

public class RebatePo  {
    private Integer rebateType;
    private String thirdPartyInsuredZero;
    private String thirdPartyInsured;
    private String carDamageInsured;
    private String carDamageInsuredZero;
    private String renewalName;
    private String driverLiabilityInsured;
    private String passengerLiabilityInsured;

    private BigDecimal rebateRatio;//返点比例
    private BigDecimal rebateRatioZero;
    private String isTransfer;
    private Integer signFee;
    private BigDecimal bonus;
    private String usageType;
    private Date createTimeBegin;
    private Date createTimeEnd;

    public RebatePo() {
    }

    public Integer getRebateType() {
        return rebateType;
    }

    public void setRebateType(Integer rebateType) {
        this.rebateType = rebateType;
    }

    public String getThirdPartyInsuredZero() {
        return thirdPartyInsuredZero;
    }

    public void setThirdPartyInsuredZero(String thirdPartyInsuredZero) {
        this.thirdPartyInsuredZero = thirdPartyInsuredZero;
    }

    public String getThirdPartyInsured() {
        return thirdPartyInsured;
    }

    public void setThirdPartyInsured(String thirdPartyInsured) {
        this.thirdPartyInsured = thirdPartyInsured;
    }

    public String getCarDamageInsured() {
        return carDamageInsured;
    }

    public void setCarDamageInsured(String carDamageInsured) {
        this.carDamageInsured = carDamageInsured;
    }

    public String getCarDamageInsuredZero() {
        return carDamageInsuredZero;
    }

    public void setCarDamageInsuredZero(String carDamageInsuredZero) {
        this.carDamageInsuredZero = carDamageInsuredZero;
    }

    public String getRenewalName() {
        return renewalName;
    }

    public void setRenewalName(String renewalName) {
        this.renewalName = renewalName;
    }

    public String getDriverLiabilityInsured() {
        return driverLiabilityInsured;
    }

    public void setDriverLiabilityInsured(String driverLiabilityInsured) {
        this.driverLiabilityInsured = driverLiabilityInsured;
    }

    public String getPassengerLiabilityInsured() {
        return passengerLiabilityInsured;
    }

    public void setPassengerLiabilityInsured(String passengerLiabilityInsured) {
        this.passengerLiabilityInsured = passengerLiabilityInsured;
    }

    public BigDecimal getRebateRatio() {
        return rebateRatio;
    }

    public void setRebateRatio(BigDecimal rebateRatio) {
        this.rebateRatio = rebateRatio;
    }

    public BigDecimal getRebateRatioZero() {
        return rebateRatioZero;
    }

    public void setRebateRatioZero(BigDecimal rebateRatioZero) {
        this.rebateRatioZero = rebateRatioZero;
    }

    public String getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer;
    }

    public Integer getSignFee() {
        return signFee;
    }

    public void setSignFee(Integer signFee) {
        this.signFee = signFee;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
