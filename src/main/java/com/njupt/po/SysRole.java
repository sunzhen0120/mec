package com.njupt.po;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2022/08/08
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole implements Serializable {
    private Long roleId;

    private String roleName;

    private String roleDescr;

    private Date createTime;

    private Date updateTime;

    private String roleType;

    private static final long serialVersionUID = 1L;
}