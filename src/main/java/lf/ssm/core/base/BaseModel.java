package lf.ssm.core.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname BaseModel
 * @Date 2020/4/8 11:21
 * @Created by liufeng
 */

@Data
@ToString
public class BaseModel implements Serializable {

    @TableField(value = "update_time",fill= FieldFill.UPDATE)
    @ApiModelProperty(value="更新时间",name="updateTime")
    private Date updateTime;

    @TableField(value = "modify_name",fill= FieldFill.UPDATE)
    @ApiModelProperty(value="修改人",name="modifyName")
    private String modifyName;

    @TableField(value = "create_time",fill=FieldFill.INSERT) //插入或更新是都fill
    @ApiModelProperty(value="创建时间",name="createTime")
    private Date createTime;

    @TableField(value = "create_name",fill=FieldFill.INSERT) //插入或更新是都fill
    @ApiModelProperty(value="创建人",name="createName")
    private String createName;

    @TableField(value = "is_delete",fill=FieldFill.INSERT) //插入或更新是都fill
    @ApiModelProperty(value="是否删除:0否 1是",name="isDelete")
    private  String isDelete;
}
