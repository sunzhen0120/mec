package com.njupt.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetTokenReq implements Serializable {

    private String userName;

    private String password;
}
