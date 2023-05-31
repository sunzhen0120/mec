package com.njupt.dto.server;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateServerReq implements Serializable {

    @ApiModelProperty("serverId")
    private String serverId;

    @ApiModelProperty("accessIPv4")
    private String accessIPv4;

    @ApiModelProperty("accessIPv6")
    private String accessIPv6;

    @ApiModelProperty("server name")
    private String name;

    @ApiModelProperty("description")
    private String description;

    private List<String> securityGroups;
}
