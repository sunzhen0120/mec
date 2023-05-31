package com.upf.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;

@Data
public class ReslutIndexData {
    private String result;
    private JSON index;
    private JSONArray data;
    private String error;
}
