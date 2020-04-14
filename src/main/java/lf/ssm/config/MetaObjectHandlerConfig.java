package lf.ssm.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lf.ssm.util.Const;
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
        Object isDelete = getFieldValByName("is_delete", metaObject);
        Object createName = getFieldValByName("create_name", metaObject);

        //mybatis-plus版本2.0.9+
        if (createTime == null)
            setFieldValByName("createTime",new Date(), metaObject);
        if (isDelete == null ){
            setFieldValByName("isDelete", Const.DEFAULT_NO_STR, metaObject);
        }
        if (createName == null){
            // todo}
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("update_time", metaObject);
        Object modifyName = getFieldValByName("modify_name", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
        if (modifyName == null) {
            // todo
        }
    }
}


