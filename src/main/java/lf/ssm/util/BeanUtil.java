package lf.ssm.util;


import lf.ssm.entity.test.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname BeanUtil
 * @Date 2020/4/12 11:08
 * @Created by liufeng
 */
@Slf4j
public class BeanUtil {

    /**
     * 拷贝实体，source,target不允许为空
     *
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 拷贝实体集合，sourceList，targetList不允许为空
     *
     * @param sourceList
     * @param targetList
     */
    public static void copyPropertiesList(List sourceList, List targetList) {
        if (CollectionUtils.isEmpty(sourceList) || CollectionUtils.isEmpty(targetList)) {
            return;
        }
        sourceList.forEach(items -> {
            Object target = new Object();
            BeanUtils.copyProperties(items, target);
            targetList.add(target);
        });
    }

    /**
     * 实体转换为Map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        if (bean == null) {
            return null;
        } else {
            Map<String, Object> map = new HashMap(16);
            BeanMap beanMap = BeanMap.create(bean);
            Iterator iterator = beanMap.keySet().iterator();

            while(iterator.hasNext()) {
                Object key = iterator.next();
                map.put(String.valueOf(key), beanMap.get(key));
            }

            return map;
        }
    }


    /**
     * 实体List转换为maps
     * @param beanList
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, Object>> beansToMaps(List<T> beanList) {
        return com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isEmpty(beanList) ? null : (List)beanList.stream().map(com.baomidou.mybatisplus.core.toolkit.BeanUtils::beanToMap).collect(Collectors.toList());
    }


    /**
     * map转 Bean
     * @author liufeng
     * @date 2020/4/12 16:03
     * @return T
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) throws Exception {
        if (map == null || clazz == null)
            return null;

        T obj = clazz.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("username","liufeng");
        map.put("age",23);
        User user = new User();
        try {
            user = mapToBean(map,user.getClass());
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
