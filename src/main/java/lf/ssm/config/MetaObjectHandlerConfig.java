package lf.ssm.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Classname MetaObjectHandlerConfig
 * @Date 2020/4/7 15:22
 * @Created by liufeng
 */
@Component
public class MetaObjectHandlerConfig extends MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        Object createTime = getFieldValByName("create_time", metaObject);
        Object updateTime = getFieldValByName("update_time", metaObject);
        if (createTime == null)
            setFieldValByName("createTime",new Date(), metaObject);//mybatis-plus版本2.0.9+
        if (updateTime == null)
            setFieldValByName("updateTime",new Date(), metaObject);//mybatis-plus版本2.0.9+
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", new Date(), metaObject);//mybatis-plus版本2.0.9+
        }
    }
}


