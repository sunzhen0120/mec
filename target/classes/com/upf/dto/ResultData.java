package com.upf.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;

@Data
public class ResultData {
    private String result;
    private JSON data;
    private String error;
}
