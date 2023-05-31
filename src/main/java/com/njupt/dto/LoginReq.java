package com.njupt.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginReq {

    @ApiModelProperty("登录名称")
    private String loginName;

    @ApiModelProperty("密码")
    private String password;


}
