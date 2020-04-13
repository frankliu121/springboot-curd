package lf.ssm.common.advice;

import lf.ssm.core.base.BaseResult;
import lf.ssm.exception.CheckParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 校验异常处理通知,异常统一处理
 * @Classname ExceptionControllerAdvice
 * @Date 2020/4/13 10:10
 * @Created by liufeng
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(CheckParamException.class)
    public BaseResult bindException(CheckParamException e){
        BaseResult baseResult = new BaseResult();
        BindingResult bindingResult = e.getBindingResult();
        log.warn("CheckParamException:>>>");

        if(bindingResult!=null && bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            baseResult.setCode(e.getCode());
            String message="参数错误：%s";

            for (ObjectError err:allErrors){
                message = String.format(message, err.getDefaultMessage());
               // log.warn(err.getDefaultMessage());
                break;
            }

            baseResult.setMessage(message);
        }else{
            baseResult.setCode(e.getCode());
            baseResult.setMessage(e.getMessage());
        }
        return baseResult;
    }
}
