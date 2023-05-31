package com.njupt.dto.network;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Wlj
 * @version 1.0
 * @date 2022/8/30
 */

@Data
public class CreateNetworksReq implements Serializable {

    @ApiModelProperty(value = "networks")
    private Object networks;
}
