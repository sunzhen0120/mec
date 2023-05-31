package com.njupt.dto.server;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateFlavorReq implements Serializable {

    private String name;

    @ApiModelProperty(value = "ram 大小 单位 mb")
    private Integer ram;

    @ApiModelProperty(value = "disk 大小 单位 GiB")
    private Integer disk;

    @ApiModelProperty(value = "The number of virtual CPUs that will be allocated to the server.")
    private Integer vcpus;





}
