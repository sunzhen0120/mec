package com.njupt.controller;

import com.njupt.dto.network.CreateNetworkReq;
import com.njupt.dto.network.CreateNetworksReq;
import com.njupt.dto.network.CreateSubNetworkReq;
import com.njupt.res.CommonResult;
import com.njupt.service.NetworkService;
import com.njupt.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wlj
 * @version 1.0
 * @date 2022/8/31
 */
@RestController
@RequestMapping("network")
public class NetworkController {

    @Autowired
    private NetworkService networkService;

    @PostMapping("selectNetworks")
    public void getNetworks(HttpServletResponse response){
        CommonResult commonResult = networkService.selectNetworks();
        RestUtil.printData(response,commonResult);
    }

    @PostMapping("selectNetworkById/{networkId}")
    public void getNetwork(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable("networkId") String networkId){

        CommonResult commonResult = networkService.selectNetworkById(networkId);
        RestUtil.printData(response,commonResult);
    }

    @PostMapping("createNetwork")
    public void insertNetwork(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody CreateNetworkReq createNetworkReq){
        CommonResult commonResult = networkService.createNetwork(createNetworkReq);

        RestUtil.printData(response,commonResult);
    }


    @PostMapping("createSubNet")
    public void createSubNet(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody CreateSubNetworkReq createSubNetworkReq){
        CommonResult commonResult = networkService.createSubNet(createSubNetworkReq);

        RestUtil.printData(response,commonResult);
    }

    @PostMapping("bulkCreateNetworks")
    public void createNetworks(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody CreateNetworksReq createNetworksReq){
        CommonResult commonResult = networkService.bulkCreateNetworks(createNetworksReq);
        RestUtil.printData(response,commonResult);
    }

    @PostMapping("changeNetwork/{networkId}")
    public void updateNetworks(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody CreateNetworkReq createNetworkReq,
                               @PathVariable("networkId") String networkId){
        CommonResult commonResult = networkService.updateNetwork(createNetworkReq,networkId);

        RestUtil.printData(response,commonResult);
    }

    @PostMapping("deleteNetwork/{networkId}")
    public void deleteNetwork(HttpServletRequest request,HttpServletResponse response,
                              @PathVariable("networkId") String networkId){

        CommonResult commonResult = networkService.deleteNetwork(networkId);
        RestUtil.printData(response,commonResult);
    }

    @PostMapping("listSubnets")
    public void listSubnets(HttpServletResponse response){
        CommonResult commonResult = networkService.listSubnets();
        RestUtil.printData(response,commonResult);
    }

    @PostMapping("selectSubnetById/{subnetId}")
    public void getSubnet(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable("subnetId") String subnetId){

        CommonResult commonResult = networkService.selectSubnetById(subnetId);
        RestUtil.printData(response,commonResult);
    }

    @PostMapping("deleteSubnet/{subnetId}")
    public void deleteSubnet(HttpServletRequest request,HttpServletResponse response,
                             @PathVariable("subnetId") String subnetId){

        CommonResult commonResult = networkService.deleteSubnet(subnetId);
        RestUtil.printData(response,commonResult);
    }
}
