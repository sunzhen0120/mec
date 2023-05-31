package com.njupt.dto.container;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ConnectDockerReq {

    @ApiModelProperty("docker实例")
    private String dockerInstance;

}
