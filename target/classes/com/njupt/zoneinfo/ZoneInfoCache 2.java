package com.njupt.zoneinfo;

import com.njupt.mapper.ZoneMapper;
import com.njupt.po.Zone;
import com.njupt.po.ZoneExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@ConditionalOnClass(ZoneMapper.class)
public class ZoneInfoCache {

    public static Map<String, Zone> zoneMap = new ConcurrentHashMap<>();

    @Autowired
    private ZoneMapper zoneMapper;


    @PostConstruct
    public void init() {
        ZoneExample zoneExample = new ZoneExample();
        List<Zone> zones = zoneMapper.selectByExample(zoneExample);
        zones.stream().forEach(z -> zoneMap.putIfAbsent(z.getZoneCode(), z));
    }

    public static void reload(List<Zone> zones) {
        zones.stream().forEach(z -> zoneMap.putIfAbsent(z.getZoneCode(), z));
    }
}
