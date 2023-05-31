package com.njupt.service.impl;

import com.njupt.dto.ZoneDto;
import com.njupt.mapper.ZoneMapper;
import com.njupt.po.Zone;
import com.njupt.po.ZoneExample;
import com.njupt.res.CommonResult;
import com.njupt.service.ZoneService;
import com.njupt.util.BeanUtil;
import com.njupt.zoneinfo.ZoneInfoCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneMapper zoneMapper;

    @Override
    public CommonResult getZoneList() {
        ZoneExample zoneExample = new ZoneExample();
        List<Zone> zones = zoneMapper.selectByExample(zoneExample);
        List<ZoneDto> zoneDtos = BeanUtil.copyBeanList(zones, ZoneDto.class);
        ZoneInfoCache.reload(zones);
        return CommonResult.success(zoneDtos);
    }
}
