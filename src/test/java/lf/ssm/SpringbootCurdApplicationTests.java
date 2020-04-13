package lf.ssm;

import lf.ssm.mapper.test.UserMapper;
import lf.ssm.entity.test.User;
import lf.ssm.service.impl.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCurdApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private test.UserServiceImpl userService;

    @Test
    public void testUpdate(){
        User user = new User();
        user.setSrc("185464");
        user.setId(6L);
        user.setUsername("测试update");
        userMapper.update(user);
    }

    @Test
    public void insert(){
        User user = new User();
        user.setSrc("185464");
        user.setUsername("测试insert");
        userService.insert(user);
        System.out.println(user);
    }



}
