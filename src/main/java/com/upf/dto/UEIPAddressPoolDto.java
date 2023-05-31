package com.upf.dto;

import lombok.Data;

@Data
public class UEIPAddressPoolDto {
    /**
     * get 与 delete共用
     */
    private String access_token;
    private String pool_name;
    private String nfId;
}
