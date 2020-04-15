package lf.ssm.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lf.ssm.core.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 *  
 * </p>
 *
 * @author liufeng
 * @since 2020-04-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_permission")
public class SysPermission extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 权限地址
     */
    private String url;

    /**
     * 角色
     */
    @TableField(exist = false)
    private List<SysRole> roles; // 一个权限可以被多个角色使用
}
