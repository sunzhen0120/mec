package com.njupt.dto.container;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateContainersReq {

    @ApiModelProperty("镜像名")
    private String imagesName;

    @ApiModelProperty("容器名")
    private String containerName;
}
