package lf.ssm.exception;

import com.sun.tools.javac.jvm.Code;
import lf.ssm.enums.ResultEnum;
import lombok.Data;
import lombok.Getter;
import org.springframework.validation.BindingResult;

/**
 * @Classname CheckParamException
 * @Date 2020/4/10 8:25
 * @Created by liufeng
 */

public class CheckParamException extends Exception{

    @Getter
    private String msg;

    @Getter
    private int code;

    @Getter
    private BindingResult bindingResult;

    public CheckParamException(ResultEnum resultEnum,BindingResult bindingResult) {
        this.msg = resultEnum.value;
        this.code = resultEnum.code;
        this.bindingResult = bindingResult;
    }

}

