package com.upf.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.upf.pojo.Multicast;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MulticastDto implements Serializable {

    private Long id;

    private Long groupId;

    private String ipFrom;

    private String ipTo;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmt_created;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmt_modified;

    public Multicast toMulticast(){
        Multicast multicast = new Multicast();
        multicast.setId(this.id);
        multicast.setGroupId(this.groupId);
        multicast.setGmt_created(this.gmt_created);
        multicast.setGmt_modified(this.gmt_modified);
        multicast.setIpFrom(JSON.parseObject(this.ipFrom, ArrayList.class));
        multicast.setIpTo(JSON.parseObject(this.ipTo,ArrayList.class));
        return multicast;
    }
}
