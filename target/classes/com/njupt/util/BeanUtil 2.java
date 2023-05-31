package com.njupt.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangw
 * @create 2021-03-25 14:29
 */
public class BeanUtil extends BeanUtils {


    /**
     * 封装BeanUtils.copyProperties 数组转换
     * @param resourceList 源数组
     * @param target 目标对象
     * @param <T> 目标对象类型
     * @return
     */
    public static <T> List<T> copyBeanList(List<?> resourceList, Class<T> target){
        List<T> targetList = new ArrayList<>();
        if (null==resourceList||resourceList.isEmpty()){
            return targetList;
        }
        resourceList.forEach(e->{
            T o = null;
            try {
                o = target.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
            BeanUtils.copyProperties(e,o);
            targetList.add(o);
        });
        return targetList;
    }
}
