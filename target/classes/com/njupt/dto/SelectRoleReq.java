package com.njupt.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SelectRoleReq implements Serializable {

    @ApiModelProperty("pageNum")
    private int pageNum;

    @ApiModelProperty("pageSize")
    private int pageSize;

}
