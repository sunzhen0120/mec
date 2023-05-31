package com.njupt.dto.server;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateServerReq implements Serializable {
//
//    @ApiModelProperty("ipV4")
//    private String accessIPv4;
//
//    @ApiModelProperty("ipV6")
//    private String accessIPv6;

    @ApiModelProperty("server name")
    private String name;

    @ApiModelProperty("The UUID of the image to use for your server instance")
    private String imageRef;
//
//    @ApiModelProperty("The flavor reference")
//    private String flavorRef;

    @ApiModelProperty("网络模块")
    private CreateNetworkReq networkReq;

    @ApiModelProperty("虚拟机资源")
    private CreateFlavorReq flavorReq;


//    private String availability_zone;

//    private List<CreateServerReqSubSecurity> securityGroups;
}
