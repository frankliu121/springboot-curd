package lf.ssm.service.sys;

import lf.ssm.core.base.IBaseService;
import lf.ssm.entity.sys.User;

import java.util.List;

/**
 * @Classname UserService
 * @Date 2019/4/18 20:03
 * @Created by liufeng
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User selectByUsername(String username);
}
