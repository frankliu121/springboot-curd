package lf.ssm.mybatisPlus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lf.ssm.core.base.BaseModel;
import lf.ssm.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname TestMybatisGenerate
 * @Date 2020/4/7 19:03
 * @Created by 刘锋
 */
public class TestMybatisGenerate {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    @Test
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig();
        String projectPath = "F:/generate";  //配置生成目录
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("liufeng");
        gc.setOpen(true);//是否打开目录
        gc.setIdType(IdType.ID_WORKER);// id生成策略
        gc.setFileOverride(true); //是否覆盖
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML 生成ResultMap
        gc.setBaseColumnList(true);// XML 生成columList
        //gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);

        mpg.setDataSource(dsc);

        // 包配置 (设置包名)
        PackageConfig pc = new PackageConfig();
        String moduleName = scanner("请输入模块名");
        String entityDir = "entity";
        String mapperDir = "mapper";
        String serviceDir = "service";
        String serviceImplDir = "service.impl";
        String controllerDir = "controller";
        if (StringUtils.isNotBlank(moduleName)){
            moduleName = "." + moduleName;
            entityDir = entityDir + moduleName;
            mapperDir = mapperDir + moduleName;
            serviceDir = serviceDir + moduleName ;
            serviceImplDir = serviceImplDir + moduleName ;
            controllerDir = controllerDir + moduleName ;
        }

        pc.setParent("lf.ssm");
        pc.setEntity(entityDir);
        pc.setMapper(mapperDir);
        pc.setXml(mapperDir);
        pc.setService(serviceDir);
        pc.setServiceImpl(serviceImplDir);
        pc.setController(controllerDir);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
//         String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper.xml";
//            }
//        });


        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel); //表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); //lombok
        strategy.setRestControllerStyle(true);
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setSuperEntityClass("lf.ssm.core.base.BaseModel");
        strategy.setSuperServiceClass("lf.ssm.core.base.IBaseService");
        strategy.setSuperServiceImplClass("lf.ssm.core.impl.BaseServiceImpl");
        strategy.setSuperControllerClass("lf.ssm.core.base.BaseController");
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.entityTableFieldAnnotationEnable(true); //生成字段加上注解
        //strategy.setControllerMappingHyphenStyle(true); // 驼峰转连字符 requestmapping请求生成为下划线
        //忽略生成父类实体属性
        Class<BaseModel> baseModelClass = BaseModel.class;
        Field[] fields = baseModelClass.getDeclaredFields();
        int len = fields.length;
        String[] fieldNames = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList()).toArray(new String[len]);
        strategy.setSuperEntityColumns(ToolUtil.humpToLine(fieldNames));

//        strategy.setTablePrefix(new String[] { "tb_", "tsys_" }); //配置生成时去除的前缀名
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
