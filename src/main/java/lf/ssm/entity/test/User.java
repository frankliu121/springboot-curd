package lf.ssm.entity.test;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lf.ssm.core.base.BaseModel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import springfox.documentation.schema.Model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Classname User
 * @Date 2019/4/18 15:05
 * @Created by 刘锋
 */

@Data
@Accessors(chain = true)
public class User extends BaseModel{
    @NotNull(message="id不能为空")
    private Long id;

    @NotNull(message="username不能为空")
    private String username;

    private Integer age;

    private Date birthday;

    private String src;

    private String ip;

    public User(){

    }
    public User(String username,Integer age){
        this.username=username;
        this.age=age;
    }
}
