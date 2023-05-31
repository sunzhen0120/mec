package com.njupt.service.impl;

import cn.hutool.json.JSONObject;
import com.njupt.ResourceLoad;
import com.njupt.constant.ApiUrlConstant;
import com.njupt.res.CommonResult;
import com.njupt.service.TokenService;
import com.njupt.util.OpenStackRequestUtil;
import com.njupt.zoneinfo.ZoneInfoCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);

    //    @Value("${openstack.identity.baseurl}")
//    private String ZoneInfoCache.zoneMap.get(zoneCode).getIdentityUrl();
    @Autowired
    private HttpServletRequest request;

    @Override
    public CommonResult getTokenByUserNameAndPwd() {
        String zoneCode = request.getHeader("zoneCode");
        String url = ZoneInfoCache.zoneMap.get(zoneCode).getIdentityUrl() + ApiUrlConstant.KEYSTONE_AUTH_USER;
        String template = ResourceLoad.getTemplate(ResourceLoad.GET_TOKEN);
        String userId = ZoneInfoCache.zoneMap.get(zoneCode).getUserId();
        String projectId = ZoneInfoCache.zoneMap.get(zoneCode).getProjectId();
        String password1 = ZoneInfoCache.zoneMap.get(zoneCode).getPassword();
        template = template.replace("$PASSWORD", password1)
                .replace("$USERID", userId).replace("$PROJECTID", projectId);
        String token = OpenStackRequestUtil.sendGetTokenPost(url, template);
        log.info("getToken res is:{}", token);
        if (StringUtils.isNotBlank(token)) {
            return CommonResult.success(token);
        }
        return CommonResult.failed("token获取失败");
    }
}
