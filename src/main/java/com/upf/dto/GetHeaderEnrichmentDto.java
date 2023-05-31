package com.upf.dto;

import lombok.Data;

@Data
public class GetHeaderEnrichmentDto {
    private String access_token;
    private String field_name;
    private String nfId;
}
