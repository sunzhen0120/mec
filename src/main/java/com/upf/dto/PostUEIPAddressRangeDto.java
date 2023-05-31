package com.upf.dto;

import lombok.Data;

@Data
public class PostUEIPAddressRangeDto {
     private String pool_name;
     private String range_name;
     private String nfId;
     private String access_token;
     private String net_mask;
     private String ip_num;
     private String start_ip;
}
