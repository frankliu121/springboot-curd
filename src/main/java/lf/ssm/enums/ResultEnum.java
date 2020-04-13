package lf.ssm.enums;

/**
 * @Classname ResultEnum
 * @Date 2019/4/20 19:44
 * @Created by 刘锋
 */
public enum ResultEnum {

    SUCCESS(200,"操作成功"),
    ERROR(1,"系统错误"),
    ERR_PARAM(1001,"参数错误"),
    ERR_REQUERY(1009,"请勿重复提交"),

    //用户注册失败
    USER_REGISTER_FAIL(1004, "用户注册失败"),
    SYSTEM_EXCEPTION(1005,"系统繁忙，请稍后再试"),
    SYS_USERLOGIN_OVERTIME(1006, "用户登录超时"),
    REGISTER_SUCCESS(1007,"用户注册成功"),

    //参数校验
    PARAM_ERROR(1008, "参数错误：%s")
    ;

    public int code;
    public String value;

    ResultEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public static String getValue(int code) {
        for (ResultEnum emum : ResultEnum.values()) {
            if (emum.getCode() == code) {
                return emum.getValue();
            }
        }
        return null;
    }
    public static int getCode(String value) {
        for (ResultEnum emum : ResultEnum.values()) {
            if (emum.getValue().equals(value)) {
                return emum.getCode();
            }
        }
        return -1;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
