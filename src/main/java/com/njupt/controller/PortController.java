package com.njupt.controller;


import com.njupt.dto.port.CreatePortReq;
import com.njupt.res.CommonResult;
import com.njupt.service.PortService;
import com.njupt.util.RestUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("ports")
@Api(tags = "端口管理", value = "端口管理")
public class PortController {

    @Autowired
    private PortService portService;

    @PostMapping("selectPorts")
    public void getPorts(HttpServletResponse response){
        CommonResult commonResult = portService.selectPorts();
        RestUtil.printData(response,commonResult);
    }

    @PostMapping("createPort")
    public void createPort(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody CreatePortReq createPortReq){
        CommonResult commonResult = portService.createPort(createPortReq);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping ("deletePort/{portId}")
    public void deleteServer(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("portId") String portId) {
        CommonResult commonResult = portService.deletePort(portId);
        RestUtil.printData(response, commonResult);
    }

}
