package lf.ssm.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lf.ssm.core.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;

import lf.ssm.util.ValidType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author liufeng
 * @since 2020-04-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_generator_record")
public class SysGeneratorRecord extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * ip
     */
    private String ip;

    /**
     * 表名
     */
    @TableField("table_name")
    @NotNull( message = "表名不能为空",groups = {ValidType.Add.class})
    private String tableName;

    /**
     * 小模块名
     */
    @TableField("module_name")
    private String moduleName;

    /**
     * 包名
     */
    @TableField("package_name")
    private String packageName;

    /**
     * 输出目录
     */
    @TableField("target_directory")
    @NotNull( message = "输出目录不能为空",groups = {ValidType.Add.class})
    @NotEmpty( message = "输出目录不能为空",groups = {ValidType.Add.class})
    private String targetDirectory;


}
