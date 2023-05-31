package com.upf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upf.dto.ReslutIndexData;
import com.upf.dto.PutPfcpPropertiesDto;
import com.upf.dto.ResultData;
import com.upf.dto.TokenNfidDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static com.upf.Parameters.StartURL;
import static com.upf.SendWithJson.Get.GetWithJson.GetDataWithJson;
import static com.upf.SendWithJson.Put.PutWithJson.PutDataWithJson;
import static com.upf.utils.Judge.Uint16;
import static com.upf.utils.Judge.Uint8;


@RestController
@RequestMapping("PfcpProperties")
public class PfcpPropertiesController {

    @PostMapping("/getPfcpProperties")
    public ReslutIndexData GetPfcpProperties(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestBody TokenNfidDto tokenNfiddto) {

        //返回的数据结构存放位置
        ReslutIndexData getPfcpPropertiesReturnDto=new ReslutIndexData();

        //获取系统返回值
        String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/properties&nfId=", JSON.toJSONString(tokenNfiddto));

        //解析返回字符串，对getPfcpPropertiesReturnDto进行赋值
        JSONObject jsonObject = JSON.parseObject(res);

        //成功情况，error设置为空
        if(jsonObject.getString("result").equals("OK")){
            getPfcpPropertiesReturnDto.setResult(jsonObject.getString("result"));
            getPfcpPropertiesReturnDto.setIndex(jsonObject.getJSONObject("index"));
            getPfcpPropertiesReturnDto.setData(jsonObject.getJSONArray("data"));

        }else{//失败情况，填充error，成功的关键字设置为空
            getPfcpPropertiesReturnDto.setResult("FAIL");
            getPfcpPropertiesReturnDto.setError(jsonObject.getString("error"));
        }
        //给前端返回数据
        return  getPfcpPropertiesReturnDto;
    }

    @PostMapping ("/putPfcpProperties")
    public ResultData PutPfcpProperties(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestBody PutPfcpPropertiesDto putPfcpPropertiesDto) {

        //返回的数据结构存放位置
        ResultData resultData=new ResultData();




        if(Uint16(putPfcpPropertiesDto.getMax_incoming_request())&& 0<=putPfcpPropertiesDto.getMax_incoming_request() &&putPfcpPropertiesDto.getMax_incoming_request()<=2500 &&
                Uint16(putPfcpPropertiesDto.getMax_outgoing_request())&& 1<=putPfcpPropertiesDto.getMax_outgoing_request() &&putPfcpPropertiesDto.getMax_outgoing_request()<=1500&&
                Uint16(putPfcpPropertiesDto.getHeartbeat_interval())&& 1<=putPfcpPropertiesDto.getHeartbeat_interval() &&putPfcpPropertiesDto.getHeartbeat_interval()<=180&&
                Uint8(putPfcpPropertiesDto.getInactivity_timer())&& 1<=putPfcpPropertiesDto.getInactivity_timer()  &&putPfcpPropertiesDto.getInactivity_timer()<=10&&
                Uint8(putPfcpPropertiesDto.getN1_request())&& 3<=putPfcpPropertiesDto.getN1_request()  &&putPfcpPropertiesDto.getN1_request()<=5&&
                Uint8(putPfcpPropertiesDto.getT1_response())&& 1<=putPfcpPropertiesDto.getT1_response() &&putPfcpPropertiesDto.getT1_response()<=6
        ){
            String res = PutDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/properties&nfId=", JSON.toJSONString(putPfcpPropertiesDto));

            //解析返回字符串，对getPfcpPropertiesReturnDto进行赋值
            JSONObject jsonObject = JSON.parseObject(res);

            //成功情况，error设置为空
            if(jsonObject.getString("result").equals("OK")){
                resultData.setResult(jsonObject.getString("result"));
                resultData.setData(jsonObject.getJSONObject("data"));

            }else{//失败情况，填充error，成功的关键字设置为空
                resultData.setResult("FAIL");
                resultData.setError(jsonObject.getString("error"));
            }
            //给前端返回数据
            return resultData;


        //后端过滤数据失败
        }else {
            resultData.setResult("FAIL");
            return resultData;
        }
    }

}
