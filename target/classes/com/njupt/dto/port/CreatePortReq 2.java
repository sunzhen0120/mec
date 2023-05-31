package com.njupt.dto.port;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class CreatePortReq implements Serializable {

    @ApiModelProperty("port")
    private Object port;

}
