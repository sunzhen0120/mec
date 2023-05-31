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
@RequestMapping("UEIPAddressRangeController")
public class UEIPAddressRangeController {
    /*
    *****添加UEIPAddressRangeController
     */
    @PostMapping("/postUEIPAddressController")
    public ResultData PostUEIPAddress(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestBody PostUEIPAddressRangeDto postUEIPAddressRangeDto) throws IOException {

        ResultData resultData = new ResultData();
        Pattern pool_namePattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
        Pattern range_namePattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
        Pattern net_maskPattern = Pattern.compile("(^((128|192)|2(24|4[08]|5[245]))(\\.(0|(128|192)|2((24)|(4[08])|(5[245])))){3}$)|(^([1-9](?:|[\\d])|1(?:[0-1][\\d]|2[0-8]))$)");
        Pattern ip_numPattern = Pattern.compile("^([1-9](?:|[\\d]|[\\d][\\d]|[\\d][\\d][\\d]|[\\d][\\d][\\d][\\d]|[\\d][\\d][\\d][\\d][\\d]|[\\d][\\d][\\d][\\d][\\d][\\d])|1[0-5][\\d][\\d][\\d][\\d][\\d][\\d]|16[0-6][\\d][\\d][\\d][\\d][\\d]|167[0-6][\\d][\\d][\\d][\\d]|1677[0-6][\\d][\\d][\\d]|16777[0-1][\\d][\\d]|1677720[\\d]|1677721[0-5])$");
        Pattern start_ipIPv4 = Pattern.compile("(^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$)");
        Pattern start_ipIPv6 = Pattern.compile("^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$");
        boolean start_ipIPVx =((start_ipIPv4.matcher(postUEIPAddressRangeDto.getStart_ip()).matches())||start_ipIPv6.matcher(postUEIPAddressRangeDto.getStart_ip()).matches());
        if(pool_namePattern.matcher(postUEIPAddressRangeDto.getPool_name()).matches() &&
                range_namePattern.matcher(postUEIPAddressRangeDto.getRange_name()).matches()&&
                net_maskPattern.matcher(postUEIPAddressRangeDto.getNet_mask()).matches()&&  //不知道该不该为空
                ip_numPattern.matcher(postUEIPAddressRangeDto.getIp_num()).matches()&&
                start_ipIPVx
        ){
            String res = PostDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=addr-mgmt/addr-ranges&nfId=", JSON.toJSONString(postUEIPAddressRangeDto));
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
            return  resultData;
        }
        else{
            resultData.setResult("FAIL");
            return resultData;
        }
    }
    /*
     *****查询UEIPAddressRangeController
     */
    @PostMapping("/getUEIPAddressController")
    public ReslutIndexData GetUEIPAddress(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestBody UEIPAddressRangeDto ueipAddressRangeDto) throws IOException {

        ReslutIndexData reslutIndexData = new ReslutIndexData();
        Pattern range_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        if(range_namePattern.matcher(ueipAddressRangeDto.getRange_name()).matches() || ueipAddressRangeDto.getRange_name().equals("null")){
            String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=addr-mgmt/addr-ranges&nfId=", JSON.toJSONString(ueipAddressRangeDto));
            JSONObject jsonObject = JSON.parseObject(res);

            //成功情况，error设置为空
            if(jsonObject.getString("result").equals("OK")){
                reslutIndexData.setResult(jsonObject.getString("result"));
                reslutIndexData.setIndex(jsonObject.getJSONObject("index"));
                reslutIndexData.setData(jsonObject.getJSONArray("data"));

            }
            else{//失败情况，填充error，成功的关键字设置为空
                reslutIndexData.setResult("FAIL");
                reslutIndexData.setError(jsonObject.getString("error"));
            }
            //给前端返回数据
            return reslutIndexData;
        }
        else {
            reslutIndexData.setResult("FAIL");
            return reslutIndexData;
        }
    }
    /*
     ****删除UEIPAddressRangeController
     */
    @PostMapping("/deleteUEIPAddressController")
    public ResultData DeleteUEIPAddress(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestBody UEIPAddressRangeDto ueipAddressRangeDto) throws IOException {
        ResultData resultData = new ResultData();
        Pattern pool_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        if(pool_namePattern.matcher(ueipAddressRangeDto.getRange_name()).matches()){
            String res = DeleteDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=addr-mgmt/addr-ranges&nfId=", JSON.toJSONString(ueipAddressRangeDto));
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
        }
        else {
            resultData.setResult("FAIL");
            return resultData;
        }
    }
}
