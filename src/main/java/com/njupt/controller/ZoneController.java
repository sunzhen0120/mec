package com.njupt.controller;

import com.njupt.res.CommonResult;
import com.njupt.service.ZoneService;
import com.njupt.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("zone")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping("getZones")
    public void getZones(HttpServletRequest request, HttpServletResponse response) {
        CommonResult commonResult = zoneService.getZoneList();
        RestUtil.printData(response, commonResult);
    }


}
