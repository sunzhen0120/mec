package com.njupt.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateRoleReq implements Serializable {

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色描述")
    private String roleDescr;

    @ApiModelProperty("角色类型 0:管理员，1:普通用户")
    private String type;
}
