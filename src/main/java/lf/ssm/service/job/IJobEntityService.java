package lf.ssm.service.job;

import lf.ssm.core.base.IBaseService;
import lf.ssm.entity.job.JobEntity;
import lf.ssm.exception.JobException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liufeng
 * @since 2020-04-10
 */
public interface IJobEntityService extends IBaseService<JobEntity> {

    /**
     * 保存任务
     * @param record
     */
    void saveTask(JobEntity record);

    /**
     * 立即执行(触发一次任务)
     * @param record
     * @throws JobException
     */
    void trigger(JobEntity record);

    /**
     * 暂停任务
     * @param record
     */
    void pause(JobEntity record);

    /**
     * 恢复任务
     * @param record
     * @throws JobException
     */
    void resume(JobEntity record);

    /**
     * 移除任务
     * @param jobEntity
     * @throws JobException
     */
    void remove(JobEntity jobEntity);

    /**
     * 更新任务
     * @param record
     * @throws JobException
     */
    void updateTask(JobEntity record);
}
