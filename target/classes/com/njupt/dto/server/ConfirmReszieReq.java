package com.njupt.dto.server;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ConfirmReszieReq implements Serializable {

    @ApiModelProperty("serverId")
    private String serverId;

    @ApiModelProperty("confirmResize")
    private String confirmResize;
}
