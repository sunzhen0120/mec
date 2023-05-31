package com.upf.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;

@Data
public class Additional1UpFunctionFeaturesReturnDto {
    private String result;
    private JSON index;
    private JSONArray data;

    private JSON error;
}
