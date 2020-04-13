package lf.ssm.jobTest;

import lf.ssm.entity.job.JobEntity;
import lf.ssm.exception.JobException;
import lf.ssm.service.impl.job.JobEntityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname JobTest
 * @Date 2020/4/10 21:09
 * @Created by liufeng
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobTest {

    @Autowired
    private JobEntityServiceImpl jobEntityService;

    /**
     * 测试添加任务
     * @throws JobException
     */
    @Test
    public void testAddJob() throws JobException {
        JobEntity jobEntity = new JobEntity();
        //两秒执行一次表达式: "0/2 * * * * ? "
        //没十秒执行一次: "0/10 * * * * ? "
        //两分钟执行一次表达式: "0/2 0/2 * * * ? "
        //十分钟分钟执行一次表达式: "0/2 0/10 * * * ? "
        //没一小时执行一次表达式:"0 0 0/1 * * ? "
        jobEntity.setId(1248628306757062658L).
                setName("第一个任务").
                setJobGroup("firstGroup").
                setCronExpression("0/2 0/10 0/1 * * ? ").
                setDescription("description").
                setJobClassName("lf.ssm.job.UserJob").
                setIsNowRun("1");
        jobEntityService.saveTask(jobEntity);
    }



    /**
     * 测试更新
     */
    @Test
    public void testUpdate() throws JobException{
        JobEntity job = jobEntityService.selectById(1248628306757062658L);
        job.setCronExpression("0/10 * * * * ? ");
        jobEntityService.updateTask(job);
    }

    /**
     * 测试暂停
     * @throws JobException
     */
    @Test
    public void testPause() throws JobException {
        JobEntity jobEntity = jobEntityService.selectById(1248628306757062658L);
        System.out.println(jobEntity);
        jobEntityService.pause(jobEntity);
    }

    @Test
    public void testResume() throws JobException {
        JobEntity jobEntity = jobEntityService.selectById(1248628306757062658L);  //会把之前
        System.out.println(jobEntity);
        jobEntityService.resume(jobEntity);
    }

    /**
     * 测试立即执行一次任务
     * @throws JobException
     */
    @Test
    public void testTriggerJob() throws JobException {
        JobEntity jobEntity = jobEntityService.selectById(1248628306757062658L);
        System.out.println(jobEntity);
        jobEntityService.trigger(jobEntity); //触发任务
    }


}
