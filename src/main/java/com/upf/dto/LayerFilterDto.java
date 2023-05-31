package com.upf.dto;

import lombok.Data;

@Data
public class LayerFilterDto {
    /**
     * Get34与7共用
     * delete 7用
     */
    private String access_token;
    private String nfId;
    private String filter_name;
}
