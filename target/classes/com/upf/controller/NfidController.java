package com.upf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.upf.dto.NfidDto;
import com.upf.dto.PostAppRuleDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.upf.Parameters.StartURL2;
import static com.upf.SendWithJson.Post.PostWithJson.PostDataWithJson;

@RestController
@RequestMapping("Nfid")
public class NfidController {
    @PostMapping("/GetNfidList")
    String getNfid(HttpServletRequest request,
                   HttpServletResponse response,
                   @RequestBody NfidDto nfidDto) throws IOException {

        String url=StartURL2+"/coreNetwork?agentName=oam&netElementName=&interfaceName=getAllnode&nfId=";
        String res = PostDataWithJson(url, JSON.toJSONString(nfidDto));
        return res;
    }
}