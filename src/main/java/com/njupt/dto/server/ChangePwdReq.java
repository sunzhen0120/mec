package com.njupt.dto.server;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChangePwdReq implements Serializable {

    @ApiModelProperty("serverId")
    private String serverId;

    @ApiModelProperty("管理员密码")
    private String adminPwd;
}
