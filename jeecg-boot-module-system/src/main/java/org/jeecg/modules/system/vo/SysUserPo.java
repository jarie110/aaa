package org.jeecg.modules.system.vo;

public class SysUserPo {
    private String oldPassword;
    private String uid;
    private String newPassword;

    public SysUserPo() {
    }

    public SysUserPo(String oldPassword, String uid, String newPassword) {
        this.oldPassword = oldPassword;
        this.uid = uid;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
