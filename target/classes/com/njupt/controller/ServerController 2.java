package com.njupt.controller;

import com.alibaba.fastjson.JSON;
import com.njupt.dto.server.CreateServerReq;
import com.njupt.dto.server.*;
import com.njupt.res.CommonResult;
import com.njupt.service.ServerService;
import com.njupt.util.RestUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("server")
public class ServerController {

    @Autowired
    private ServerService serverService;

    @PostMapping("getServerList")
    public void getServerList(HttpServletRequest request, HttpServletResponse response) {
        CommonResult commonResult = serverService.getServerList();
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("showServerDetail/{serverId}")
    public void getServerDetail(HttpServletRequest request, HttpServletResponse response,
                                @PathVariable("serverId") String serverId) {
        CommonResult commonResult = serverService.showServerDetail(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("deleteServer/{serverId}")
    public void deleteServer(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("serverId") String serverId) {
        CommonResult commonResult = serverService.deleteServer(serverId);
        RestUtil.printData(response, commonResult);
    }


    @PostMapping("forceDeleteServer/{serverId}")
    public void forceDeleteServer(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("serverId") String serverId) {
        CommonResult commonResult = serverService.forceDeleteServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("changeAdminPassword")
    public void changeAdminPassword(HttpServletRequest request, HttpServletResponse response,
                                    @RequestBody ChangePwdReq changePwdReq) {
        CommonResult commonResult = serverService.changeAdminPassword(changePwdReq);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("confirmResize")
    public void confirmResize(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody ConfirmReszieReq confirmReszieReq) {
        CommonResult commonResult = serverService.confirmReszie(confirmReszieReq);
        RestUtil.printData(response, commonResult);
    }

    //Creates an image from a server
    @PostMapping("createImage")
    public void createImage(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody CreateImageReq createImageReq) {
        CommonResult commonResult = serverService.createImage(createImageReq);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("pauseServer/{serverId}")
    public void pauseServer(HttpServletRequest request, HttpServletResponse response,
                            @PathVariable String serverId) {
        CommonResult commonResult = serverService.pauseServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("lockServer/{serverId}")
    public void lockServer(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable String serverId) {
        CommonResult commonResult = serverService.lockServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("unLockServer/{serverId}")
    @ApiOperation("Unlocks a locked server")
    public void unLockServer(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable String serverId) {
        CommonResult commonResult = serverService.unLockServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("unpauseServer/{serverId}")
    @ApiOperation("Unpauses a paused server and changes its status to ACTIVE")
    public void unpauseServer(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable String serverId) {
        CommonResult commonResult = serverService.unpauseServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("rebootServer/{serverId}")
    public void rebootServer(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable String serverId) {
        CommonResult commonResult = serverService.rebootServer(serverId);
        RestUtil.printData(response, commonResult);
    }


    @PostMapping("rescueServer")
    @ApiOperation("rescue server with specified image and new password if " +
            "not point image and password,use default to rescue")
    public void rescueServer(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody RescueServerReq rescueServerReq) {
        CommonResult commonResult = serverService.rescueServer(rescueServerReq);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("unrescueServer/{serverId}")
    @ApiOperation("Unrescues a server. Changes status to ACTIVE")
    public void unrescueServer(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable String serverId) {
        CommonResult commonResult = serverService.unrescueServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("startServer/{serverId}")
    @ApiOperation("Starts a stopped server and changes its status to ACTIVE.")
    public void startServer(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable String serverId) {
        CommonResult commonResult = serverService.startServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("stopServer/{serverId}")
    @ApiOperation("Stops a running server and changes its status to SHUTOFF")
    public void stopServer(HttpServletRequest request, HttpServletResponse response,
                            @PathVariable String serverId) {
        CommonResult commonResult = serverService.stopServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("suspend/{serverId}")
    @ApiOperation("Suspends a server and changes its status to SUSPENDED")
    public void suspend(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable String serverId) {
        CommonResult commonResult = serverService.suspendServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("resizeServer")
    @ApiOperation("resize  a server")
    public void resizeServer(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody ResizeServerReq resizeServerReq) {
        CommonResult commonResult = serverService.resizeServer(resizeServerReq);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("revertResizeServer/{serverId}")
    @ApiOperation("Cancels and reverts a pending resize action for a server")
    public void revertResizeServer(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable String serverId) {
        CommonResult commonResult = serverService.revertResizeServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("confirmResize/{serverId}")
    @ApiOperation("Confirms a pending resize action for a server.")
    public void confirmResize(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable String serverId) {
        CommonResult commonResult = serverService.confirmResizedServer(serverId);
        RestUtil.printData(response, commonResult);
    }


    @PostMapping("addSecurityGroup")
    @ApiOperation("Adds a security group to a server")
    public void addSecurityGroup(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody AddSecurityGroupReq addSecurityGroupReq) {
        CommonResult commonResult = serverService.addSecurityGroup(addSecurityGroupReq);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("removeSecurityGroup")
    @ApiOperation("remove a security group to a server")
    public void removeSecurityGroup(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable RemoveSecurityGroupReq removeSecurityGroupReq) {
        CommonResult commonResult = serverService.removeSecurityGroup(removeSecurityGroupReq);
        RestUtil.printData(response, commonResult);
    }


    @PostMapping("resumeSuspendServer/{serverId}")
    @ApiOperation("Resumes a suspended server and changes its status to ACTIVE")
    public void resumeSuspendServer(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable String serverId) {
        CommonResult commonResult = serverService.resumeSuspendServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("createFlavor")
    @ApiOperation("create a flavor")
    public void createFlavor(HttpServletResponse response,@RequestBody CreateFlavorReq createFlavorReq){
        CommonResult commonResult = serverService.createFlavor(createFlavorReq);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("getFlavorList")
    public void getFlavorList(HttpServletRequest request, HttpServletResponse response) {
        CommonResult commonResult = serverService.listFlavors();
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("createServer")
    @ApiOperation("create a server")
    public void createServer(HttpServletResponse response,@RequestBody CreateServerReq createServerReq){
//        CreateServerReq createServerReq = JSON.parseObject(data, CreateServerReq.class);
        CommonResult commonResult = serverService.createServer(createServerReq);
        RestUtil.printData(response, commonResult);
    }


    @PostMapping("migrateServer/{serverId}")
    @ApiOperation("Migrate a server")
    public void migrateServer(HttpServletResponse response,  @PathVariable String serverId){
        CommonResult commonResult = serverService.migrateServer(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PutMapping("updateServer")
    @ApiOperation("update a server")
    public void updateServer(HttpServletResponse response, @RequestBody UpdateServerReq updateServerReq){
        CommonResult commonResult = serverService.updateServer(updateServerReq);
        RestUtil.printData(response, commonResult);
    }


    @PostMapping("showConsoleOutput/{serverId}")
    @ApiOperation("Shows console output for a server")
    public void showConsoleOutput(HttpServletResponse response, @PathVariable String serverId){
        CommonResult commonResult = serverService.showConsoleOutput(serverId);
        RestUtil.printData(response, commonResult);
    }


    @PostMapping("ips/{serverId}")
    @ApiOperation("Lists IP addresses that are assigned to an instance.")
    public void listIps(HttpServletResponse response, @PathVariable String serverId){
        CommonResult commonResult = serverService.ips(serverId);
        RestUtil.printData(response, commonResult);
    }


    @PostMapping("metadata/{serverId}")
    @ApiOperation("Lists all metadata for a server")
    public void metadata(HttpServletResponse response, @PathVariable String serverId){
        CommonResult commonResult = serverService.metadata(serverId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("showFlavorDetails/{flavorId}")
    @ApiOperation("Shows details for a flavor.")
    public void showFlavorDetails(HttpServletResponse response, @PathVariable String flavorId){
        CommonResult commonResult = serverService.showFlavorDetails(flavorId);
        RestUtil.printData(response, commonResult);
    }
    


}
