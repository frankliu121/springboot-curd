package lf.ssm.service.impl.job;

import lf.ssm.core.impl.BaseServiceImpl;
import lf.ssm.entity.job.JobEntity;
import lf.ssm.exception.JobException;
import lf.ssm.mapper.job.JobEntityMapper;
import lf.ssm.service.job.IJobEntityService;
import lf.ssm.util.BeanUtil;
import lf.ssm.util.Const;
import lf.ssm.util.JobUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-04-10
 */
@Service
@Slf4j
public class JobEntityServiceImpl extends BaseServiceImpl<JobEntity,JobEntityMapper> implements IJobEntityService {

    @Autowired
    private Scheduler scheduler;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void saveTask(@RequestBody JobEntity record) {
        try {
            buildJob(record);
            insert(record);
        } catch (Exception e) {
            throw new JobException(201,"新增任务失败");
        }
    }

    /**
     * 立即执行
     * quartz是通过临时生成一个trigger的方式来实现的，这个trigger将在本次任务运行完成之后自动删除。
     * @param record
     * @throws JobException
     */
    @Override
    public void trigger(JobEntity record) {
        try {
            scheduler.triggerJob(JobUtil.genJobKey(record));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause(JobEntity record)  {
        try {
            record.setStatus(Const.DEFAULT_NO_STR);
            updateById(record);
            scheduler.pauseJob(JobUtil.genJobKey(record));
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new JobException(666,"暂停任务失败");
        }
    }

    @Override
    public void resume(JobEntity record) {
        try {
            record.setStatus(Const.DEFAULT_YES_STR);
            updateById(record);
            scheduler.resumeJob(JobUtil.genJobKey(record));
        } catch (Exception e) {
            e.printStackTrace();
            throw new JobException(999,"恢复任务失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void remove(JobEntity jobEntity){
        try {
            deleteById(jobEntity.getId());
            deleteJob(jobEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JobException(520,"移除任务失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = JobException.class)
    public void updateTask(JobEntity record) throws JobException {
        // 1.更新自定义任务实体数据 2.删除原任务相关数据 3.重新构建任务
        try {
            deleteJob(selectById(record.getId()));
            buildJob(record);
            updateById(record);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JobException(886,"更新任务失败");
        }
    }

    /**
     * 构建定时任务
     * @param record
     * @throws ClassNotFoundException
     * @throws SchedulerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void buildJob(JobEntity record) throws ClassNotFoundException, SchedulerException, IllegalAccessException, InstantiationException {
        Class cls = Class.forName(record.getJobClassName());
        cls.newInstance();
        // 构建jobdetail丶 触发器丶调度器
        JobDetail job = JobBuilder.newJob(cls).withIdentity(JobUtil.genJobKey(record))
                .withDescription(record.getRemark()).build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(record.getCronExpression());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(JobUtil.genTriggerKey(record)) .startNow()
                .withSchedule(cronScheduleBuilder).build();
        // 传递的参数
        job.getJobDataMap().put("jobEntity", BeanUtil.beanToMap(record));
        // 调度任务
        if(!scheduler.isShutdown()) {
            scheduler.scheduleJob(job, trigger);
        }
        // 启动调度器
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
        //未启用
        if (Const.DEFAULT_NO_STR.equals(record.getStatus())){
            scheduler.pauseJob(JobUtil.genJobKey(record));
        }
        if (Const.DEFAULT_YES_STR.equals(record.getIsNowRun())) { //是否立即执行一次
            trigger(record);
        }
    }

    /**
     * 移除任务
     * @param record
     * @throws SchedulerException
     */
    private void deleteJob(JobEntity record) throws SchedulerException {
        //移除任务
        TriggerKey triggerKey = JobUtil.genTriggerKey(record);
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(JobUtil.genJobKey(record));
    }
}
