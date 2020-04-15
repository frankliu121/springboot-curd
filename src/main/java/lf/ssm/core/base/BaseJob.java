package lf.ssm.core.base;

import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import lf.ssm.entity.job.JobEntity;
import lf.ssm.exception.JobException;
import lf.ssm.util.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @Classname BaseJob
 * @Date 2020/4/11 21:14
 * @Created by liufeng
 */
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class BaseJob implements Job,Serializable {

    /**
     * 利用反射执行指定的方法
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap JobDataMap = context.getMergedJobDataMap();
        Map jobMap = (Map<String,Object>)JobDataMap.get("jobEntity");
        if (CollectionUtils.isEmpty(jobMap)){
            log.error("获取jobEntity失败");
            return;
        }
        JobEntity jobEntity = null;
        Class<?> clazz =null;
        try {
            jobEntity = BeanUtil.mapToBean(jobMap, JobEntity.class);
            clazz = Class.forName(jobEntity.getJobClassName());
            String className = StringUtils.toLowerCaseFirstOne(clazz.getSimpleName());
            Object bean = (Object) SpringUtil.getBean(className);
            Method method = ReflectionUtils.findMethod(bean.getClass(), jobEntity.getMethodName());
            log.info("=======开始执行定时任务:"+jobEntity.getJobClassName()+"_"+jobEntity.getMethodName()+"=======");
            long start = System.currentTimeMillis();
            ReflectionUtils.invokeMethod(method, bean);
            long end = System.currentTimeMillis();
            log.info("=======定时任务执行完毕,耗时:"+(end-start)+"毫秒,任务执行时间:"+DateUtil.dateToStr(context.getFireTime())+",下次执行时间:"+ DateUtil.dateToStr(context.getNextFireTime()) +"=======");
        }  catch (Exception e) {
            log.error("定时任务执行失败");
            e.printStackTrace();
        }
    }
}
