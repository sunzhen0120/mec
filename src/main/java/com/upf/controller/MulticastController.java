package com.upf.controller;

import com.alibaba.fastjson.JSON;
import com.upf.dto.MulticastDto;
import com.upf.dto.ResultData;
import com.upf.mapper.MulticastMapper;
import com.upf.pojo.Multicast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("multicast")
public class MulticastController {

    @Autowired
    private MulticastMapper multicastMapper;

    @PostMapping("/getAll")
    public Map<String,Object> getAllMulticast(){
        List<MulticastDto> multicastDtos = multicastMapper.selectAll();
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("success",true);
        List<Multicast> multicasts = new ArrayList<>();
        multicastDtos.forEach(multicastDto -> {
            multicasts.add(multicastDto.toMulticast());
        });
        resultMap.put("data", multicasts);

        return resultMap;
    }

    @PostMapping("/addMulticast")
    public Map<String,String> addMulticast(@RequestBody Multicast multicast){
        MulticastDto multicastDto = new MulticastDto();
        Map<String,String> resultMap = new HashMap<>();

        multicastDto.setGroupId(multicast.getGroupId());
        multicastDto.setIpFrom(JSON.toJSONString(multicast.getIpFrom()));
        multicastDto.setIpTo(JSON.toJSONString(multicast.getIpTo()));

        int insert = multicastMapper.insert(multicastDto);
        if(insert>0){
            resultMap.put("success","true");
            resultMap.put("message","操作成功");
        }
        else {
            resultMap.put("success","false");
            resultMap.put("message","操作失败");
        }
        return resultMap;
    }

    @PostMapping("/delById")
    public Map<String,String> deleteByGroupId(@RequestParam("id") Long id){
        Map<String,String> resultMap = new HashMap<>();

        multicastMapper.deleteById(id);

        resultMap.put("success","true");
        resultMap.put("message","操作成功");

        return resultMap;
    }

    @PostMapping("/updateMulticast")
    public Map<String,String> updateMulticast(@RequestBody Multicast multicast){
        Map<String,String> resultMap = new HashMap<>();
        MulticastDto multicastDto = new MulticastDto();

        multicastDto.setId(multicast.getId());
        multicastDto.setGroupId(multicast.getGroupId());
        multicastDto.setIpFrom(JSON.toJSONString(multicast.getIpFrom()));
        multicastDto.setIpTo(JSON.toJSONString(multicast.getIpTo()));

        int update = multicastMapper.update(multicastDto);
        if(update>0){
            resultMap.put("success","true");
            resultMap.put("message","操作成功");
        }
        else {
            resultMap.put("success","false");
            resultMap.put("message","操作失败");
        }
        return resultMap;

    }
}
