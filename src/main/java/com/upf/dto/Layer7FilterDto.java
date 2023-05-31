package com.upf.dto;

import lombok.Data;

@Data
public class Layer7FilterDto {
    /**
     * Post 与 Put 共用
     */
    private int use_well_known_port;
    private String protocol;
    private String nfId;
    private String url;
    private String sub_filter;
    private String domain_name;
    private String access_token;
    private String filter_name;
    private String ref_app_rule_name;
}




