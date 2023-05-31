package com.upf.mapper;

import com.upf.dto.MulticastDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MulticastMapper {

    List<MulticastDto> selectAll();

    int insert(MulticastDto multicastDto);

    int update(MulticastDto multicastDto);

    int deleteById(@Param("id") long id);
}
