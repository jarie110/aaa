package org.jeecg.modules.message.entity;

public class MsgPo {
    private String nowDay;
    private String uid;

    public MsgPo() {
    }

    public MsgPo(String nowDay, String uid) {
        this.nowDay = nowDay;
        this.uid = uid;
    }

    public String getNowDay() {
        return nowDay;
    }

    public void setNowDay(String nowDay) {
        this.nowDay = nowDay;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
