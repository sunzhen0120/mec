package com.njupt.dto.container;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CopyArchiveToContainerReq {

    @ApiModelProperty("容器id")
    private String containerId;

    @ApiModelProperty("本地资源路径")
    private String resource;

    @ApiModelProperty("服务器资源路径")
    private String remote;
}
