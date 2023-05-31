package com.njupt.dto.server;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateImageReq implements Serializable {

    @ApiModelProperty("serverId")
    private String serverId;

    @ApiModelProperty("imageName")
    private String imageName;
}
