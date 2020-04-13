package lf.ssm.core.base;

import lf.ssm.enums.ResultEnum;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * Web接口统一返回结果
 * @author
 * @since
 */

@Data
@ToString
@Accessors(chain = true)
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ERROR_MSG = "操作失败";

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息信息
     */
    private String message;

    private Object data;

    private Map<String,Object> row;

    public BaseResult() {
    }

    public BaseResult(int code, String message) {
        this(code, message, null);
    }

    public BaseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 获取成功的对象
     * @return
     */
    public static BaseResult successResult(){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(ResultEnum.SUCCESS.getCode());
        baseResult.setMessage(ResultEnum.SUCCESS.getValue());
        return baseResult;
    }

    /**
     * 设置为操作成功
     * @return
     */
    public BaseResult success(){
        this.setCode(ResultEnum.SUCCESS.getCode());
        this.setMessage(ResultEnum.SUCCESS.getValue());
        return this;
    }

    /**
     * 设置为失败
     * @return
     */
    public BaseResult error(){
        this.setCode(ResultEnum.ERR_PARAM.getCode());
        this.setMessage(ResultEnum.ERR_PARAM.getValue());
        return this;
    }

    /**
     * 设置为失败
     * @param code
     * @param msg
     * @return
     */
    public BaseResult error(int code, String msg){
        this.setCode(ResultEnum.ERR_PARAM.getCode());
        this.setMessage(ResultEnum.ERR_PARAM.getValue());
        return this;
    }

    /**
     * 是否成功
     * @return
     */
    public boolean isSuccess(){
      return this.code == 200? true:false;
    }
}

