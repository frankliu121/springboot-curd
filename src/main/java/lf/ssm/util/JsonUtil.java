package lf.ssm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.List;

/**
 * JSON 工具类
 * @author Administrator
 *
 */
public class JsonUtil {
	
	private static SerializeConfig mapping = new SerializeConfig();
    static {
        mapping.put(java.util.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        mapping.put(java.sql.Timestamp.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }
    
	/**
	 * 转换普通 Java 对象为 JSON 对象, 转换 List/Set 为 JSON 数组, 转换 Map 为 JSON 对象
	 * @param <T>
	 * @param object
	 * @return
	 */
	public static <T> String toJson(T object) {
		return JSON.toJSONString(object, mapping);
	}
	
	/**
	 * 转换 JSON 对象为 Java 对象, 注意两点: 
	 * 1. 提供无参构造函数; 
	 * 2. 对于 Map 类属性, 需要首先初始化. 
	 * @param <T>
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> T toBean(String json, Class<T> c) {
		return JSON.parseObject(json, c);
	}
	
	/**
	 * 转换 JSON 对象/数组为 Java List, 注意两点: 
	 * 1. 提供无参构造函数; 
	 * 2. 对于 Map 类属性, 需要首先初始化. 
	 * @param <T>
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> List<T> toList(String json, Class<T> c) {
		return JSON.parseArray(json, c);
	}
	
	
	public static void main(String[] args) {
		
	}
	
}

