package lf.ssm.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 *  自定生成代码配置文件
 * @Classname generator
 * @Date 2020/4/14 20:42
 * @Created by liufeng
 */
@Component
@PropertySource("classpath:config/generator.properties")
@Data
public class MybatisGeneratorConfig {

    @ApiModelProperty("entity父类class")
    @Value("${mybatisGenerator.superEntityClass}")
    public String superEntityClass;

    @ApiModelProperty("service父类class")
    @Value("${mybatisGenerator.superServiceClass}")
    public String superServiceClass;

    @ApiModelProperty("serviceImpl父类class")
    @Value("${mybatisGenerator.superServiceImplClass}")
    public String superServiceImplClass;

    @ApiModelProperty("controller父类class")
    @Value("${mybatisGenerator.superControllerClass}")
    public String superControllerClass;

    @ApiModelProperty("数据库连接")
    @Value("${mybatisGenerator.dbUrl}")
    public String dbUrl;

    @ApiModelProperty("数据库驱动")
    @Value("${mybatisGenerator.dbDriverName}")
    public String dbDriverName;

    @ApiModelProperty("数据库密码")
    @Value("${mybatisGenerator.dbPassword}")
    public String dbPassword;

    @ApiModelProperty("数据库用户名")
    @Value("${mybatisGenerator.dbUsername}")
    public String dbUsername;

    @ApiModelProperty("父目录名")
    @Value("${mybatisGenerator.parentPackageName}")
    public String parentPackageName;

    @ApiModelProperty("entity目录名")
    @Value("${mybatisGenerator.entityDirectory}")
    public String entityDirectory;

    @ApiModelProperty("mapper目录名")
    @Value("${mybatisGenerator.mapperDirectory}")
    public String mapperDirectory;

    @ApiModelProperty("service目录名")
    @Value("${mybatisGenerator.serviceDirectory}")
    public String serviceDirectory;

    @ApiModelProperty("serviceImpl目录名")
    @Value("${mybatisGenerator.serviceImplDirectory}")
    public String serviceImplDirectory;

    @ApiModelProperty("controller目录名")
    @Value("${mybatisGenerator.controllerDirectory}")
    public String controllerDirectory;

}
