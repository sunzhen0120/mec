package com.njupt.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateUserReq {

    @ApiModelProperty("用户id")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long userId;

    @ApiModelProperty("角色id")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long roleId;

    @ApiModelProperty("用户名称")
    private String userName;

//    @ApiModelProperty("登录名称")
//    private String loginName;

//    @ApiModelProperty("密码")
//    private String password;


}
