package com.upf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upf.dto.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.upf.Parameters.StartURL;
import static com.upf.SendWithJson.Get.GetWithJson.GetDataWithJson;
import static com.upf.SendWithJson.Put.PutWithJson.PutDataWithJson;
import static com.upf.utils.Judge.Two;

@RestController
@RequestMapping("UpFunctionFeatures")
public class UpFunctionFeaturesController {
    /*
    ****DefaultUpFunctionFeatures查询
     */
    @PostMapping("/getDefaultUPFunctionFeatures")
    public ReslutIndexData GetDefaultUPFunctionFeatures(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestBody TokenNfidDto tokenNfiddto) throws IOException {

        ReslutIndexData UpFunctionFeatures=new ReslutIndexData();

        String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/up-func-features/default&nfId=", JSON.toJSONString(tokenNfiddto));

        JSONObject jsonObject = JSON.parseObject(res);

        //成功情况，error设置为空
        if(jsonObject.getString("result").equals("OK")){
            UpFunctionFeatures.setResult(jsonObject.getString("result"));
            UpFunctionFeatures.setIndex(jsonObject.getJSONObject("index"));
            UpFunctionFeatures.setData(jsonObject.getJSONArray("data"));

        }else{//失败情况，填充error，成功的关键字设置为空
            UpFunctionFeatures.setResult("FAIL");
            UpFunctionFeatures.setError(jsonObject.getString("error"));
        }
        //给前端返回数据
        return UpFunctionFeatures;
    }
    /*
     ****Additional-1UpFunctionFeatures查询
     */
    @PostMapping("/getAdditional-1UPFunctionFeatures")
    public ReslutIndexData GetAdditional1UPFunctionFeatures(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        @RequestBody TokenNfidDto tokenNfiddto) throws IOException {
        ReslutIndexData UpFunctionFeatures=new ReslutIndexData();

        String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/up-func-features/additional-1&nfId=", JSON.toJSONString(tokenNfiddto));

        JSONObject jsonObject = JSON.parseObject(res);
        //成功情况，error设置为空
        if(jsonObject.getString("result").equals("OK")){
            UpFunctionFeatures.setResult(jsonObject.getString("result"));
            UpFunctionFeatures.setIndex(jsonObject.getJSONObject("index"));
            UpFunctionFeatures.setData(jsonObject.getJSONArray("data"));

        }else{//失败情况，填充error，成功的关键字设置为空
            UpFunctionFeatures.setResult("FAIL");
            UpFunctionFeatures.setError(jsonObject.getString("error"));
        }
        //给前端返回数据
        return UpFunctionFeatures;
    }

    /*
     ****Additional-2UpFunctionFeatures查询
     */
    @PostMapping("/getAdditional-2UPFunctionFeatures")
    public ReslutIndexData GetAdditional2UPFunctionFeatures(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           @RequestBody TokenNfidDto tokenNfiddto) throws IOException {

        ReslutIndexData UpFunctionFeatures=new ReslutIndexData();

        String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/up-func-features/additional-2&nfId=", JSON.toJSONString(tokenNfiddto));

        JSONObject jsonObject = JSON.parseObject(res);
        //成功情况，error设置为空
        if(jsonObject.getString("result").equals("OK")){
            UpFunctionFeatures.setResult(jsonObject.getString("result"));
            UpFunctionFeatures.setIndex(jsonObject.getJSONObject("index"));
            UpFunctionFeatures.setData(jsonObject.getJSONArray("data"));

        }else{//失败情况，填充error，成功的关键字设置为空
            UpFunctionFeatures.setResult("FAIL");
            UpFunctionFeatures.setError(jsonObject.getString("error"));
        }
        //给前端返回数据
        return UpFunctionFeatures;
    }
    /*
     ****DefaultUpFunctionFeatures修改
     */
    @PostMapping("/putDefaultUPFunctionFeatures")
    public ResultData PutDefaultUPFunctionFeatures(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   @RequestBody DefaultUpFunctionFeaturesDto defaultUpFunctionFeaturesDto) throws IOException {

        ResultData resultData = new ResultData();
        if(Two(defaultUpFunctionFeaturesDto.getFtup()) && Two(defaultUpFunctionFeaturesDto.getDdnd()) &&Two(defaultUpFunctionFeaturesDto.getPfdm()) &&Two(defaultUpFunctionFeaturesDto.getQuoac()) &&Two(defaultUpFunctionFeaturesDto.getTrace()) &&Two(defaultUpFunctionFeaturesDto.getDlbd())&&Two(defaultUpFunctionFeaturesDto.getHeeu())  &&
                Two(defaultUpFunctionFeaturesDto.getEpfar()) && Two(defaultUpFunctionFeaturesDto.getPdiu()) &&Two(defaultUpFunctionFeaturesDto.getFrrt()) &&Two(defaultUpFunctionFeaturesDto.getPfde()) &&Two(defaultUpFunctionFeaturesDto.getEmpu()) &&
                Two(defaultUpFunctionFeaturesDto.getTrst())&&Two(defaultUpFunctionFeaturesDto.getTreu())&& Two(defaultUpFunctionFeaturesDto.getBucp()) &&Two(defaultUpFunctionFeaturesDto.getUdbc())){
            String res = PutDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/up-func-features/default&nfId=", JSON.toJSONString(defaultUpFunctionFeaturesDto));
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
            //给前端返回数据
            return resultData;
        }
        else {
            resultData.setResult("FAIL");
            return resultData;
        }

    }

    /*
     ****Additional-1UpFunctionFeatures修改
     */
    @PostMapping("/putAddition1UPFunctionFeatures")
    public ResultData PutAddition1UPFunctionFeatures(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   @RequestBody Additional1UpFunctionFeaturesDto additional1UpFunctionFeaturesDto) throws IOException {

        ResultData resultData = new ResultData();
        if (Two(additional1UpFunctionFeaturesDto.getUeip()) && Two(additional1UpFunctionFeaturesDto.getDpdra()) && Two(additional1UpFunctionFeaturesDto.getNorp()) && Two(additional1UpFunctionFeaturesDto.getVtime()) && Two(additional1UpFunctionFeaturesDto.getMptcp()) && Two(additional1UpFunctionFeaturesDto.getSset()) && Two(additional1UpFunctionFeaturesDto.getMnop()) && Two(additional1UpFunctionFeaturesDto.getBundl()) &&
                Two(additional1UpFunctionFeaturesDto.getIptv()) && Two(additional1UpFunctionFeaturesDto.getGcom()) &&
                Two(additional1UpFunctionFeaturesDto.getAdpdp()) && Two(additional1UpFunctionFeaturesDto.getIp6pl()) && Two(additional1UpFunctionFeaturesDto.getTscu()) && Two(additional1UpFunctionFeaturesDto.getMpas()) && Two(additional1UpFunctionFeaturesDto.getRttl()) && Two(additional1UpFunctionFeaturesDto.getMte())
        ) {
            String res = PutDataWithJson(StartURL + "coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/up-func-features/additional-1&nfId=", JSON.toJSONString(additional1UpFunctionFeaturesDto));
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
            //给前端返回数据
            return resultData;
        }
        else{
            resultData.setResult("FAIL");
            return resultData;
        }
    }

    @PostMapping("/putAddition2UPFunctionFeatures")
    public ResultData PutAddition2UPFunctionFeatures(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     @RequestBody Additional2UpFunctionFeaturesDto additional2UpFunctionFeaturesDto) throws IOException {
        ResultData resultData=new ResultData();
        if(Two(additional2UpFunctionFeaturesDto.getMtedt())&&Two(additional2UpFunctionFeaturesDto.getQfqm())&&Two(additional2UpFunctionFeaturesDto.getRds())&&
                Two(additional2UpFunctionFeaturesDto.getEthar())&&Two(additional2UpFunctionFeaturesDto.getAtsssll())&&Two(additional2UpFunctionFeaturesDto.getGpqm())&&Two(additional2UpFunctionFeaturesDto.getRttwp())&&Two(additional2UpFunctionFeaturesDto.getDdds())&&Two(additional2UpFunctionFeaturesDto.getCiot())){
            String res = PutDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/up-func-features/additional-2&nfId=", JSON.toJSONString(additional2UpFunctionFeaturesDto));
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
            //给前端返回数据
            return resultData;
        }
        else{
            resultData.setResult("FAIL");
            return resultData;
        }
    }
}
