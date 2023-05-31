package com.njupt.dto.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateImageGlanceReq implements Serializable {
    @ApiModelProperty("镜像容器格式")
    @JsonProperty("container_format")
    private String containerFormat;

    @JsonProperty("disk_format")
    private String diskFormat;

    private String name;

    private String id;


}
