package com.njupt.controller;

import com.njupt.dto.CreateRoleReq;
import com.njupt.dto.SelectRoleReq;
import com.njupt.dto.UpdateRoleReq;
import com.njupt.res.CommonResult;
import com.njupt.service.RoleService;
import com.njupt.util.RestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("role")
@Api(tags = "角色管理", value = "角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("create")
    @ApiOperation("创建角色")
    public void createRole(HttpServletResponse response, @RequestBody CreateRoleReq roleReq) {
        RestUtil.printData(response, roleService.create(roleReq));
    }

    @PostMapping("update")
    @ApiOperation("更新角色")
    public void createRole(HttpServletResponse response, @RequestBody UpdateRoleReq roleReq) {
        RestUtil.printData(response, roleService.update(roleReq));
    }

    @PostMapping("selectByPage")
    @ApiOperation("分页查询")
    public void selectByPage(HttpServletResponse response, @RequestBody SelectRoleReq roleReq) {
        RestUtil.printData(response, roleService.selectByPage(roleReq));
    }

    @PostMapping("selectRoles")
    @ApiOperation("查询角色")
    public void selectByPage(HttpServletResponse response) {
        RestUtil.printData(response, roleService.selectRoles());
    }

    @PostMapping("delete")
    @ApiOperation("删除角色")
    public void delete(HttpServletResponse response, @RequestParam("roleId") String roleId) {
        RestUtil.printData(response, roleService.delete(Long.valueOf(roleId)));
    }
}
