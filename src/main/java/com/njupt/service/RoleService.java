package com.njupt.service;

import com.njupt.dto.CreateRoleReq;
import com.njupt.dto.SelectRoleReq;
import com.njupt.dto.UpdateRoleReq;
import com.njupt.res.CommonResult;

public interface RoleService {

    CommonResult create(CreateRoleReq createRoleReq);

    CommonResult update(UpdateRoleReq updateRoleReq);

    CommonResult selectByPage(SelectRoleReq selectRoleReq);

    CommonResult selectRoles();

    CommonResult delete(Long roleId);
}
