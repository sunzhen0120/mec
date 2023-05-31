package com.njupt.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SelectUserReq {

    @ApiModelProperty("pageNum")
    private Integer pageNum;

    @ApiModelProperty("pageSize")
    private Integer pageSize;




}
