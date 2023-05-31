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
public class CreateSubNetworkReq implements Serializable {

    @ApiModelProperty(value = "networkId")
    private String networkId;

    @ApiModelProperty(value = "ipVersion value is 4 or 6")
    private Integer ipVersion;

    @ApiModelProperty(value = "cidr")
    private String cidr;

}
