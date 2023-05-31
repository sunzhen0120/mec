package com.upf.dto;

import lombok.Data;

@Data
public class TokenNfidDto {
    /**
     * 常用Dto
     * 只有token和nfid两个属性的输入，可调用
     */
    private String access_token;
    private String nfId;

    public TokenNfidDto(){
        this.access_token=null;
        this.nfId=null;
    }

    public TokenNfidDto(String access_token,
                        String nfId){
        this.access_token=access_token;
        this.nfId=nfId;


    }

}
