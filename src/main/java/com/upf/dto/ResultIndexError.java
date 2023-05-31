package com.upf.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class ResultIndexError {
    private String result;
    private JSON index;
    private String error;
}
