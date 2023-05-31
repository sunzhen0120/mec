package com.upf.dto;

import lombok.Data;

@Data
public class PostAppRuleDto {
    private String ref_app_id;
    private String nfId;
    private String access_token;
    private String l34_filter_name;
    private String rule_name;
}

