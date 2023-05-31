package com.upf.dto;

import lombok.Data;

@Data
public class PFCPAssocReleaseDto {
    private String access_token;
    private String nfId;
    private int urss;
    private int sarr;
    private int timer_value;
    private int timer_unit;
}
