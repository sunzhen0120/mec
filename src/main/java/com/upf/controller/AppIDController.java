package com.upf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upf.dto.AppIdDto;
import com.upf.dto.NfidDto;
import com.upf.dto.ReslutIndexData;
import com.upf.dto.ResultData;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

import static com.upf.Parameters.StartURL;
import static com.upf.Parameters.StartURL2;
import static com.upf.SendWithJson.Delete.DeleteWithJson.DeleteDataWithJson;
import static com.upf.SendWithJson.Get.GetWithJson.GetDataWithJson;
import static com.upf.SendWithJson.Post.PostWithJson.PostDataWithJson;


@RestController
@RequestMapping("AppID")
public class AppIDController {


    //添加
    @PostMapping("/postAppID")
    public ResultData PostAppID(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestBody AppIdDto appIdDto) throws IOException {

        ResultData postAppIDReturnDto = new ResultData();
        Pattern app_idPattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
        if (app_idPattern.matcher(appIdDto.getApp_id()).matches()) {

            String res = PostDataWithJson(StartURL + "coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/ids&nfId=", JSON.toJSONString(appIdDto));
//
            JSONObject jsonObject = JSON.parseObject(res);

            if (jsonObject.getString("result").equals("OK")) {
                postAppIDReturnDto.setResult(jsonObject.getString("result"));
                postAppIDReturnDto.setData(jsonObject.getJSONObject("data"));
                //error设置为空
            } else {
                /**
                 * 如果错误，填充error
                 */
                postAppIDReturnDto.setResult("FAIL");
                postAppIDReturnDto.setData(jsonObject.getJSONObject("data"));
                postAppIDReturnDto.setError(jsonObject.getString("error"));
            }
            return postAppIDReturnDto;
        } else {
            postAppIDReturnDto.setResult("FAIL");
            return postAppIDReturnDto;
        }
    }

    //查询 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/ids&nfId="
    @PostMapping("/getAppID")
    public ReslutIndexData GetAppID(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody AppIdDto appIdDto) throws IOException {

        //返回的值
        ReslutIndexData getAppIDReturnDto = new ReslutIndexData();

        Pattern app_idPattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
        if (app_idPattern.matcher(appIdDto.getApp_id()).matches() || appIdDto.getApp_id() == "null") {
            String res = GetDataWithJson(StartURL + "coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/ids&nfId=", JSON.toJSONString(appIdDto));

            JSONObject jsonObject = JSON.parseObject(res);

            if (jsonObject.getString("result").equals("OK")) {
                getAppIDReturnDto.setResult(jsonObject.getString("result"));
                getAppIDReturnDto.setIndex(jsonObject.getJSONObject("index"));
                getAppIDReturnDto.setData(jsonObject.getJSONArray("data"));
                //error设置为空
            } else {
                /**
                 * 如果错误，填充error
                 */
                getAppIDReturnDto.setResult("FAIL");
                getAppIDReturnDto.setIndex(jsonObject.getJSONObject("index"));
                getAppIDReturnDto.setError(jsonObject.getString("error"));
            }
            return getAppIDReturnDto;
        } else {
            getAppIDReturnDto.setResult("FAIL");
            return getAppIDReturnDto;
        }

    }

    //删除 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/ids&nfId="
    @PostMapping("/deleteAppID")
    public ResultData DeleteAppID(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestBody AppIdDto appIdDto) throws IOException {

        //返回的值
        ResultData deleteAppIDReturnDto = new ResultData();

        Pattern app_idPattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
        if (app_idPattern.matcher(appIdDto.getApp_id()).matches()) {
            String res = DeleteDataWithJson(StartURL + "coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/ids&nfId=", JSON.toJSONString(appIdDto));

            JSONObject jsonObject = JSON.parseObject(res);

            if (jsonObject.getString("result").equals("OK")) {
                deleteAppIDReturnDto.setResult(jsonObject.getString("result"));
                deleteAppIDReturnDto.setData(jsonObject.getJSONObject("data"));
                //error设置为空
            } else {
                /**
                 * 如果错误，填充error
                 */
                deleteAppIDReturnDto.setResult("FAIL");
                deleteAppIDReturnDto.setData(jsonObject.getJSONObject("data"));
                deleteAppIDReturnDto.setError(jsonObject.getString("error"));
            }
            return deleteAppIDReturnDto;
        } else {
            deleteAppIDReturnDto.setResult("FAIL");
            return deleteAppIDReturnDto;
        }
    }
}


