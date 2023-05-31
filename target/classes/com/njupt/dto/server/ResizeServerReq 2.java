package com.njupt.dto.server;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResizeServerReq implements Serializable {

    private String serverId;

    @ApiModelProperty("副本数，必须大于等于当前副本数")
    private String flavorRef;

}
