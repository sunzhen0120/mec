package com.njupt.controller;

import com.alibaba.fastjson.JSON;
import com.njupt.dto.CreateUserReq;
import com.njupt.dto.LoginReq;
import com.njupt.dto.SelectUserReq;
import com.njupt.dto.UpdateUserReq;
import com.njupt.service.RoleService;
import com.njupt.service.UserService;
import com.njupt.util.RestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author liangw
 * @create 2021-03-26 17:20
 */
@RestController
@RequestMapping("user")
@Api(tags = "sysUser", description = "系统用户管理")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping(value = "create")
    @ApiOperation(value = "新增用户")
    public void register(@RequestBody CreateUserReq createUserReq, HttpServletResponse response) {
        RestUtil.printData(response, userService.create(createUserReq));
    }


    @PostMapping(value = "selectByPage")
    @ApiOperation(value = "列表查询")
    public void selectByPage(@RequestBody SelectUserReq selectUserReq, HttpServletResponse response) {
        RestUtil.printData(response, userService.selectByPage(selectUserReq));
    }

    @PostMapping("update")
    @ApiOperation("更新用户")
    public void selectByPage(HttpServletResponse response, @RequestBody UpdateUserReq updateUserReq) {
        RestUtil.printData(response, userService.update(updateUserReq));
    }

    @PostMapping("resetpassword")
    @ApiOperation("重置密码")
    public void resetpassword(HttpServletResponse response, @RequestParam("userId") String userId) {
        RestUtil.printData(response, userService.resetpassword(Long.valueOf(userId)));
    }

    @PostMapping("statusChange")
    @ApiOperation("切换用户状态,0:停用,1正常")
    public void statusChange(HttpServletResponse response, @RequestParam("userId") String userId,
                             @RequestParam("status") String status) {
        RestUtil.printData(response, userService.statusChange(Long.valueOf(userId), status));
    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public void delete(HttpServletResponse response, @RequestParam("userId") String userId) {
        RestUtil.printData(response, userService.delete(Long.valueOf(userId)));
    }

    @PostMapping("login")
    @ApiOperation("login")
    public void login(HttpServletResponse response, @RequestBody LoginReq loginReq) {
        RestUtil.printData(response, userService.login(loginReq));
    }

    @PostMapping("getUserInfo/{userId}")
    @ApiOperation("getUserInfo")
    public void getUserInfo(HttpServletResponse response,@PathVariable("userId") String userId){
        RestUtil.printData(response, userService.getUserInfo(userId));
    }


}
