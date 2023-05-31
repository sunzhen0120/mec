package com.njupt.dto.server;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RemoveSecurityGroupReq implements Serializable {

    private String serverId;

    @ApiModelProperty("security group name")
    private String name;

}
