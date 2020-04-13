package lf.ssm.service.impl;

import lf.ssm.core.impl.BaseServiceImpl;
import lf.ssm.entity.test.User;
import lf.ssm.mapper.test.UserMapper;
import lf.ssm.service.test.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname test
 * @Date 2020/4/12 22:09
 * @Created by liufeng
 */
public class test {
    /**
     * @Classname UserServiceImpl
     * @Date 2019/4/18 20:03
     * @Created by 刘锋
     */
    @Slf4j
    @Service
    @Transactional
    public static class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements IUserService {

        @Override
        public List<User> findAll() {
            return dao.findAll();
        }

        @Override
        public User findById(Long id) {
            return dao.selById(id);
        }

        @Override
        public int delById(Long id) {
            return dao.delById(id);
        }

        @Override
        public int add(User user) {
            return 0;
        }

        public Class testClass(){//测试事务用
            return this.getClass();
        }

    }
}
