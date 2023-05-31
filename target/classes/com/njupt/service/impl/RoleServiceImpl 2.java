package com.njupt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njupt.dto.*;
import com.njupt.mapper.SysRoleMapper;
import com.njupt.mapper.SysUserMapper;
import com.njupt.po.SysRole;
import com.njupt.po.SysRoleExample;
import com.njupt.po.SysUserExample;
import com.njupt.res.CommonResult;
import com.njupt.service.RoleService;
import com.njupt.util.BeanUtil;
import com.njupt.util.SeqIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public CommonResult create(CreateRoleReq createRoleReq) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleDescr(createRoleReq.getRoleDescr());
        sysRole.setRoleName(createRoleReq.getRoleName());
        sysRole.setRoleId(SeqIdUtil.getId());
        sysRole.setRoleType(createRoleReq.getType());
        sysRoleMapper.insertSelective(sysRole);
        return CommonResult.success();
    }

    @Override
    public CommonResult update(UpdateRoleReq updateRoleReq) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(updateRoleReq.getRoleId());
        if (sysRole != null) {
            sysRole.setRoleName(updateRoleReq.getRoleName());
            sysRole.setRoleDescr(updateRoleReq.getRoleDescr());
            sysRole.setRoleType(updateRoleReq.getType());
            sysRole.setUpdateTime(new Date());
            sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult selectByPage(SelectRoleReq selectRoleReq) {
        Page page = PageHelper.startPage(selectRoleReq.getPageNum(), selectRoleReq.getPageSize());
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.setOrderByClause("create_time desc,update_time desc");
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        PageDto pageDto = new PageDto(selectRoleReq.getPageNum(), selectRoleReq.getPageSize(), page.getTotal(),
                BeanUtil.copyBeanList(sysRoles, SysRoleDto.class));
        return CommonResult.success(pageDto);
    }

    @Override
    public CommonResult selectRoles() {
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.setOrderByClause("create_time desc,update_time desc");
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        List<SysRoleDto> sysRoleDtos = BeanUtil.copyBeanList(sysRoles, SysRoleDto.class);
        return CommonResult.success(sysRoleDtos);
    }

    @Override
    public CommonResult delete(Long roleId) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andRoleIdEqualTo(roleId);
        long l = sysUserMapper.countByExample(sysUserExample);
        if (l > 0) {
            return CommonResult.failed("角色下有用户关联");
        }
        sysRoleMapper.deleteByPrimaryKey(roleId);
        return CommonResult.success();
    }
}
