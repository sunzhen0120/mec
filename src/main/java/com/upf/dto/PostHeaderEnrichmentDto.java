package com.upf.dto;

import lombok.Data;

@Data
public class PostHeaderEnrichmentDto {
    private String access_token;
    private String field_value;
    private String field_name;
    private String nfId;

}
