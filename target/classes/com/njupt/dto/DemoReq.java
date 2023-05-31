package com.njupt.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DemoReq implements Serializable {

    private String parameter1;

    private String parameter2;
}
