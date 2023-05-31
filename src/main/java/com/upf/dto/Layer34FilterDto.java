package com.upf.dto;

import lombok.Data;

@Data
public class Layer34FilterDto {
    public String src_port;
    public String nfId;
    public String access_token;
    public String dst_port;
    public String filter_name;
    public String ip_version;
    public String protocol;
    public String src_ip_addr;
    public String dst_ip_addr;
}
