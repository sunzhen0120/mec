package com.njupt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* Created by Mybatis Generator on 2022/11/15
*/
@Data
public class ZoneDto implements Serializable {
    private Integer zoneId;

    private String zoneName;

    private String zoneCode;

    private String serverUrl;

    private String identityUrl;

    private String networkUrl;

    private String imageUrl;

    private String userName;

    private String password;

    private String ip;

    private static final long serialVersionUID = 1L;
}