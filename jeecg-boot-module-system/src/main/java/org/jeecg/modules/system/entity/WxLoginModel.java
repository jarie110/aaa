package org.jeecg.modules.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="小程序登录对象", description="小程序登录对象")
public class WxLoginModel {
   @ApiModelProperty(value = "账号")
    private String username;
   @ApiModelProperty(value = "密码")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }


}