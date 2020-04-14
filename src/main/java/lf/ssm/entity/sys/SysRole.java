package lf.ssm.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lf.ssm.core.base.BaseModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @Classname SysRole
 * @Date 2020/4/14 16:36
 * @Created by liufeng
 */

@Data
@Accessors(chain = true)
public class SysRole extends BaseModel {

    @TableId(value = "id", type = IdType.ID_WORKER)
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "角色名")
    @TableField("role_name")
    private String roleName;

    private Set<SysPermissions> permissions;
}
