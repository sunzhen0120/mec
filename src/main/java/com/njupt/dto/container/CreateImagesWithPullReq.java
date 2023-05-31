package com.njupt.dto.container;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateImagesWithPullReq {

    @ApiModelProperty("镜像名")
    private String imageName;

    @ApiModelProperty("镜像版本号")
    private String version;
}
