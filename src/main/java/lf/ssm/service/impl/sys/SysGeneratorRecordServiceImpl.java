package lf.ssm.service.impl.sys;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lf.ssm.config.MybatisGeneratorConfig;
import lf.ssm.core.base.BaseModel;
import lf.ssm.core.base.BaseResult;
import lf.ssm.entity.sys.SysGeneratorRecord;
import lf.ssm.mapper.sys.SysGeneratorRecordMapper;
import lf.ssm.service.sys.ISysGeneratorRecordService;
import lf.ssm.core.impl.BaseServiceImpl;
import lf.ssm.util.Const;
import lf.ssm.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-04-14
 */
@Service
public class SysGeneratorRecordServiceImpl extends BaseServiceImpl<SysGeneratorRecord,SysGeneratorRecordMapper> implements ISysGeneratorRecordService {

    @Autowired
    private MybatisGeneratorConfig generatorConfig;

    @Override
    public BaseResult generatorCode(SysGeneratorRecord record) {
        AutoGenerator mpg = new AutoGenerator();
        BaseResult result = BaseResult.successResult();
        try {
            // 全局配置
            setGlobalConfig(mpg,record);
            // 数据源配置
            setDataSourceConfig(mpg);
            // 包配置 (设置包名)
            setPackageConfig(mpg,record);
            // 设置自定义配置
            setCustomConfig(mpg);
            // 策略配置
            setStrategyConfig(mpg,record);
            //执行
            mpg.execute();

            //插入记录
            dao.insert(record);
        } catch (Exception e) {
            result.error(2,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    private void setStrategyConfig(AutoGenerator mpg,SysGeneratorRecord record) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel); //表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); //lombok
        strategy.setRestControllerStyle(true);
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setSuperEntityClass(generatorConfig.getSuperEntityClass());
        strategy.setSuperServiceClass(generatorConfig.getSuperServiceClass());
        strategy.setSuperServiceImplClass(generatorConfig.getSuperServiceImplClass());
        strategy.setSuperControllerClass(generatorConfig.getSuperControllerClass());
        strategy.setInclude(record.getTableName().split(",")); //  "表名，多个英文逗号分割"
        strategy.entityTableFieldAnnotationEnable(true); //生成字段加上注解
        //排出生成父类实体
        Class<BaseModel> baseModelClass = BaseModel.class;
        Field[] fields = baseModelClass.getDeclaredFields();
        String[] fieldNames = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList()).toArray(new String[fields.length]);
        strategy.setSuperEntityColumns(ToolUtil.humpToLine(fieldNames));
        //strategy.setTablePrefix(new String[] { "tb_", "tsys_" }); //配置生成时去除的前缀名
        //strategy.setControllerMappingHyphenStyle(true); // 驼峰转连字符 requestmapping请求生成为下划线
        //忽略生成父类实体属性
        mpg.setStrategy(strategy);
    }

    private void setCustomConfig(AutoGenerator mpg) {
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
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setCfg(cfg);
    }

    private void setPackageConfig(AutoGenerator mpg ,SysGeneratorRecord record) {
        PackageConfig pc = new PackageConfig();
        String moduleName = record.getModuleName();
        String entityDir = generatorConfig.getEntityDirectory();
        String mapperDir = generatorConfig.getMapperDirectory();
        String serviceDir = generatorConfig.getServiceDirectory();
        String serviceImplDir = generatorConfig.getServiceImplDirectory();
        String controllerDir = generatorConfig.getControllerDirectory();
        if (StringUtils.isNotBlank(moduleName)){
            moduleName = Const.DOT + moduleName;
            entityDir = entityDir + moduleName;
            mapperDir = mapperDir + moduleName;
            serviceDir = serviceDir + moduleName ;
            serviceImplDir = serviceImplDir + moduleName ;
            controllerDir = controllerDir + moduleName ;
        }

        pc.setParent(generatorConfig.getParentPackageName());
        pc.setEntity(entityDir);
        pc.setMapper(mapperDir);
        pc.setXml(mapperDir);
        pc.setService(serviceDir);
        pc.setServiceImpl(serviceImplDir);
        pc.setController(controllerDir);
        mpg.setPackageInfo(pc);
    }

    private void setDataSourceConfig(AutoGenerator mpg) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(generatorConfig.getDbUrl());
        dsc.setDriverName(generatorConfig.getDbDriverName());
        dsc.setUsername(generatorConfig.getDbUsername());
        dsc.setPassword(generatorConfig.getDbPassword());
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
    }

    private void setGlobalConfig(AutoGenerator mpg,SysGeneratorRecord record){
        GlobalConfig gc = new GlobalConfig();
        String projectPath = record.getTargetDirectory();  //配置生成目录
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(record.getCreateName());
        gc.setDateType(DateType.TIME_PACK);
        gc.setOpen(true);//是否打开目录
        gc.setIdType(IdType.ID_WORKER);// id生成策略
        gc.setFileOverride(true); //是否覆盖
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML 生成ResultMap
        gc.setBaseColumnList(true);// XML 生成columList
        mpg.setGlobalConfig(gc);
    }

}
