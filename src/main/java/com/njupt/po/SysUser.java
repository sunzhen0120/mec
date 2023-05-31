package com.njupt.po;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2022/07/18
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {
    private Long userId;

    private String loginName;

    private Long roleId;

    private String userName;

    private String phone;

    private String email;

    private String password;

    private String status;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}