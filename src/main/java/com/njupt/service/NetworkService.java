package com.njupt.service;

import com.njupt.dto.network.CreateNetworkReq;
import com.njupt.dto.network.CreateNetworksReq;
import com.njupt.dto.network.CreateSubNetworkReq;
import com.njupt.res.CommonResult;

/**
 * @author Wlj
 * @version 1.0
 * @date 2022/8/30
 */
public interface NetworkService {

    CommonResult selectNetworks();

    CommonResult selectNetworkById(String networkId);

    CommonResult updateNetwork(CreateNetworkReq createNetworkReq,String networkId);

    CommonResult createNetwork(CreateNetworkReq createNetworkReq);

    CommonResult createSubNet(CreateSubNetworkReq createSubNetworkReq);

    CommonResult bulkCreateNetworks(CreateNetworksReq createNetworksReq);

    CommonResult deleteNetwork(String networkID);

    CommonResult listSubnets();

    CommonResult deleteSubnet(String subnetID);

    CommonResult selectSubnetById(String subnetId);
}
