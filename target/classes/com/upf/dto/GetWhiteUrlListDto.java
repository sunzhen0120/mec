package com.upf.dto;

import lombok.Data;

@Data
public class GetWhiteUrlListDto {
    private String access_token;
    private String url_name;
    private String nfId;
}
