package com.njupt.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.njupt.constant.ApiUrlConstant;
import com.njupt.dto.port.CreatePortReq;
import com.njupt.res.CommonResult;
import com.njupt.service.PortService;
import com.njupt.util.OpenStackRequestUtil;
import com.njupt.zoneinfo.ZoneInfoCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class PortServiceImpl implements PortService {


//    @Value("http://10.166.38.48:9696")
//    private String ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() ;

    @Autowired
    private HttpServletRequest request;

    @Override
    public CommonResult selectPorts() {
        String zoneCode = request.getHeader("zoneCode");
        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() + ApiUrlConstant.NETWORK_PORT;

        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(response);
        return CommonResult.success(jsonObject);

    }

    @Override
    public CommonResult createPort(CreatePortReq createPortReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl()  + ApiUrlConstant.NETWORK_PORT;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }

        Object port = createPortReq.getPort();

        HashMap bodyMap = new HashMap<>((Map) port);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("port", bodyMap);
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(paramMap), token);
        if (StringUtils.equals(dataMap.get("code"), "201")) {
            String message = dataMap.get("message");
            return CommonResult.success(JSON.parseObject(message));
        }
        return CommonResult.failed(dataMap.get("message"));


    }


    @Override
    public CommonResult deletePort(String portID) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl()  + ApiUrlConstant.NETWORK_PORT_DELETE + portID;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpDeleteWithToken204(url, token);
        if (StringUtils.equals(dataMap.get("code"), "200")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));
    }


}
