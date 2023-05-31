package com.upf.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upf.dto.GetWhiteUrlListDto;
import com.upf.dto.ReslutIndexData;
import com.upf.dto.ResultData;
import com.upf.dto.WhiteUrlListDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

import static com.upf.Parameters.StartURL;
import static com.upf.SendWithJson.Delete.DeleteWithJson.DeleteDataWithJson;
import static com.upf.SendWithJson.Get.GetWithJson.GetDataWithJson;
import static com.upf.SendWithJson.Post.PostWithJson.PostDataWithJson;
import static com.upf.SendWithJson.Put.PutWithJson.PutDataWithJson;

@RestController
@RequestMapping("WhiterUrlListController")
public class WhiteUrlListController {

    /*
    ****添加postWhiterUrlListController
     */
    @PostMapping("/postWhiterUrlListController")
    public ResultData PostWhiterUrlList(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestBody WhiteUrlListDto whiteUrlListDto) throws IOException {

        ResultData resultData = new ResultData();

        Pattern url_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        Pattern urlPattern=Pattern.compile("((http|https)://)?(www.)?[a-zA-Z0-9@:%._\\\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\\\+~#?&//=]*)");
        if(url_namePattern.matcher(whiteUrlListDto.getUrl_name()).matches()&&
                urlPattern.matcher(whiteUrlListDto.getUrl()).matches()){
            String res = PostDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/white-url-lists&nfId=", JSON.toJSONString(whiteUrlListDto));

            JSONObject jsonObject = JSON.parseObject(res);
            //成功情况，error设置为空
            if(jsonObject.getString("result").equals("OK")){
                resultData.setResult(jsonObject.getString("result"));
                resultData.setData(jsonObject.getJSONObject("data"));

            }else{//失败情况，填充error，成功的关键字设置为空
                resultData.setResult("FAIL");
                resultData.setData(jsonObject.getJSONObject("data"));
                resultData.setError(jsonObject.getString("error"));
            }
            return resultData;
        }else{
            resultData.setResult("FAIL");
            return resultData;
        }

    }

    /*
     ****查询postWhiterUrlListController
     */
    @PostMapping("/getWhiterUrlListController")
    public ReslutIndexData GetWhiterUrlList(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestBody GetWhiteUrlListDto getWhiteUrlListDto) throws IOException {

        ReslutIndexData reslutIndexData = new ReslutIndexData();

        Pattern url_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        if(url_namePattern.matcher(getWhiteUrlListDto.getUrl_name()).matches() || getWhiteUrlListDto.getUrl_name().equals("null")){
            String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/white-url-lists&nfId=", JSON.toJSONString(getWhiteUrlListDto));

            JSONObject jsonObject = JSON.parseObject(res);

            //成功情况，error设置为空
            if(jsonObject.getString("result").equals("OK")){
                reslutIndexData.setResult(jsonObject.getString("result"));
                reslutIndexData.setIndex(jsonObject.getJSONObject("index"));
                reslutIndexData.setData(jsonObject.getJSONArray("data"));

            }else{//失败情况，填充error，成功的关键字设置为空
                reslutIndexData.setResult("FAIL");
                reslutIndexData.setError(jsonObject.getString("error"));
            }
            //给前端返回数据
            return reslutIndexData;
        }else {
            reslutIndexData.setResult("FAIL");
            return reslutIndexData;
        }
    }

    /*
     ****修改postWhiterUrlListController
     */
    @PostMapping("/putWhiterUrlListController")
    public ResultData PutWhiterUrlList(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestBody WhiteUrlListDto whiteUrlListDto) throws IOException {

        ResultData resultData = new ResultData();

        Pattern url_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        Pattern urlPattern=Pattern.compile("((http|https)://)?(www.)?[a-zA-Z0-9@:%._\\\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\\\+~#?&//=]*)");
        if(url_namePattern.matcher(whiteUrlListDto.getUrl_name()).matches()&&
                urlPattern.matcher(whiteUrlListDto.getUrl()).matches()){
            String res = PutDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/white-url-lists&nfId=", JSON.toJSONString(whiteUrlListDto));

            JSONObject jsonObject = JSON.parseObject(res);
            //成功情况，error设置为空
            if (jsonObject.getString("result").equals("OK")) {
                resultData.setResult(jsonObject.getString("result"));
                resultData.setData(jsonObject.getJSONObject("data"));

            } else {//失败情况，填充error，成功的关键字设置为空
                resultData.setResult("FAIL");
                resultData.setData(jsonObject.getJSONObject("data"));
                resultData.setError(jsonObject.getString("error"));
            }
            return resultData;
        }else{
            resultData.setResult("FAIL");
            return resultData;
        }


    }

    /*
     ****删除postWhiterUrlListController
     */
    @PostMapping("/deleteWhiterUrlListController")
    public ResultData DeleteWhiterUrlList(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestBody GetWhiteUrlListDto getWhiteUrlListDto) throws IOException {
        ResultData resultData = new ResultData();

        Pattern url_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        if(url_namePattern.matcher(getWhiteUrlListDto.getUrl_name()).matches()){
            String res = DeleteDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/white-url-lists&nfId=", JSON.toJSONString(getWhiteUrlListDto));

            JSONObject jsonObject = JSON.parseObject(res);
            //成功情况，error设置为空
            if (jsonObject.getString("result").equals("OK")) {
                resultData.setResult(jsonObject.getString("result"));
                resultData.setData(jsonObject.getJSONObject("data"));

            } else {//失败情况，填充error，成功的关键字设置为空
                resultData.setResult("FAIL");
                resultData.setData(jsonObject.getJSONObject("data"));
                resultData.setError(jsonObject.getString("error"));
            }
            return resultData;
        }else {
            resultData.setResult("FAIL");
            return resultData;
        }
    }
}
