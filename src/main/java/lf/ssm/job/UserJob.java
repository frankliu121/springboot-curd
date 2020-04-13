package lf.ssm.job;

import lf.ssm.core.base.BaseJob;
import lf.ssm.mapper.job.JobEntityMapper;
import lf.ssm.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
* Created by liufeng on 2020/4/10 18:04
 * :@DisallowConcurrentExecution : 此标记用在实现Job的类上面,意思是不允许并发执行.
 * :注意org.quartz.threadPool.threadCount线程池中线程的数量至少要多个,否则@DisallowConcurrentExecution不生效
 * :假如Job的设置时间间隔为3秒,但Job执行时间是5秒,设置@DisallowConcurrentExecution以后程序会等任务执行完毕以后再去执行,否则会在3秒时再启用新的线程执行
 */
@DisallowConcurrentExecution
@Component
@Slf4j
public class UserJob extends BaseJob {


    public void doSomeThing(){
        log.info("======================doSomeThing定时任务开始执行===================");
        print();
        log.info("======================doSomeThing定时任务结束===================");
    }


    private void print(){
        for(int i =1;i<5;i++){
            for(int j=0;j<5-i;j++){
                System.out.print(" ");
            }
            for(int j=0;j<i*2-1;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
