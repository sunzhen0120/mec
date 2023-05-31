package com.upf.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upf.dto.DataPlaneFunctionDto;
import com.upf.dto.ReslutIndexData;
import com.upf.dto.ResultData;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.upf.Parameters.StartURL;

import static com.upf.SendWithJson.Get.GetWithJson.GetDataWithJson;
import static com.upf.SendWithJson.Put.PutWithJson.PutDataWithJson;
import static com.upf.utils.Judge.Two;


@RestController
@RequestMapping("DataPlaneFunction")
public class DataPlaneFunctionController {

    //查询 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func&nfId="
    @PostMapping("/getDataPlaneFunction")
    public ReslutIndexData GetDataPlaneFunction(HttpServletRequest request,
                                                HttpServletResponse response,
                                                @RequestBody DataPlaneFunctionDto dataPlaneFunctionDto) throws IOException {
        //返回的值
        ReslutIndexData getDataPlaneFunctionReturnDto = new ReslutIndexData();

        String res = GetDataWithJson(StartURL + "coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func&nfId=", JSON.toJSONString(dataPlaneFunctionDto));

        JSONObject jsonObject = JSON.parseObject(res);

        if (jsonObject.getString("result").equals("OK")) {
            getDataPlaneFunctionReturnDto.setResult(jsonObject.getString("result"));
            getDataPlaneFunctionReturnDto.setIndex(jsonObject.getJSONObject("index"));
            getDataPlaneFunctionReturnDto.setData(jsonObject.getJSONArray("data"));
            //error设置为空
        } else {
            /**
             * 如果错误，填充error
             */
            getDataPlaneFunctionReturnDto.setResult("FAIL");
            getDataPlaneFunctionReturnDto.setIndex(jsonObject.getJSONObject("index"));
            getDataPlaneFunctionReturnDto.setError(jsonObject.getString("error"));
        }
        return getDataPlaneFunctionReturnDto;
    }

    //修改 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func&nfId="
    @PostMapping("/putDataPlaneFunction")
    public ResultData PutDataPlaneFunction(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestBody DataPlaneFunctionDto dataPlaneFunctionDto) throws IOException {
        //返回的值
        ResultData putDataPlaneFunctionReturnDto = new ResultData();

        if(Two(dataPlaneFunctionDto.getWhite_url_list())&&
                Two(dataPlaneFunctionDto.getPath_mgmt())&&
                Two(dataPlaneFunctionDto.getHeader_enrich())&&
                Two(dataPlaneFunctionDto.getDpi())){
            String res = PutDataWithJson(StartURL + "coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func&nfId=", JSON.toJSONString(dataPlaneFunctionDto));

            JSONObject jsonObject = JSON.parseObject(res);

            if (jsonObject.getString("result").equals("OK")) {
                putDataPlaneFunctionReturnDto.setResult(jsonObject.getString("result"));
                putDataPlaneFunctionReturnDto.setData(jsonObject.getJSONObject("data"));
                //error设置为空
            } else {
                /**
                 * 如果错误，填充error
                 */
                putDataPlaneFunctionReturnDto.setResult("FAIL");
                putDataPlaneFunctionReturnDto.setData(jsonObject.getJSONObject("data"));
                putDataPlaneFunctionReturnDto.setError(jsonObject.getString("error"));
            }
            return putDataPlaneFunctionReturnDto;
        }else{
        putDataPlaneFunctionReturnDto.setResult("FAIL");
        return putDataPlaneFunctionReturnDto;
        }
    }
}


