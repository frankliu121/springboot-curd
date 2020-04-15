package lf.ssm.exception;

/**
 * @Classname PermissionsException
 * @Date 2020/4/15 14:47
 * @Created by liufeng
 */
public class PermissionsException extends RuntimeException{
    /**
     * 错误信息
     */
    private String msg;

    /**
     * 状态码
     */
    private int code;

    public PermissionsException(int code,String msg) {
        this.msg = msg;
        this.code = code;
    }
}
