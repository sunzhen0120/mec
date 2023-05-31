package com.njupt.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.njupt.constant.ApiUrlConstant;
import com.njupt.dto.server.CreateServerReq;
import com.njupt.dto.server.*;
import com.njupt.res.CommonResult;
import com.njupt.service.ServerService;
import com.njupt.util.OpenStackRequestUtil;
import com.njupt.zoneinfo.ZoneInfoCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServerServiceImpl implements ServerService {

//    @Value("${openstack.server.baseurl}")
//    private String ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl();

    @Autowired
    private HttpServletRequest request;

    @Override
    public CommonResult getServerList() {
        String zoneCode = request.getHeader("zoneCode");
        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_LIST_SERVERS;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(response);
        return CommonResult.success(jsonObject);
    }

    @Override
    public CommonResult showServerDetail(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SHOW_SERVER_DETAIL + serverId;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        return CommonResult.success(JSON.parseObject(response));
    }

    @Override
    public CommonResult deleteServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SHOW_SERVER_DELETE + serverId;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpDeleteWithToken(url, token);
        if (StringUtils.equals(dataMap.get("code"), "200")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult forceDeleteServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"forceDelete\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult changeAdminPassword(ChangePwdReq changePwdReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", changePwdReq.getServerId());
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, Object> adminMap = new HashMap<>();
        adminMap.put("adminPass", changePwdReq.getAdminPwd());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("changePassword", adminMap);

        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(paramMap), token);
        if (StringUtils.equals(dataMap.get("code"), "200")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult confirmReszie(ConfirmReszieReq confirmReszieReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", confirmReszieReq.getServerId());
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"confirmResize\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "204")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));
    }


    @Override
    public CommonResult createImage(CreateImageReq createImageReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", createImageReq.getServerId());
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, Object> nameMap = new HashMap<>();
        nameMap.put("name", createImageReq.getImageName());

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("createImage", nameMap);
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(paramMap), token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult pauseServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"pause\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult lockServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"lock\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult unLockServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"unlock\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult unpauseServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"unpause\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult rebootServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("type", "HARD");
        CommonResult commonResult = showServerDetail(serverId);
        if (CommonResult.isSuccess(commonResult)) {
            String value = String.valueOf(commonResult.getData());
            String status = String.valueOf(JSONPath.read(value, "$.server.status"));
            if (StringUtils.equals(status, "ACTIVE")) {
                paramMap.put("type", "SOFT");
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("reboot", paramMap);
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(map), token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult rescueServer(RescueServerReq rescueServerReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", rescueServerReq.getServerId());
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, Object> paramMap = new HashMap<>();
        if (!StringUtils.isBlank(rescueServerReq.getAdminPass())) {
            paramMap.put("adminPass", rescueServerReq.getAdminPass());
        }
        if (!StringUtils.isBlank(rescueServerReq.getRescueImageRef())) {
            paramMap.put("rescue_image_ref", rescueServerReq.getRescueImageRef());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rescue", paramMap);
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(map), token);
        if (StringUtils.equals(dataMap.get("code"), "200")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult unrescueServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"unrescue\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult startServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"os-start\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult stopServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"os-stop\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult suspendServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"suspend\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult resizeServer(ResizeServerReq resizeServerReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", resizeServerReq.getServerId());
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("flavorRef", resizeServerReq.getFlavorRef());
        map.put("OS-DCF:diskConfig", "AUTO");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("resize", map);
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(paramMap), token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult revertResizeServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"revertResize\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult confirmResizedServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"confirmResize\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "204")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult addSecurityGroup(AddSecurityGroupReq addSecurityGroupReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", addSecurityGroupReq.getServerId());
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("name", addSecurityGroupReq.getName());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("addSecurityGroup", map);
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(paramMap), token);
        if (StringUtils.equals(dataMap.get("code"), "200")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }


    @Override
    public CommonResult removeSecurityGroup(RemoveSecurityGroupReq removeSecurityGroupReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", removeSecurityGroupReq.getServerId());
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("name", removeSecurityGroupReq.getName());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("removeSecurityGroup", map);
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(paramMap), token);
        if (StringUtils.equals(dataMap.get("code"), "200")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult resumeSuspendServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"resume\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult createFlavor(CreateFlavorReq createFlavorReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_FLAVORS;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }

        Map<String, Object> flavorMap = new HashMap<>();

        Map<String, Object> map = new HashMap<>();
        map.put("name", createFlavorReq.getName());
        map.put("ram", createFlavorReq.getRam());
        map.put("vcpus", createFlavorReq.getVcpus());
        map.put("disk", createFlavorReq.getDisk());
        flavorMap.put("flavor", map);

        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(flavorMap), token);
        if (StringUtils.equals(dataMap.get("code"), "200")) {
            JSONObject jsonObject = JSON.parseObject(dataMap.get("message"));
            return CommonResult.success(jsonObject);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult listFlavors() {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_FLAVORS;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(response);
        return CommonResult.success(jsonObject);
    }

    @Override
    public CommonResult showFlavorDetails(String flavorId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_FLAVORS_LIST + flavorId;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        return CommonResult.success(JSON.parseObject(response));
    }

    @Override
    public CommonResult migrateServer(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String param = "{\n" +
                "   \"migrate\": null\n" +
                "}";
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, param, token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult updateServer(UpdateServerReq updateServerReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_UPDATE_SERVER + updateServerReq.getServerId();
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("accessIPv4", updateServerReq.getAccessIPv4());
        map.put("accessIPv6", updateServerReq.getAccessIPv6());
        map.put("OS-DCF:diskConfig", "AUTO");
        map.put("name", updateServerReq.getName());
        map.put("description", updateServerReq.getDescription());

        Map<String, Object> _map = new HashMap<>();
        _map.put("server", map);
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPutWithTokenNetwork(url, JSON.toJSONString(_map), token);
        if (StringUtils.equals(dataMap.get("code"), "200")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult createServer(CreateServerReq createServerReq) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_CREATE_SERVER;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, Object> paramMap = new HashMap<>();

        createServerReq.getFlavorReq().setName(createServerReq.getName() + "-flavor");
        CommonResult flavor = createFlavor(createServerReq.getFlavorReq());
        String href;
        if (CommonResult.isSuccess(flavor)) {
            href = (String) JSONPath.read(JSON.toJSONString(flavor.getData()), "$.flavor.links[0].href");
        } else {
            return CommonResult.failed("flavor 创建失败");
        }
        List<Map<String, Object>> securityMap = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "default");
        securityMap.add(map);
        paramMap.put("security_groups", securityMap);

        paramMap.put("name", createServerReq.getName());
        paramMap.put("imageRef", createServerReq.getImageRef());
        paramMap.put("flavorRef", href);
        paramMap.put("OS-DCF:diskConfig", "AUTO");


        List<Map<String, String>> networks = new ArrayList<>();
        Map<String, String> network = new HashMap<>();
        network.put("uuid", createServerReq.getNetworkReq().getNetworkUid());
        networks.add(network);

        paramMap.put("networks", networks);
        Map<String, Object> _map = new HashMap<>();
        _map.put("server", paramMap);

        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(_map), token);
        if (StringUtils.equals(dataMap.get("code"), "202")) {
            String message = dataMap.get("message");
            return CommonResult.success(JSON.parseObject(message));
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult showConsoleOutput(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_ACTION;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("length", 50);

        Map<String, Object> _map = new HashMap<>();
        map.put("os-getConsoleOutput", map);

        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(_map), token);
        if (StringUtils.equals(dataMap.get("code"), "200")) {
            return CommonResult.success(dataMap);
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult ips(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_IPS;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String s = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(s);
        return CommonResult.success(jsonObject);
    }

    @Override
    public CommonResult metadata(String serverId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getServerUrl() + ApiUrlConstant.NOVA_SERVER_METADATA;
        url = url.replace("{server_id}", serverId);
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String s = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(s);
        return CommonResult.success(jsonObject);
    }
}
