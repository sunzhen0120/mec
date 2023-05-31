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
import static com.upf.utils.Judge.Uint8;

@RestController
@RequestMapping("PFCPAssocSetupController")
public class PFCPAssocSetupController {
    /*
    ****添加PFCPAssocSetupController
     */
    @PostMapping("/postPFCPAssocSetupController")
    public ResultData PostPfcpProperties(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestBody PfcpAssocSetupDto pfcpAssocSetupDto) throws IOException {

        ResultData resultData = new ResultData();
        Pattern ip_versionPattern=Pattern.compile("^(ipv4|ipv6)$");
        Pattern smf_node_ipv6_addrPattern=Pattern.compile("^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$");
        Pattern smf_node_ipv4_addrPattern=Pattern.compile("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");
        Pattern smf_nf_idPattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        boolean judgeIp=true;
        if(pfcpAssocSetupDto.getIp_version().equals("ipv4")){
            judgeIp=smf_node_ipv4_addrPattern.matcher(pfcpAssocSetupDto.getSmf_node_ipv4_addr()).matches();
        }else if (pfcpAssocSetupDto.getIp_version().equals("ipv6")){
            judgeIp=smf_node_ipv6_addrPattern.matcher(pfcpAssocSetupDto.getSmf_node_ipv6_addr()).matches();
        }else {
            judgeIp=ip_versionPattern.matcher(pfcpAssocSetupDto.getIp_version()).matches();
        }
        if(     judgeIp&&
                Uint8(pfcpAssocSetupDto.getTeid_range())&&
                Uint8(pfcpAssocSetupDto.getTeid_range_indication())&&0<=pfcpAssocSetupDto.getTeid_range_indication()&&pfcpAssocSetupDto.getTeid_range_indication()<=7&&
                smf_nf_idPattern.matcher(pfcpAssocSetupDto.getSmf_nf_id()).matches()){
            String res = PostDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/assoc-setup&nfId=", JSON.toJSONString(pfcpAssocSetupDto));
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
        else {
            resultData.setResult("FAIL");
            return resultData;
        }
    }
    /*
     ****查询PFCPAssocSetupController
     */
    @PostMapping("/getPFCPAssocSetupController")
    public ReslutIndexData GetPfcpProperties(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestBody TokenNfidDto tokenNfidDto) throws IOException {

        ReslutIndexData reslutIndexData = new ReslutIndexData();
        String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/assoc-setup&nfId=", JSON.toJSONString(tokenNfidDto));

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
    }
    /*
     ****修改PFCPAssocSetupController
     */
    @PostMapping("/putPFCPAssocSetupController")
    public ResultData PutPfcpProperties(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestBody PfcpAssocSetupDto pfcpAssocSetupDto) throws IOException {

        ResultData resultData = new ResultData();
        Pattern smf_node_ipv6_addrPattern=Pattern.compile("^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$");
        Pattern ip_versionPattern=Pattern.compile("^(ipv4|ipv6)$");
        Pattern smf_node_ipv4_addrPattern=Pattern.compile("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");
        Pattern smf_nf_idPattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        boolean judgeIp=true;
        if(pfcpAssocSetupDto.getIp_version().equals("ipv4")){
            judgeIp=smf_node_ipv4_addrPattern.matcher(pfcpAssocSetupDto.getSmf_node_ipv4_addr()).matches();
        }else if (pfcpAssocSetupDto.getIp_version().equals("ipv6")){
            judgeIp=smf_node_ipv6_addrPattern.matcher(pfcpAssocSetupDto.getSmf_node_ipv6_addr()).matches();
        }else {
            judgeIp=ip_versionPattern.matcher(pfcpAssocSetupDto.getIp_version()).matches();
        }

        if(     judgeIp&&
                Uint8(pfcpAssocSetupDto.getTeid_range())&&
                Uint8(pfcpAssocSetupDto.getTeid_range_indication())&&0<=pfcpAssocSetupDto.getTeid_range_indication()&&pfcpAssocSetupDto.getTeid_range_indication()<=7&&
                smf_nf_idPattern.matcher(pfcpAssocSetupDto.getSmf_nf_id()).matches()){
            String res = PutDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/assoc-setup&nfId=", JSON.toJSONString(pfcpAssocSetupDto));

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
     ****删除PFCPAssocSetupController
     */
    @PostMapping("/deletePFCPAssocSetupController")
    public ResultData DeletePfcpProperties(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestBody DeletePfcpAssocSetupDto deletePfcpAssocSetupDto) throws IOException {

        ResultData resultData = new ResultData();
        Pattern smf_nf_idPattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");
        if(smf_nf_idPattern.matcher(deletePfcpAssocSetupDto.getSmf_nf_id()).matches()||deletePfcpAssocSetupDto.getSmf_nf_id().equals(null)){
            String res = DeleteDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=pfcp/assoc-setup&nfId=", JSON.toJSONString(deletePfcpAssocSetupDto));
            JSONObject jsonObject = JSON.parseObject(res);
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
        else {
            resultData.setResult("FAIL");
            return resultData;
        }
    }
}
