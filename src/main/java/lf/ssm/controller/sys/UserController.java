package lf.ssm.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lf.ssm.core.base.BaseController;
import lf.ssm.core.base.BaseResult;
import lf.ssm.entity.sys.User;
import lf.ssm.exception.CheckParamException;
import lf.ssm.service.sys.IUserService;
import lf.ssm.util.ToolUtil;
import lf.ssm.util.ValidType;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public BaseResult login(String username,String password) {
        BaseResult result = BaseResult.successResult();
        if (!ToolUtil.isAllNotEmpty(username,password)) {
            return result.error(2, "请输入用户名和密码");
        }
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setTimeout(-1000L); //设置过期时间 负数表示永不过期
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆 todo 1.实现利用缓存限制登录失败尝试次数.2.实现单点登录功能
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return result.error(2,"未知账户");
        } catch (IncorrectCredentialsException ice) {
            return result.error(2,"密码不正确");
        } catch (LockedAccountException lae) {
            return result.error(2,"账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            return result.error(2,"用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            return result.error(2,"用户名或密码不正确！");
        }
        if (subject.isAuthenticated()) {
            return result.success();
        } else {
            token.clear();
            return result.error(2,"登录失败");
        }
    }

    @PostMapping("/logout")
    @ApiOperation("注销")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

}
