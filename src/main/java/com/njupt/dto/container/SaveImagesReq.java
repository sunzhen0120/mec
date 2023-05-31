package com.njupt.dto.container;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SaveImagesReq {

    @ApiModelProperty("容器name")
    private String imagesName;

    @ApiModelProperty("镜像版本号")
    private String version;
}
