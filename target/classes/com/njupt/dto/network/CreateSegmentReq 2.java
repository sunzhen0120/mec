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
public class CreateSegmentReq implements Serializable {

    @ApiModelProperty(value = "physicalNetwork")
    private String physicalNetwork;

    @ApiModelProperty(value = "networkType: flat,vlan,vxlan,gre")
    private String physicalNetworkType;

    @ApiModelProperty(value = "segmentId")
    private Integer segmentId;
}
