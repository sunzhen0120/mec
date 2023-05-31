package com.upf.dto;

import lombok.Data;

@Data
public class UEIPAddressRangeDto {
    /**
     * get 与 delete共用
     */
    private String access_token;
    private String range_name;
    private String nfId;
}
