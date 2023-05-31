package com.njupt.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.njupt.constant.ApiUrlConstant;
import com.njupt.dto.image.CreateImageGlanceReq;
import com.njupt.res.CommonResult;
import com.njupt.service.ImageService;
import com.njupt.util.OpenStackRequestUtil;
import com.njupt.zoneinfo.ZoneInfoCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

//    @Value("${openstack.image.ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl()}")
//    private String ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl();

    @Autowired
    private HttpServletRequest request;


    @Override
    public CommonResult createImage(InputStream inputStream, String containerFormat, String diskFormat, String name) {
        String zoneCode = request.getHeader("zoneCode");
        String url = ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl() + ApiUrlConstant.GLANCE_GENERAL_IMAGE;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("container_format", containerFormat);
        paramMap.put("disk_format", diskFormat);
        paramMap.put("name", name);
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithToken(url, JSON.toJSONString(paramMap), token);
        if (StringUtils.equals(dataMap.get("code"), "201")) {
            JSONObject jsonObject = JSON.parseObject(dataMap.get("message"));
            String id = String.valueOf(jsonObject.get("id"));
            new Thread(() -> {
                String replace = ApiUrlConstant.GLANCE_UPLOAD_BINARYDATA.replace("{IMAGEID}", id);
                String sRl = ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl() + replace;
                OpenStackRequestUtil.sendHttpPutWithFile(sRl, inputStream, token);
            }).start();
            return CommonResult.success(id,"镜像创建中");
        }
        return CommonResult.failed(dataMap.get("message"));
    }


    @Override
    public CommonResult showImageDetail(String imageId) {
        String zoneCode = request.getHeader("zoneCode");
        String url = ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl() + ApiUrlConstant.GLANCE_GENERAL_CHANGE_IMAGE + imageId;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(response);
        return CommonResult.success(jsonObject);
    }

    @Override
    public CommonResult showImagesList() {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl() + ApiUrlConstant.GLANCE_GENERAL_IMAGE;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(response);
        return CommonResult.success(jsonObject);
    }

    @Override
    public CommonResult deleteImage(String imageId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl() + ApiUrlConstant.GLANCE_GENERAL_CHANGE_IMAGE + imageId;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpDeleteWithTokenForImage(url, token);
        if (StringUtils.equals(dataMap.get("code"), "204")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult deactivateImage(String imageId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl() + ApiUrlConstant.GLANCE_GENERAL_CHANGE_IMAGE + imageId + ApiUrlConstant.GLANCE_DEACTIVATE_IMAGE_SUFFIX;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithTokenWithoutParam(url, token);
        if (StringUtils.equals(dataMap.get("code"), "204")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult reactivateImage(String imageId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl() + ApiUrlConstant.GLANCE_GENERAL_CHANGE_IMAGE + imageId + ApiUrlConstant.GLANCE_REACTIVATE_IMAGE_SUFFIX;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPostWithTokenWithoutParam(url, token);
        if (StringUtils.equals(dataMap.get("code"), "204")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult uploadImageData(String imageId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl() + ApiUrlConstant.GLANCE_GENERAL_CHANGE_IMAGE + imageId + ApiUrlConstant.GLANCE_UPLOAD_OR_DOWNLOAD_IMAGE;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPutWithTokenForUploadImageData(url, token);
        if (StringUtils.equals(dataMap.get("code"), "204")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));
    }

    @Override
    public CommonResult downloadImageData(String imageId) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl() + ApiUrlConstant.GLANCE_GENERAL_CHANGE_IMAGE + imageId + ApiUrlConstant.GLANCE_UPLOAD_OR_DOWNLOAD_IMAGE;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        String response = OpenStackRequestUtil.sendHttpGetWithToken(url, token);
        JSONObject jsonObject = JSON.parseObject(response);
        return CommonResult.success(jsonObject);
    }

    @Override
    public CommonResult addImageTag(String imageId, String tag) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl() + ApiUrlConstant.GLANCE_GENERAL_CHANGE_IMAGE + imageId + ApiUrlConstant.GLANCE_ADD_OR_DELETE_TAG + tag;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return CommonResult.failed("无授权信息");
        }
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpPutWithToken(url, token);
        if (StringUtils.equals(dataMap.get("code"), "204")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));

    }

    @Override
    public CommonResult deleteImageTag(String imageId, String tag) {
        String zoneCode = request.getHeader("zoneCode");

        String url = ZoneInfoCache.zoneMap.get(zoneCode).getImageUrl() + ApiUrlConstant.GLANCE_GENERAL_CHANGE_IMAGE + imageId + ApiUrlConstant.GLANCE_ADD_OR_DELETE_TAG + tag;
        String token = request.getHeader("token");
        Map<String, String> dataMap = OpenStackRequestUtil.sendHttpDeleteWithTokenForImage(url, token);
        if (StringUtils.equals(dataMap.get("code"), "204")) {
            return CommonResult.success();
        }
        return CommonResult.failed(dataMap.get("message"));
    }

}
