package com.njupt.dto;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liangw
 * @create 2021-04-01 15:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {

    @ApiModelProperty(value = "当前页")
    private int pageNum;

    @ApiModelProperty(value = "条数")
    private int pageSize;

    @ApiModelProperty(hidden = true)
    private long total;

    @ApiModelProperty(hidden = true)
    private Object list;

    public <T> List<T> getList(Class<T> responseType) {
        return JSONObject.parseArray(JSONObject.toJSONString(this.getList())).toJavaList(responseType);

    }
}
