package com.njupt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njupt.dto.*;
import com.njupt.mapper.SysRoleMapper;
import com.njupt.mapper.SysUserMapper;
import com.njupt.po.SysRole;
import com.njupt.po.SysUser;
import com.njupt.po.SysUserExample;
import com.njupt.res.CommonResult;
import com.njupt.service.UserService;
import com.njupt.util.BeanUtil;
import com.njupt.util.MD5Util;
import com.njupt.util.SeqIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CommonResult create(CreateUserReq userReq) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginNameEqualTo(userReq.getLoginName());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (!CollectionUtils.isEmpty(sysUsers)) {
            return CommonResult.failed("登录名已存在");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserId(SeqIdUtil.getId());
        String encodePassword = MD5Util.md5Encrypt(userReq.getPassword());
        sysUser.setRoleId(userReq.getRoleId());
        sysUser.setPassword(encodePassword);
        sysUser.setLoginName(userReq.getLoginName());
        sysUser.setUserName(userReq.getUserName());
        sysUser.setStatus("1");
        sysUserMapper.insertSelective(sysUser);
        return CommonResult.success();
    }

    @Override
    public CommonResult selectByPage(SelectUserReq selectUserReq) {
        Page page = PageHelper.startPage(selectUserReq.getPageNum(), selectUserReq.getPageSize());
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.setOrderByClause("create_time desc,update_time desc");
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        return CommonResult.success(new PageDto(selectUserReq.getPageNum(), selectUserReq.getPageSize(), page.getTotal(),
                BeanUtil.copyBeanList(sysUsers, SysUserDto.class)));
    }

    @Override
    public CommonResult update(UpdateUserReq userReq) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userReq.getUserId());
        if (sysUser == null) {
            return CommonResult.failed("用户不存在");
        }
        sysUser.setUserName(userReq.getUserName());
        sysUser.setRoleId(userReq.getRoleId());
        sysUser.setUpdateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return CommonResult.success();
    }

    @Override
    public CommonResult resetpassword(Long userId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (sysUser == null) {
            return CommonResult.failed("用户不存在");
        }
        String encodePassword = MD5Util.md5Encrypt("123456");
        sysUser.setPassword(encodePassword);
        sysUser.setUpdateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return CommonResult.success();
    }

    @Override
    public CommonResult statusChange(Long userId, String status) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (sysUser == null) {
            return CommonResult.failed("用户不存在");
        }
        sysUser.setStatus(status);
        sysUser.setUpdateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return CommonResult.success();
    }

    @Override
    public CommonResult delete(Long userId) {
        sysUserMapper.deleteByPrimaryKey(userId);
        return CommonResult.success();
    }

    @Override
    public CommonResult login(LoginReq loginReq) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginNameEqualTo(loginReq.getLoginName());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (!CollectionUtils.isEmpty(sysUsers)) {
            SysUser sysUser = sysUsers.get(0);
            if (StringUtils.equals(sysUser.getPassword(), loginReq.getPassword())) {
                SysUserDto sysUserDto = new SysUserDto();
                BeanUtil.copyProperties(sysUser,sysUserDto);
                return CommonResult.success(sysUserDto);
//                return CommonResult.success("登录成功");
            } else {
                return CommonResult.failed("密码错误");
            }
        }
        return CommonResult.failed("用户不存在");
    }

    @Override
    public CommonResult getUserInfo(String userId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(Long.valueOf(userId));
        SysUserDto sysUserDto = new SysUserDto();
        if (sysUser != null) {
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(sysUser.getRoleId());
            BeanUtil.copyProperties(sysUser, sysUserDto);
            if (sysRole != null) {
                sysUserDto.setRoleType(sysRole.getRoleType());
            }
        }
        return CommonResult.success(sysUserDto);
    }
}
