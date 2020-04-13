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
 * @Created by 刘锋
 */

@Data
@ToString
public class BaseModel implements Serializable {

    @TableField(value = "update_time",fill= FieldFill.INSERT)
    @ApiModelProperty(value="更新时间",name="updateTime")
    private Date updateTime;

    @TableField(value = "create_time",fill=FieldFill.INSERT_UPDATE) //插入或更新是都fill
    @ApiModelProperty(value="创建时间",name="createTime")
    private Date createTime;
}
