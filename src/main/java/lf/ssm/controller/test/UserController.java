package lf.ssm.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lf.ssm.core.base.BaseController;
import lf.ssm.core.base.BaseResult;
import lf.ssm.entity.test.User;
import lf.ssm.exception.CheckParamException;
import lf.ssm.service.test.IUserService;
import lf.ssm.util.ValidType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Classname UserController
 * @Date 2019/4/18 15:05
 * @Created by liufeng
 */
@Slf4j
@Api(description = "用户控制器",tags = "BaseController")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService service;


    @ApiOperation(value="获取用户列表",notes = "根据page,limit获取用户列表")
    @GetMapping("/")
    public BaseResult userList(String page,String limit){
        List<User> all = service.findAll();
        return new BaseResult().setData(all);
    }


    @GetMapping("/valid") //封装校验
    public BaseResult testGet(@Validated({ValidType.Update.class}) User user, BindingResult bindingResult){
        BaseResult baseResult=new BaseResult();
        return baseResult;
    }

    @GetMapping("/validArg") //封装校验
    public BaseResult testValidArg(@Valid String username,BindingResult bindingResult) throws CheckParamException {
        BaseResult baseResult=new BaseResult();
        return baseResult;
    }


    @PostMapping("/login")
    public BaseResult login(@Valid User user,BindingResult bindingResult){
        BaseResult baseResult = new BaseResult();
        baseResult.setMessage("登陆成功");
        baseResult.setData(null);
        return baseResult;
    }

    @ApiOperation("添加用户")
    @RequestMapping("/addUser")
    public void addUser(User user){
        User u = new User();
        u.setUsername("测试1");
        u.setAge(19);
        service.insert(u);
    }

    @RequestMapping("/delById")
    public void delById(Long id){
        service.delById(id);
    }

}
