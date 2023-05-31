package com.upf.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upf.dto.PFCPAssocReleaseDto;
import com.upf.dto.ReslutIndexData;
import com.upf.dto.ResultData;
import com.upf.dto.TokenNfidDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


import static com.upf.Parameters.StartURL;
import static com.upf.SendWithJson.Delete.DeleteWithJson.DeleteDataWithJson;
import static com.upf.SendWithJson.Get.GetWithJson.GetDataWithJson;
import static com.upf.SendWithJson.Post.PostWithJson.PostDataWithJson;
import static com.upf.SendWithJson.Put.PutWithJson.PutDataWithJson;
import static com.upf.utils.Judge.*;


@RestController
@RequestMapping("PfcpAssocRelease")
public class PfcpAssocReleaseController {
    //添加
    @PostMapping("/postPfcpAssocRelease")
    public ResultData PostPfcpAssocRelease(HttpServletRequest request,
                                                HttpServletResponse response,
                                                @RequestBody PFCPAssocReleaseDto pFCPAssocReleaseDto) throws IOException {

        ResultData postPfcpAssocReleaseReturnDto= new ResultData();

        if(Two(pFCPAssocReleaseDto.getSarr() ) && Two(pFCPAssocReleaseDto.getUrss()) && JudgeTimerValue(pFCPAssocReleaseDto.getTimer_value()) &&JudgeTimerUnit(pFCPAssocReleaseDto.getTimer_unit())){

            String res = PostDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/assoc-release&nfId=", JSON.toJSONString(pFCPAssocReleaseDto));

            JSONObject jsonObject = JSON.parseObject(res);

            if(jsonObject.getString("result").equals("OK")){
                postPfcpAssocReleaseReturnDto.setResult(jsonObject.getString("result"));
                postPfcpAssocReleaseReturnDto.setData(jsonObject.getJSONObject("data"));
                //error设置为空
            }else{
                /**
                 * 如果错误，填充error
                 */
                postPfcpAssocReleaseReturnDto.setResult("FAIL");
                postPfcpAssocReleaseReturnDto.setData(jsonObject.getJSONObject("data"));
                postPfcpAssocReleaseReturnDto.setError(jsonObject.getString("error"));
            }
            return  postPfcpAssocReleaseReturnDto;

        }else {
            postPfcpAssocReleaseReturnDto.setResult("FAIL");
            return  postPfcpAssocReleaseReturnDto;
        }
    }

    //查询 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/assoc-release&nfId="
    @PostMapping("/getPfcpAssocRelease")
    public ReslutIndexData GetPfcpAssocRelease(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @RequestBody PFCPAssocReleaseDto pFCPAssocReleaseDto) throws IOException {

        //返回的值
        ReslutIndexData getPfcpAssocReleaseReturnDto=new ReslutIndexData();

        String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/assoc-release&nfId=", JSON.toJSONString(pFCPAssocReleaseDto));

        JSONObject jsonObject = JSON.parseObject(res);

        if(jsonObject.getString("result").equals("OK")){
            getPfcpAssocReleaseReturnDto.setResult(jsonObject.getString("result"));
            getPfcpAssocReleaseReturnDto.setIndex(jsonObject.getJSONObject("index"));
            getPfcpAssocReleaseReturnDto.setData(jsonObject.getJSONArray("data"));
            //error设置为空
        }else{
            /**
             * 如果错误，填充error
             */
            getPfcpAssocReleaseReturnDto.setResult("FAIL");
            getPfcpAssocReleaseReturnDto.setIndex(jsonObject.getJSONObject("index"));
            getPfcpAssocReleaseReturnDto.setError(jsonObject.getString("error"));
        }
        return  getPfcpAssocReleaseReturnDto;
    }

    //修改StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/assoc-release&nfId="
    @PostMapping("/putPfcpAssocRelease")
    public ResultData PutPfcpAssocRelease(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @RequestBody PFCPAssocReleaseDto pFCPAssocReleaseDto) throws IOException {

        //返回的值
        ResultData putPfcpAssocReleaseReturnDto=new ResultData();

        if(Two(pFCPAssocReleaseDto.getSarr() ) && Two(pFCPAssocReleaseDto.getUrss()) && JudgeTimerValue(pFCPAssocReleaseDto.getTimer_value()) &&JudgeTimerUnit(pFCPAssocReleaseDto.getTimer_unit())){
            String res = PutDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/assoc-release&nfId=", JSON.toJSONString(pFCPAssocReleaseDto));

            JSONObject jsonObject = JSON.parseObject(res);

            if(jsonObject.getString("result").equals("OK")){
                putPfcpAssocReleaseReturnDto.setResult(jsonObject.getString("result"));
                putPfcpAssocReleaseReturnDto.setData(jsonObject.getJSONObject("data"));
                //error设置为空
            }else{
                /**
                 * 如果错误，填充error
                 */
                putPfcpAssocReleaseReturnDto.setResult("FAIL");
                putPfcpAssocReleaseReturnDto.setData(jsonObject.getJSONObject("data"));
                putPfcpAssocReleaseReturnDto.setError(jsonObject.getString("error"));
            }
            return  putPfcpAssocReleaseReturnDto;
        }else {
            putPfcpAssocReleaseReturnDto.setResult("FAIL");
            return  putPfcpAssocReleaseReturnDto;

        }

    }




    //删除 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/assoc-release&nfId="
    @PostMapping("/deletePfcpAssocRelease")
    public ResultData DeletePfcpAssocRelease(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @RequestBody PFCPAssocReleaseDto pFCPAssocReleaseDto) {
        //返回的值
        ResultData deletePfcpAssocReleaseReturnDto=new ResultData();

        String res = DeleteDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/assoc-release&nfId=", JSON.toJSONString(pFCPAssocReleaseDto));

        JSONObject jsonObject = JSON.parseObject(res);

        if(jsonObject.getString("result").equals("OK")){
            deletePfcpAssocReleaseReturnDto.setResult(jsonObject.getString("result"));
            deletePfcpAssocReleaseReturnDto.setData(jsonObject.getJSONObject("data"));
            //error设置为空
        }else{
            /**
             * 如果错误，填充error
             */
            deletePfcpAssocReleaseReturnDto.setResult("FAIL");
            deletePfcpAssocReleaseReturnDto.setData(jsonObject.getJSONObject("data"));
            deletePfcpAssocReleaseReturnDto.setError(jsonObject.getString("error"));
        }
        return  deletePfcpAssocReleaseReturnDto;
    }
}
