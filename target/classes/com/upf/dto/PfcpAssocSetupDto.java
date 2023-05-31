package com.upf.dto;

import lombok.Data;

@Data
public class PfcpAssocSetupDto {
    private  String smf_node_ipv6_addr;
    private  String nfId;
    private  String access_token;
    private int teid_range;
    private int teid_range_indication;
    private  String ip_version;
    private  String smf_node_ipv4_addr;
    private  String smf_nf_id;
}
