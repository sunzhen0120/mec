package com.njupt.dto.network;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Wlj
 * @version 1.0
 * @date 2022/8/30
 */

@Data
public class CreateNetworkReq implements Serializable {

    @ApiModelProperty(value = "networkName")
    private String networkName;

}
