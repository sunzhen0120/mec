package com.njupt.dto.container;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchImagesReq {
    @ApiModelProperty("镜像名")
    private String imageName;

    @ApiModelProperty("版本号")
    private String version;
}
