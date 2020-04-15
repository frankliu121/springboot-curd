package lf.ssm.controller.test;

import lf.ssm.core.base.BaseController;
import lf.ssm.core.base.BaseResult;
import lf.ssm.entity.sys.User;
import lf.ssm.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname TestController
 * @Date 2019/4/27 10:05
 * @Created by 刘锋
 */

@Slf4j
@RestController
public class TestController extends BaseController {

    //测试原始的校验
    @GetMapping("/originValid")
    public BaseResult testVaild(User user){
        log.info("originValid");
        BaseResult baseResult = new BaseResult();
        if(user.getUserName()==null) {
            return baseResult.setMessage("username不能为空");
        }
//        else if (user.getBirthday()==null){
//            return baseResult.setMessage("birthday不能为空");}
        else if(user.getAge()<18){
            return baseResult.setMessage("年龄未满18岁");
        } else {  //成功
            log.info("do something ...");
            List<User> userList=new ArrayList<>();
            userList.add(new User().setUserName("测试").setAge(18));

            baseResult.setData(user);
             //..... codes

            return baseResult.setMessage(ResultEnum.SUCCESS.value).setCode(ResultEnum.SUCCESS.code);
        }

    }

    //测试使用hibernate validation的@Valid注解校验
    @GetMapping("/hibernateValid")
    public BaseResult testValid(@Valid User user, BindingResult bindingResult){// 每一个参数都要有一个BindResult对象对校验结果进行封装
        BaseResult baseResult = new BaseResult();

        if (bindingResult.hasErrors()){// 判断参数校验成果
            List<ObjectError> allErrors=bindingResult.getAllErrors(); //获取所有error
            String errorMessage="";
            for (ObjectError objectError:allErrors) { //遍历将error打印
                log.error(objectError.getDefaultMessage());
                errorMessage+=objectError.getDefaultMessage()+","; //拼接错误信息
                baseResult.setMessage(errorMessage);
                return  baseResult;
            }
        }

        // do something ....


        return baseResult;

    }


    @GetMapping("/methodValid")  //对校验判断进行封装
    public BaseResult methodValid(@Valid User user, BindingResult bindingResult){
        BaseResult baseResult = new BaseResult();
        if(!validate(bindingResult)){  //校验
            String erroMessage = bindingResult.getFieldError().getDefaultMessage();
            return baseResult.setMessage(erroMessage);
        }

        // codes ...

        return baseResult.setMessage(ResultEnum.SUCCESS.value).setCode(ResultEnum.SUCCESS.code);
    }





}
