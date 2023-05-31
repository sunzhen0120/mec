package com.upf.dto;

import lombok.Data;

@Data
public class PutPfcpPropertiesDto {
    private int t1_response;
    private String nfId;
    private String access_token;
    private int n1_request;
    private int heartbeat_interval;
    private int max_outgoing_request;
    private int inactivity_timer;
    private int max_incoming_request;
}
