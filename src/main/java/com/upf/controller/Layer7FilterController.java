package com.upf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upf.dto.*;
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
import static com.upf.utils.Judge.Two;

@RestController
@RequestMapping("Layer7FilterController")
public class Layer7FilterController {
    /*
     ****添加Layer7FilterController
     */
    @PostMapping("/postLayer7FilterController")
    public ResultData PostLayer34Filter(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestBody Layer7FilterDto layer7FilterDto) throws IOException {

        ResultData resultData = new ResultData();
        Pattern protocolPattern=Pattern.compile("^(ftp|rtsp|dns|mqtt|http|https)$");
        Pattern urlPattern=Pattern.compile("((http|https)://)?(www.)?[a-zA-Z0-9@:%._\\\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\\\+~#?&//=]*)");
        Pattern sub_filterPattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        Pattern domain_namePattern=Pattern.compile("^((?!-)[A-Za-z\\d-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$");
        Pattern filter_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        Pattern ref_app_rule_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");


        if(layer7FilterDto.getProtocol().equals("https")){
            if(!(domain_namePattern.matcher(layer7FilterDto.getDomain_name()).matches() )) {
                resultData.setResult("FAIL");
                return resultData;
            }
        }

        if(Two(layer7FilterDto.getUse_well_known_port())&&
                protocolPattern.matcher(layer7FilterDto.getProtocol()).matches()&&
                urlPattern.matcher(layer7FilterDto.getUrl()).matches()&&
                //sub_filterPattern.matcher(sub_filter).matches()&&暂不支持
                filter_namePattern.matcher(layer7FilterDto.getFilter_name()).matches()&&
                ref_app_rule_namePattern.matcher(layer7FilterDto.getRef_app_rule_name()).matches()
        ){
            String res = PostDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/layer7-filters&nfId=", JSON.toJSONString(layer7FilterDto));

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
     ****查询Layer7FilterController
     */
    @PostMapping("/getLayer7FilterController")
    public ReslutIndexData GetLayer7Filter(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestBody LayerFilterDto layerFilterDto) throws IOException {

        ReslutIndexData reslutIndexData = new ReslutIndexData();

        Pattern filter_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");

        if(filter_namePattern.matcher(layerFilterDto.getFilter_name()).matches() || layerFilterDto.getFilter_name().equals("null")){
            String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/layer7-filters&nfId=", JSON.toJSONString(layerFilterDto));

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

        }else{
            reslutIndexData.setResult("FAIL");
            return reslutIndexData;
        }

    }
    /*
     ****修改Layer7FilterController
     */
    @PostMapping("/putLayer7FilterController")
    public ResultData PutLayer34Filter(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestBody Layer7FilterDto layer7FilterDto) throws IOException {

        ResultData resultData = new ResultData();
        Pattern protocolPattern=Pattern.compile("^(ftp|rtsp|dns|mqtt|http|https)$");
        Pattern urlPattern=Pattern.compile("((http|https)://)?(www.)?[a-zA-Z0-9@:%._\\\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\\\+~#?&//=]*)");
        Pattern sub_filterPattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        Pattern domain_namePattern=Pattern.compile("^((?!-)[A-Za-z\\d-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$");
        Pattern filter_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        Pattern ref_app_rule_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");


        if(layer7FilterDto.getProtocol().equals("https")){
            if(!(domain_namePattern.matcher(layer7FilterDto.getDomain_name()).matches() )) {
                resultData.setResult("FAIL");
                return resultData;
            }
        }
        if(Two(layer7FilterDto.getUse_well_known_port())&&
                protocolPattern.matcher(layer7FilterDto.getProtocol()).matches()&&
                urlPattern.matcher(layer7FilterDto.getUrl()).matches()&&
                //sub_filterPattern.matcher(sub_filter).matches()&&暂不支持
                filter_namePattern.matcher(layer7FilterDto.getFilter_name()).matches()&&
                ref_app_rule_namePattern.matcher(layer7FilterDto.getRef_app_rule_name()).matches()
        ){
            String res = PutDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/layer7-filters&nfId=", JSON.toJSONString(layer7FilterDto));

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
     ****删除Layer7FilterController
     */
    @PostMapping("/deleteLayer7FilterController")
    public ResultData DeleteLayer34Filter(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestBody LayerFilterDto layerFilterDto) throws IOException {

        ResultData resultData = new ResultData();

        Pattern filter_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");

        if(filter_namePattern.matcher(layerFilterDto.getFilter_name()).matches()){
            String res = DeleteDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/layer7-filters&nfId=", JSON.toJSONString(layerFilterDto));

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
}
