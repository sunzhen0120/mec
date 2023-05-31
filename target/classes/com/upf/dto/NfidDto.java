package com.upf.dto;

import lombok.Data;

@Data
public class NfidDto {
    private String access_token;
    private String nfType;
    private int systemID;
    private String nfId;
}