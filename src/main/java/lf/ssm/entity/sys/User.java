package lf.ssm.entity.sys;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lf.ssm.core.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * user 储存用户信息
 * </p>
 *
 * @author liufeng
 * @since 2020-04-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 账号
     */
    @TableField("user_name")
    private String userName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 年龄
     */
    private int age;

    /**
     * 盐
     */
    private String salt;

    @TableField(exist = false)
    private List<SysRole> roles; // 一个用户具有多个角色
}
