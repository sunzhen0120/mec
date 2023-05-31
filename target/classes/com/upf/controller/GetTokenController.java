package com.upf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.njupt.util.RestUtil;
import com.upf.dto.UserDto;
import com.upf.mapper.TUserMapper;
import com.upf.pojo.TUser;
import com.upf.pojo.TUserExample;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.upf.utils.Judge.JudegePassword;
import static com.upf.utils.Judge.JudegeUserName;

@RestController
@RequestMapping("GetToken")
public class GetTokenController {

    @Autowired
    private TUserMapper tUserMapper;

    @PostMapping("/getToken")
    public JSONObject GetLoginToken(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody UserDto UserDto) throws IOException {

        String username = UserDto.getUsername();
        String password = UserDto.getPassword();

        JSONObject loginJsonObject = new JSONObject();

        TUserExample tUserExample = new TUserExample();
        tUserExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<TUser> tUsers = tUserMapper.selectByExample(tUserExample);
        if (CollectionUtils.isEmpty(tUsers)) {
            return JSON.parseObject("{\n" +
                    "  \"result\"                  : \"FAIL\",\n" +
                    "  \"msg\"                  : \"……..\"\n" +
                    "}");
        }
        //处理用户名密码
        if (JudegeUserName(username) && JudegePassword(password)) {
            String postURL = "http://192.168.2.157:8500/v1/coreNetwork/auth/Login";
            PostMethod postMethod = null;
            postMethod = new PostMethod(postURL);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            //参数设置，需要注意的就是里边不能传NULL，要传空字符串

            NameValuePair[] loginData = {
                    new NameValuePair("username", username),
                    new NameValuePair("password", password)
            };
            postMethod.setRequestBody(loginData);
            org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
            int response1 = httpClient.executeMethod(postMethod); // 执行POST方法

            //获得输出结果并解析
            InputStream inputStream = postMethod.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();//测试时改一下试试


            String str = "";
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }

            loginJsonObject = JSON.parseObject(stringBuffer.toString());

        }
        return loginJsonObject;
    }
}
