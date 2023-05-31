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
import static com.upf.SendWithJson.Put.PutWithJson.PutDataWithJson;
import static com.upf.SendWithJson.Delete.DeleteWithJson.DeleteDataWithJson;
import static com.upf.SendWithJson.Get.GetWithJson.GetDataWithJson;
import static com.upf.SendWithJson.Post.PostWithJson.PostDataWithJson;

@RestController
@RequestMapping("HeaderEnrichment")
public class HeaderEnrichmentController {


        //添加
        @PostMapping("/postHeaderEnrichment")
        public ResultData PostHeaderEnrichment(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @RequestBody PostHeaderEnrichmentDto postHeaderEnrichmentDto) throws IOException {

            //返回的值
            ResultData postHeaderEnrichmentReturnDto=new ResultData();

            Pattern field_namePattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
            Pattern field_valuePattern = Pattern.compile("^[\\da-zA-Z]{1,64}$");

            if((postHeaderEnrichmentDto.getField_name().equals("IMSI")||postHeaderEnrichmentDto.getField_name().equals("SUPI")||
                    postHeaderEnrichmentDto.getField_name().equals("IMEI")||postHeaderEnrichmentDto.getField_name().equals("PEI")||
                    postHeaderEnrichmentDto.getField_name().equals("MSISDN")||postHeaderEnrichmentDto.getField_name().equals("GPSI")||
                    postHeaderEnrichmentDto.getField_name().equals("UEIP")||postHeaderEnrichmentDto.getField_name().equals("TIMESTAMP")||
                    postHeaderEnrichmentDto.getField_name().equals("UPFIP")||postHeaderEnrichmentDto.getField_name().equals("APNDNN")) ){

                String res = PostDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/header-enrich&nfId=", JSON.toJSONString(postHeaderEnrichmentDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    postHeaderEnrichmentReturnDto.setResult(jsonObject.getString("result"));
                    postHeaderEnrichmentReturnDto.setData(jsonObject.getJSONObject("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    postHeaderEnrichmentReturnDto.setResult("FAIL");
                    postHeaderEnrichmentReturnDto.setData(jsonObject.getJSONObject("data"));
                    postHeaderEnrichmentReturnDto.setError(jsonObject.getString("error"));
                }
                return  postHeaderEnrichmentReturnDto;

            } else if(field_namePattern.matcher(postHeaderEnrichmentDto.getField_name()).matches()&&
                    field_valuePattern.matcher(postHeaderEnrichmentDto.getField_value()).matches()){
                String res = PostDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/header-enrich&nfId=", JSON.toJSONString(postHeaderEnrichmentDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    postHeaderEnrichmentReturnDto.setResult(jsonObject.getString("result"));
                    postHeaderEnrichmentReturnDto.setData(jsonObject.getJSONObject("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    postHeaderEnrichmentReturnDto.setResult("FAIL");
                    postHeaderEnrichmentReturnDto.setData(jsonObject.getJSONObject("data"));
                    postHeaderEnrichmentReturnDto.setError(jsonObject.getString("error"));
                }
                return  postHeaderEnrichmentReturnDto;

            }else{
                postHeaderEnrichmentReturnDto.setResult("FAIL");
                return  postHeaderEnrichmentReturnDto;
            }
        }

        //查询 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/header-enrich&nfId="
        @PostMapping("/getHeaderEnrichment")
        public ReslutIndexData GetHeaderEnrichment(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   @RequestBody GetHeaderEnrichmentDto getHeaderEnrichmentDto) throws IOException {

            //返回的值
            ReslutIndexData getHeaderEnrichmentReturnDto=new ReslutIndexData();

            Pattern field_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
            if(field_namePattern.matcher(getHeaderEnrichmentDto.getField_name()).matches() || getHeaderEnrichmentDto.getField_name().equals("null")){
                String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/header-enrich&nfId=", JSON.toJSONString(getHeaderEnrichmentDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    getHeaderEnrichmentReturnDto.setResult(jsonObject.getString("result"));
                    getHeaderEnrichmentReturnDto.setIndex(jsonObject.getJSONObject("index"));
                    getHeaderEnrichmentReturnDto.setData(jsonObject.getJSONArray("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    getHeaderEnrichmentReturnDto.setResult("FAIL");
                    getHeaderEnrichmentReturnDto.setError(jsonObject.getString("error"));
                }
                return  getHeaderEnrichmentReturnDto;
            }else{
                getHeaderEnrichmentReturnDto.setResult("FAIL");
                return  getHeaderEnrichmentReturnDto;
            }
        }

        //修改 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/header-enrich&nfId="
        @PostMapping("/putHeaderEnrichment")
        public ResultData PutHeaderEnrichment(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   @RequestBody PostHeaderEnrichmentDto putHeaderEnrichmentDto) throws IOException {

            //返回的值
            ResultData putHeaderEnrichmentReturnDto=new ResultData();

            Pattern field_namePattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
            Pattern field_valuePattern = Pattern.compile("^[\\da-zA-Z]{1,64}$");

            if((putHeaderEnrichmentDto.getField_name().equals("IMSI")||putHeaderEnrichmentDto.getField_name().equals("SUPI")||
                    putHeaderEnrichmentDto.getField_name().equals("IMEI")||putHeaderEnrichmentDto.getField_name().equals("PEI")||
                    putHeaderEnrichmentDto.getField_name().equals("MSISDN")||putHeaderEnrichmentDto.getField_name().equals("GPSI")||
                    putHeaderEnrichmentDto.getField_name().equals("UEIP")||putHeaderEnrichmentDto.getField_name().equals("TIMESTAMP")||
                    putHeaderEnrichmentDto.getField_name().equals("UPFIP")||putHeaderEnrichmentDto.getField_name().equals("APNDNN")) ){
                String res = PutDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/header-enrich&nfId=", JSON.toJSONString(putHeaderEnrichmentDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    putHeaderEnrichmentReturnDto.setResult(jsonObject.getString("result"));
                    putHeaderEnrichmentReturnDto.setData(jsonObject.getJSONObject("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    putHeaderEnrichmentReturnDto.setResult("FAIL");
                    putHeaderEnrichmentReturnDto.setData(jsonObject.getJSONObject("data"));
                    putHeaderEnrichmentReturnDto.setError(jsonObject.getString("error"));
                }
                return  putHeaderEnrichmentReturnDto;


            } else if(field_namePattern.matcher(putHeaderEnrichmentDto.getField_name()).matches()&&
                    field_valuePattern.matcher(putHeaderEnrichmentDto.getField_value()).matches()){
                String res = PutDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/header-enrich&nfId=", JSON.toJSONString(putHeaderEnrichmentDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    putHeaderEnrichmentReturnDto.setResult(jsonObject.getString("result"));
                    putHeaderEnrichmentReturnDto.setData(jsonObject.getJSONObject("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    putHeaderEnrichmentReturnDto.setResult("FAIL");
                    putHeaderEnrichmentReturnDto.setData(jsonObject.getJSONObject("data"));
                    putHeaderEnrichmentReturnDto.setError(jsonObject.getString("error"));
                }
                return  putHeaderEnrichmentReturnDto;

            }else{
                putHeaderEnrichmentReturnDto.setResult("FAIL");
                return  putHeaderEnrichmentReturnDto;
            }
        }




        //删除 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/header-enrich&nfId="
        @PostMapping("/deleteHeaderEnrichment")
        public ResultData DeleteHeaderEnrichment(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      @RequestBody GetHeaderEnrichmentDto deleteHeaderEnrichmentDto) throws IOException {

            //返回的值
            ResultData deleteHeaderEnrichmentReturnDto=new ResultData();

            Pattern field_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
            if(field_namePattern.matcher(deleteHeaderEnrichmentDto.getField_name()).matches() ){
                String res = DeleteDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=data-plane/func/header-enrich&nfId=", JSON.toJSONString(deleteHeaderEnrichmentDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    deleteHeaderEnrichmentReturnDto.setResult(jsonObject.getString("result"));
                    deleteHeaderEnrichmentReturnDto.setData(jsonObject.getJSONObject("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    deleteHeaderEnrichmentReturnDto.setResult("FAIL");
                    deleteHeaderEnrichmentReturnDto.setData(jsonObject.getJSONObject("data"));
                    deleteHeaderEnrichmentReturnDto.setError(jsonObject.getString("error"));
                }
                return  deleteHeaderEnrichmentReturnDto;
            }else{
                deleteHeaderEnrichmentReturnDto.setResult("FAIL");
                return  deleteHeaderEnrichmentReturnDto;
            }
        }

}


