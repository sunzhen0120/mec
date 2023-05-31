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

@RestController
@RequestMapping("UEIPAddressPool")
public class UEIPAddressPoolController {
        //添加
        @PostMapping("/postUEIPAddressPool")
        public ResultData PostUEIPAddressPool(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   @RequestBody PostUEIPAddressPoolDto postUEIPAddressPoolDto) throws IOException {

            //返回的值
            ResultData postUEIPAddressPoolReturnDto=new ResultData();


            Pattern network_instancePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
            Pattern pool_namePattern= Pattern.compile("^(?=.{1,100}$)(?!-)[a-zA-Z\\d-]{1,63}(?<!-)(\\.(?!-)[a-zA-Z\\d-]{1,63}(?<!-))*$");


            if(network_instancePattern.matcher(postUEIPAddressPoolDto.getNetwork_instance()).matches()&&
                    pool_namePattern.matcher(postUEIPAddressPoolDto.getPool_name()).matches()){

                String res = PostDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=addr-mgmt/addr-pools&nfId=", JSON.toJSONString(postUEIPAddressPoolDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    postUEIPAddressPoolReturnDto.setResult(jsonObject.getString("result"));
                    postUEIPAddressPoolReturnDto.setData(jsonObject.getJSONObject("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    postUEIPAddressPoolReturnDto.setResult("FAIL");
                    postUEIPAddressPoolReturnDto.setData(jsonObject.getJSONObject("data"));
                    postUEIPAddressPoolReturnDto.setError(jsonObject.getString("error"));
                }
                return  postUEIPAddressPoolReturnDto;
            }
            else{
                postUEIPAddressPoolReturnDto.setResult("FAIL");
                return postUEIPAddressPoolReturnDto;
            }

        }

        //查询 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=addr-mgmt/addr-pools&nfId="
        @PostMapping("/getUEIPAddressPool")
        public ReslutIndexData GetUEIPAddressPool(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   @RequestBody UEIPAddressPoolDto uEIPAddressPoolDto) throws IOException {

            //返回的值
            ReslutIndexData getUEIPAddressPoolReturnDto=new ReslutIndexData();

            Pattern pool_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
            if(pool_namePattern.matcher(uEIPAddressPoolDto.getPool_name()).matches() || uEIPAddressPoolDto.getPool_name().equals("null")){
                String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=addr-mgmt/addr-pools&nfId=", JSON.toJSONString(uEIPAddressPoolDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    getUEIPAddressPoolReturnDto.setResult(jsonObject.getString("result"));
                    getUEIPAddressPoolReturnDto.setIndex(jsonObject.getJSONObject("index"));
                    getUEIPAddressPoolReturnDto.setData(jsonObject.getJSONArray("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    getUEIPAddressPoolReturnDto.setResult("FAIL");
                    getUEIPAddressPoolReturnDto.setError(jsonObject.getString("error"));
                }
                return  getUEIPAddressPoolReturnDto;
            }
            else{
                getUEIPAddressPoolReturnDto.setResult("FAIL");
                return  getUEIPAddressPoolReturnDto;
            }


        }

        //删除 StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=addr-mgmt/addr-pools&nfId="
        @PostMapping("/deleteUEIPAddressPool")
        public ResultData DeleteUEIPAddressPool(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      @RequestBody UEIPAddressPoolDto uEIPAddressPoolDto) throws IOException {

            //返回的值
            ResultData deleteUEIPAddressPoolReturnDto=new ResultData();

            Pattern pool_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
            if(pool_namePattern.matcher(uEIPAddressPoolDto.getPool_name()).matches()){
                String res = DeleteDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=addr-mgmt/addr-pools&nfId=", JSON.toJSONString(uEIPAddressPoolDto));

                JSONObject jsonObject = JSON.parseObject(res);

                if(jsonObject.getString("result").equals("OK")){
                    deleteUEIPAddressPoolReturnDto.setResult(jsonObject.getString("result"));
                    deleteUEIPAddressPoolReturnDto.setData(jsonObject.getJSONObject("data"));
                    //error设置为空
                }else{
                    /**
                     * 如果错误，填充error
                     */
                    deleteUEIPAddressPoolReturnDto.setResult("FAIL");
                    deleteUEIPAddressPoolReturnDto.setData(jsonObject.getJSONObject("data"));
                    deleteUEIPAddressPoolReturnDto.setError(jsonObject.getString("error"));
                }
                return  deleteUEIPAddressPoolReturnDto;
            }

            else{
                deleteUEIPAddressPoolReturnDto.setResult("FAIL");
                return  deleteUEIPAddressPoolReturnDto;
            }

        }
    }


