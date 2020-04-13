package lf.ssm.service.test;

import lf.ssm.core.base.IBaseService;
import lf.ssm.entity.test.User;

import java.util.List;

/**
 * @Classname UserService
 * @Date 2019/4/18 20:03
 * @Created by 刘锋
 */
public interface IUserService extends IBaseService<User> {
    List<User> findAll();

    User findById(Long id);

    int delById(Long id);

    int add(User user);

}
