package com.njupt.dto.container;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RemoveImagesReq {

    @ApiModelProperty("容器id")
    private String imagesId;

    @ApiModelProperty("镜像版本号")
    private String version;
}
