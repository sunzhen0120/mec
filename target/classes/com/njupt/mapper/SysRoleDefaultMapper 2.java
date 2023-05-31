package com.njupt.mapper;

import com.njupt.po.SysRoleDefault;
import com.njupt.po.SysRoleDefaultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleDefaultMapper {
    long countByExample(SysRoleDefaultExample example);

    int deleteByExample(SysRoleDefaultExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRoleDefault record);

    int insertSelective(SysRoleDefault record);

    List<SysRoleDefault> selectByExample(SysRoleDefaultExample example);

    SysRoleDefault selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRoleDefault record, @Param("example") SysRoleDefaultExample example);

    int updateByExample(@Param("record") SysRoleDefault record, @Param("example") SysRoleDefaultExample example);

    int updateByPrimaryKeySelective(SysRoleDefault record);

    int updateByPrimaryKey(SysRoleDefault record);
}