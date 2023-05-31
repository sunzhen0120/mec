package com.njupt.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* Created by Mybatis Generator on 2022/11/15
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Zone implements Serializable {
    private Integer zoneId;

    private String zoneName;

    private String zoneCode;

    private String serverUrl;

    private String identityUrl;

    private String networkUrl;

    private String imageUrl;

    private String userName;

    private String password;

    private String userId;

    private String projectId;

    private String ip;

    private static final long serialVersionUID = 1L;
}