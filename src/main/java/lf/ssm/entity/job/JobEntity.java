package lf.ssm.entity.job;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lf.ssm.core.base.BaseModel;
import lf.ssm.util.ValidType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Classname JobEntity
 * @Date 2020/4/10 17:01
 * @Created by liufeng
 */

@Data
@Accessors(chain = true)
@ApiModel("任务实体")
@TableName("JOB_ENTITY")
public class JobEntity extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    @ApiModelProperty(value="id",name="id")
    @NotNull( message = "id不能为空",groups = {ValidType.Update.class,ValidType.Delete.class})
    private Long id;

    @ApiModelProperty(value="job名称",name="name")
    @NotNull( message = "任务名不能为空",groups = {ValidType.Add.class,ValidType.Update.class})
    @NotBlank( message = "任务名不能为空",groups = {ValidType.Add.class,ValidType.Update.class})
    private String name;

    @ApiModelProperty(value="job分组",name="job_group")
    private String jobGroup;

    @ApiModelProperty(value="执行的表达式",name="cronExpression")
    @TableField("cron_expression")
    @NotNull( message = "表达式不能为空",groups = {ValidType.Add.class,ValidType.Update.class})
    @NotBlank( message = "表达式不能为空",groups = {ValidType.Add.class,ValidType.Update.class})
    private String cronExpression;

    @ApiModelProperty(value="job的参数",name="parameter")
    private String parameter;

    @ApiModelProperty(value="job描述信息",name="description")
    private String description;

    @ApiModelProperty(value="标识job的执行状态 0:已停用 1已启用",name="status")
    @NotNull( message = "状态不能为空",groups = {ValidType.Add.class,ValidType.Update.class})
    @NotBlank( message = "状态不能为空",groups = {ValidType.Add.class,ValidType.Update.class})
    private String status;

    @ApiModelProperty(value="触发器名字",name="triggerName")
    @TableField("trigger_name")
    private String triggerName;

    @ApiModelProperty(value="触发器分组",name="triggerGroup")
    @TableField("trigger_group")
    private String triggerGroup;

    @ApiModelProperty(value="执行类名(包名+类名)",name="jobClassName")
    @NotNull( message = "类名不能为空",groups = {ValidType.Add.class,ValidType.Update.class})
    @NotBlank( message = "类名不能为空",groups = {ValidType.Add.class,ValidType.Update.class})
    @TableField("job_class_name")
    private String jobClassName;

    @ApiModelProperty(value="执行的方法名",name="jobClassName")
    @NotNull( message = "方法名不能为空",groups = {ValidType.Add.class,ValidType.Update.class})
    @NotBlank( message = "方法名不能为空",groups = {ValidType.Add.class,ValidType.Update.class})
    @TableField("method_name")
    private  String methodName;

    @ApiModelProperty(value="是否立即运行，0：否，1：是",name="isNowRun")
    @TableField("is_now_run")
    private String isNowRun;

    @ApiModelProperty(value="备注",name="remark")
    private String remark;

    @ApiModelProperty(value="表达式解析后的值显示",name="cronExpressionVal")
    @TableField(exist = false)
    private String cronExpressionVal;
}
