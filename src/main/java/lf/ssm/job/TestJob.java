package lf.ssm.job;

import lf.ssm.core.base.BaseJob;
import lf.ssm.entity.sys.User;
import lf.ssm.service.sys.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Test类定时任务
 * @Classname TestJob
 * @Date 2020/4/11 21:34
 * @Created by liufeng
 */
@DisallowConcurrentExecution
@Component
@Slf4j
public class TestJob extends BaseJob {

    @Autowired
    private IUserService userService;

    /**
     * 打印我爱你
     */
    public void printLoveYou(){
        log.info("======================printLoveYou定时任务开始执行===================");
        User user = userService.selectList().get(0);
        System.out.println("我爱你"+user.getUserName());
        iLoveYou();
        log.info("======================printLoveYou定时任务执行结束===================");
    }

    /**
     * 打印你最棒
     */
    public void printYouAreBest(){
        log.info("======================printLoveYou定时任务开始执行===================");
        List<User> users = userService.selectList();
        users.forEach(u->{
            System.out.println("亲爱的"+u.getUserName()+"你最棒");
        });
        log.info("======================printLoveYou定时任务执行结束===================");

    }

    /**
     * 打印心形
     */
    private void iLoveYou(){
        for(float y = (float) 1.5;y>-1.5;y -=0.1)  {
            for(float x= (float) -1.5;x<1.5;x+= 0.05){
                float a = x*x+y*y-1;
                if((a*a*a-x*x*y*y*y)<=0.0)  {
                    System.out.print("^");
                }
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}
