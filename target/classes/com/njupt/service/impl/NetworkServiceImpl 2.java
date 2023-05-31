package com.njupt.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.njupt.constant.ApiUrlConstant;
import com.njupt.dto.network.CreateNetworkReq;
import com.njupt.dto.network.CreateNetworksReq;
import com.njupt.dto.network.CreateSegmentReq;
import com.njupt.dto.network.CreateSubNetworkReq;
import com.njupt.res.CommonResult;
import com.njupt.service.NetworkService;
import com.njupt.util.OpenStackRequestUtil;
import com.njupt.zoneinfo.ZoneInfoCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Wlj
 * @version 1.0
 * @date 2022/8/31
 */
@Service
public class NetworkServiceImpl implements NetworkService {

//    @Value("${openstack.network.ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl()}")
//    private String ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl();

    @Autowired
    private HttpServletRequest request;


    @Override
    public CommonResult selectNetworks() {
        String zoneCode = request.getHeader("zoneCode");
        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() + ApiUrlConstant.NETWORK_VLAN;

        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(response);
        return CommonResult.success(jsonObject);

    }

    @Override
    public CommonResult selectNetworkById(String networkId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() + ApiUrlConstant.NETWORK_VLAN_DETAIL + networkId;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(response);
        return CommonResult.success(jsonObject);
    }

    @Override
    public CommonResult updateNetwork(CreateNetworkReq createNetworkReq, String networkId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() + ApiUrlConstant.NETWORK_VLAN_UPDATE + networkId;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("name",createNetworkReq.getNetworkName());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("network", bodyMap);

        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPutWithTokenNetwork(url, JSON.toJSONString(paramMap), token);
        if (StringUtils.equals(dataMap.get("code"), "200")) {
            String message = dataMap.get("message");
            return CommonResult.success(JSON.parseObject(message));
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult createNetwork(CreateNetworkReq createNetworkReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() + ApiUrlConstant.NETWORK_VLAN;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", createNetworkReq.getNetworkName());

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("network", map);

        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(paramMap), token);
        if (StringUtils.equals(dataMap.get("code"), "201")) {
            String message = dataMap.get("message");
            return CommonResult.success(JSON.parseObject(message));
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult createSubNet(CreateSubNetworkReq createSubNetworkReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() + ApiUrlConstant.NETWORK_SUBNETS;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("network_id", createSubNetworkReq.getNetworkId());
        map.put("ip_version", createSubNetworkReq.getIpVersion());
        map.put("cidr", createSubNetworkReq.getCidr());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("subnet", map);

        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(paramMap), token);
        if (StringUtils.equals(dataMap.get("code"), "201")) {
            String message = dataMap.get("message");
            return CommonResult.success(JSON.parseObject(message));
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult bulkCreateNetworks(CreateNetworksReq createNetworksReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() + ApiUrlConstant.NETWORK_VLAN;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }

        Object networks = createNetworksReq.getNetworks();

        List<Object> networkLists = new ArrayList<>((Collection) networks);

        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("networks", networkLists);
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(paramMap), token);
        if (StringUtils.equals(dataMap.get("code"), "201")) {
            String message = dataMap.get("message");
            return CommonResult.success(JSON.parseObject(message));
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult deleteNetwork(String networkID) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() + ApiUrlConstant.NETWORK_VLAN_DELETE + networkID;
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

    @Override
    public CommonResult listSubnets() {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() + ApiUrlConstant.NETWORK_SUBNETS;

        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(response);
        return CommonResult.success(jsonObject);
    }

    @Override
    public CommonResult selectSubnetById(String subnetId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() + ApiUrlConstant.NETWORK_SUBNETS_DELETE + subnetId;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(response);
        return CommonResult.success(jsonObject);
    }

    @Override
    public CommonResult deleteSubnet(String subnetID) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getNetworkUrl() + ApiUrlConstant.NETWORK_SUBNETS_DELETE + subnetID;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpDeleteWithToken204(url, token);
        if (StringUtils.equals(dataMap.get("code"), "204")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));
    }
}
