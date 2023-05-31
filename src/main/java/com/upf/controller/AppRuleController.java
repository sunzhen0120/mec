package com.upf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upf.dto.AppRuleDto;
import com.upf.dto.PostAppRuleDto;
import com.upf.dto.ReslutIndexData;
import com.upf.dto.ResultData;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

import static com.upf.Parameters.StartURL;
import static com.upf.SendWithJson.Delete.DeleteWithJson.DeleteDataWithJson;
import static com.upf.SendWithJson.Get.GetWithJson.GetDataWithJson;
import static com.upf.SendWithJson.Post.PostWithJson.PostDataWithJson;


@RestController
@RequestMapping("AppRule")
public class AppRuleController {

        //添加
        @PostMapping("/postAppRule")
        public ResultData PostAppRule(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestBody PostAppRuleDto postAppRuleDto) throws IOException {

            //返回的值
            ResultData postAppRuleReturnDto=new ResultData();

            Pattern rule_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
            Pattern l34_filter_namePattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
            Pattern ref_app_idPattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");

            if(     rule_namePattern.matcher(postAppRuleDto.getRule_name()).matches() &&
                    l34_filter_namePattern.matcher(postAppRuleDto.getL34_filter_name()).matches()&&
                    ref_app_idPattern.matcher(postAppRuleDto.getRef_app_id()).matches()
            ){
                String res = PostDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/rules&nfId=", JSON.toJSONString(postAppRuleDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    postAppRuleReturnDto.setResult(jsonObject.getString("result"));
                    postAppRuleReturnDto.setData(jsonObject.getJSONObject("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    postAppRuleReturnDto.setResult("FAIL");
                    postAppRuleReturnDto.setData(jsonObject.getJSONObject("data"));
                    postAppRuleReturnDto.setError(jsonObject.getString("error"));
                }
                return  postAppRuleReturnDto;

            }else{
                postAppRuleReturnDto.setResult("FAIL");
                return  postAppRuleReturnDto;
            }
        }

        //查询 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/rules&nfId="
        @PostMapping("/getAppRule")
        public ReslutIndexData GetAppRule(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   @RequestBody AppRuleDto appRuleDto) throws IOException {

            //返回的值
            ReslutIndexData getAppRuleReturnDto=new ReslutIndexData();

            Pattern rule_namePattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
            if(rule_namePattern.matcher(appRuleDto.getRule_name()).matches() || appRuleDto.getRule_name().equals("null")){
                String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/rules&nfId=", JSON.toJSONString(appRuleDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    getAppRuleReturnDto.setResult(jsonObject.getString("result"));
                    getAppRuleReturnDto.setIndex(jsonObject.getJSONObject("index"));
                    getAppRuleReturnDto.setData(jsonObject.getJSONArray("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    getAppRuleReturnDto.setResult("FAIL");
                    getAppRuleReturnDto.setIndex(jsonObject.getJSONObject("index"));
                    getAppRuleReturnDto.setError(jsonObject.getString("error"));
                }
                return  getAppRuleReturnDto;
            }else{
                getAppRuleReturnDto.setResult("FAIL");
                return  getAppRuleReturnDto;
            }
        }

        //删除 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/rules&nfId="
        @PostMapping("/deleteAppRule")
        public ResultData DeleteAppRule(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      @RequestBody AppRuleDto appRuleDto) throws IOException {

            //返回的值
            ResultData deleteAppRuleReturnDto=new ResultData();

            Pattern rule_namePattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
            if (rule_namePattern.matcher(appRuleDto.getRule_name()).matches() || appRuleDto.getRule_name().equals("null")) {
                String res = DeleteDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/rules&nfId=", JSON.toJSONString(appRuleDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    deleteAppRuleReturnDto.setResult(jsonObject.getString("result"));
                    deleteAppRuleReturnDto.setData(jsonObject.getJSONObject("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    deleteAppRuleReturnDto.setResult("FAIL");
                    deleteAppRuleReturnDto.setData(jsonObject.getJSONObject("data"));
                    deleteAppRuleReturnDto.setError(jsonObject.getString("error"));
                }
                return  deleteAppRuleReturnDto;
            }
            else {
                    deleteAppRuleReturnDto.setResult("FAIL");
                    return  deleteAppRuleReturnDto;
            }
        }

    }


