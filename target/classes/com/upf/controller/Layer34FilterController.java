package com.upf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upf.dto.Layer34FilterDto;
import com.upf.dto.LayerFilterDto;
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
import static com.upf.SendWithJson.Put.PutWithJson.PutDataWithJson;

@RestController
@RequestMapping("Layer34FilterController")
public class Layer34FilterController {
    /*
     ****添加Layer34FilterController
     */
    @PostMapping("/postLayer34FilterController")
    public ResultData PostLayer34Filter(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestBody Layer34FilterDto layer34FilterDto) throws IOException {

        ResultData resultData = new ResultData();
        Pattern filter_namePattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
        Pattern src_ip_addrIPv4Pattern = Pattern.compile("(^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$)|(^any$)");
        Pattern src_ip_addrIPv6Pattern = Pattern.compile("^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$|(^any$)");
        Pattern src_portPattern = Pattern.compile("(^([1-9](?:|[\\d]|[\\d][\\d]|[\\d][\\d][\\d])|[1-5][\\d][\\d][\\d][\\d]|[6][0-4][\\d][\\d][\\d]|[6][5][0-5][0-3][0-5])$)|(^any$)");

        Pattern dst_ip_addrIPv4Pattern = Pattern.compile("(^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$)|(^any$)");
        Pattern dst_ip_addrIPv6Pattern = Pattern.compile("^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$|(^any$)");

        Pattern dst_portPattern=Pattern.compile("(^([1-9](?:|[\\d]|[\\d][\\d]|[\\d][\\d][\\d])|[1-5][\\d][\\d][\\d][\\d]|[6][0-4][\\d][\\d][\\d]|[6][5][0-5][0-3][0-5])$)|(^any$)");

        Pattern ip_versionPattern = Pattern.compile("^(ipv4|ipv6)$");
        Pattern protocolPatttern = Pattern.compile("^(tcp|udp|icmp|any)$");

        boolean src_ip_addrboolen = src_ip_addrIPv4Pattern.matcher(layer34FilterDto.getSrc_ip_addr()).matches() || src_ip_addrIPv6Pattern.matcher(layer34FilterDto.getSrc_ip_addr()).matches();
        boolean dst_ip_addrboolen = dst_ip_addrIPv4Pattern.matcher(layer34FilterDto.getDst_ip_addr()).matches() || dst_ip_addrIPv6Pattern.matcher(layer34FilterDto.getDst_ip_addr()).matches();

        if(
                src_ip_addrboolen&&
                        dst_ip_addrboolen&&
                        filter_namePattern.matcher(layer34FilterDto.getFilter_name()).matches()&&
                        src_portPattern.matcher(layer34FilterDto.getSrc_port()).matches()&&
                        dst_portPattern.matcher(layer34FilterDto.getDst_port()).matches()&&
                        ip_versionPattern.matcher(layer34FilterDto.getIp_version()).matches()&&
                        protocolPatttern.matcher(layer34FilterDto.getProtocol()).matches()
        ){
            String res = PostDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/layer34-filters&nfId=", JSON.toJSONString(layer34FilterDto));

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
        }
        else{
            resultData.setResult("FAIL");
            return resultData;
        }
    }
    /*
     ****查询Layer34FilterController
     */
    @PostMapping("/getLayer34FilterController")
    public ReslutIndexData GetLayer34Filter(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 @RequestBody LayerFilterDto layerFilterDto) throws IOException {

        ReslutIndexData reslutIndexData = new ReslutIndexData();
        Pattern filter_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");

        if(filter_namePattern.matcher(layerFilterDto.getFilter_name()).matches() || layerFilterDto.getFilter_name().equals("null")){
            String res = GetDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/layer34-filters&nfId=", JSON.toJSONString(layerFilterDto));

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
        else {
            reslutIndexData.setResult("FAIL");
            return reslutIndexData;
        }
    }
    /*
     ****修改Layer34FilterController
     */
    @PostMapping("/putLayer34FilterController")
    public ResultData PutLayer34Filter(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestBody Layer34FilterDto layer34FilterDto) throws IOException {

        ResultData resultData = new ResultData();
        Pattern filter_namePattern = Pattern.compile("^[\\da-zA-Z]{1,32}$");
        Pattern src_ip_addrIPv4Pattern = Pattern.compile("(^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$)|(^any$)");
        Pattern src_ip_addrIPv6Pattern = Pattern.compile("^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$|(^any$)");
        Pattern src_portPattern = Pattern.compile("(^([1-9](?:|[\\d]|[\\d][\\d]|[\\d][\\d][\\d])|[1-5][\\d][\\d][\\d][\\d]|[6][0-4][\\d][\\d][\\d]|[6][5][0-5][0-3][0-5])$)|(^any$)");

        Pattern dst_ip_addrIPv4Pattern = Pattern.compile("(^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$)|(^any$)");
        Pattern dst_ip_addrIPv6Pattern = Pattern.compile("^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$|(^any$)");

        Pattern dst_portPattern=Pattern.compile("(^([1-9](?:|[\\d]|[\\d][\\d]|[\\d][\\d][\\d])|[1-5][\\d][\\d][\\d][\\d]|[6][0-4][\\d][\\d][\\d]|[6][5][0-5][0-3][0-5])$)|(^any$)");

        Pattern ip_versionPattern = Pattern.compile("^(ipv4|ipv6)$");
        Pattern protocolPatttern = Pattern.compile("^(tcp|udp|icmp|any)$");

        boolean src_ip_addrboolen = src_ip_addrIPv4Pattern.matcher(layer34FilterDto.getSrc_ip_addr()).matches() || src_ip_addrIPv6Pattern.matcher(layer34FilterDto.getSrc_ip_addr()).matches();
        boolean dst_ip_addrboolen = dst_ip_addrIPv4Pattern.matcher(layer34FilterDto.getDst_ip_addr()).matches() || dst_ip_addrIPv6Pattern.matcher(layer34FilterDto.getDst_ip_addr()).matches();

        if(
                src_ip_addrboolen&&
                        dst_ip_addrboolen&&
                        filter_namePattern.matcher(layer34FilterDto.getFilter_name()).matches()&&
                        src_portPattern.matcher(layer34FilterDto.getSrc_port()).matches()&&
                        dst_portPattern.matcher(layer34FilterDto.getDst_port()).matches()&&
                        ip_versionPattern.matcher(layer34FilterDto.getIp_version()).matches()&&
                        protocolPatttern.matcher(layer34FilterDto.getProtocol()).matches()
        ){
            String res = PutDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/layer34-filters&nfId=", JSON.toJSONString(layer34FilterDto));
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
        }
        else {
            resultData.setResult("FAIL");
            return resultData;
        }
    }
    /*
     ****删除Layer34FilterController
     */
    @PostMapping("/deleteLayer34FilterController")
    public ResultData DeleteLayer34Filter(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestBody LayerFilterDto layerFilterDto) throws IOException {

        ResultData resultData = new ResultData();
        Pattern filter_namePattern=Pattern.compile("^[\\da-zA-Z]{1,32}$");

        if(filter_namePattern.matcher(layerFilterDto.getFilter_name()).matches()){
            String res = DeleteDataWithJson(StartURL+"coreNetwork?agentName=cm&netElementName=upf&interfaceName=app/layer34-filters&nfId=", JSON.toJSONString(layerFilterDto));

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
        }
        else {
            resultData.setResult("FAIL");
            return resultData;
        }
    }
}
