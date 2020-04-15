package lf.ssm.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lf.ssm.core.impl.BaseServiceImpl;
import lf.ssm.entity.sys.User;
import lf.ssm.mapper.sys.UserMapper;
import lf.ssm.service.sys.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname UserServiceImpl
 * @Date 2019/4/18 20:03
 * @Created by 刘锋
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements IUserService {

    @Override
    public User selectByUsername(String username) {
        return dao.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserName,username));
    }
}
