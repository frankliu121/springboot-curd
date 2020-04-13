package lf.ssm.exception;

import lombok.Data;

/**
 * @Classname JobException
 * @Date 2020/4/10 19:15
 * @Created by 刘锋
 */
@Data
public class JobException extends RuntimeException{

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 状态码
     */
    private int code;

    public JobException(int code,String msg) {
        this.msg = msg;
        this.code = code;
    }
}
