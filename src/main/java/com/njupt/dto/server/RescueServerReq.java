package com.njupt.dto.server;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RescueServerReq implements Serializable {

    private String serverId;

    @ApiModelProperty("a new password(Optional)")
    private String adminPass;

    @ApiModelProperty(" If you omit an image reference, default is the base image reference.(Optional)")
    private String rescueImageRef;
}
