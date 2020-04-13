package lf.ssm.common.aop;

import lf.ssm.enums.ResultEnum;
import lf.ssm.exception.CheckParamException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**统一进行参数校验
 * @Classname CheckParamAop
 * @Date 2020/4/13 8:05
 * @Created by 刘锋
 */

@Slf4j
@Aspect
@Component
public class CheckParamAop {
    //execution(* lf.ssm.controller.*.*(..)):controller包下所有控制器的方法
    //execution(* lf.ssm.controller..*.*(..)):controller包及其子包下controller的所有方法
    //execution(* lf.ssm.controller.UserController.*(..)): UserController的所有方法
    private final String expCheckPoint="execution(* lf.ssm.controller..*.*(..))";

    @Pointcut(expCheckPoint) //配置切点
    public void expCheckPoint(){}

    /**
     *配置前置通知并织入
     * @author liufeng
     * @date 2020/4/13 8:05
     * @return void
     */
    @Before("expCheckPoint()")
    public void doBefore(JoinPoint joinPoint) throws CheckParamException {
        log.info("这是前置通知参数校验");
        BindingResult bindingResult=null;

        for (Object arg:joinPoint.getArgs()){
            if(arg instanceof BindingResult){   //取出校验结果
                bindingResult= (BindingResult) arg;
            }
        }
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new CheckParamException(ResultEnum.PARAM_ERROR,bindingResult);
            //log.info("参数校验错误");
        }
    }

}
