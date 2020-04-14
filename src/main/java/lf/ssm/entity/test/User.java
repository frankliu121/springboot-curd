package lf.ssm.entity.test;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lf.ssm.core.base.BaseModel;
import lf.ssm.entity.sys.SysRole;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import springfox.documentation.schema.Model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Classname User
 * @Date 2019/4/18 15:05
 * @Created by 刘锋
 */

@Data
@Accessors(chain = true)
@TableName("user")
public class User extends BaseModel{

    @NotNull(message="id不能为空")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value="用户名",name="username")
    private String username;

    @ApiModelProperty(value="密码",name="username")
    private String password;

    private Integer age;

    private Date birthday;

    private String src;

    private String ip;

    private Set<SysRole> roles;

    public User(){

    }
    public User(String username,Integer age){
        this.username=username;
        this.age=age;
    }
}
