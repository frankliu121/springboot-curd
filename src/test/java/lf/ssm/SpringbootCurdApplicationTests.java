package lf.ssm;

import lf.ssm.mapper.sys.UserMapper;
import lf.ssm.entity.sys.User;
import lf.ssm.service.impl.sys.UserServiceImpl;
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
    private UserServiceImpl userService;

    @Test
    public void testUpdate(){
        User user = new User();
//        user.setSrc("185464");
        user.setId(6L);
//        user.setUsername("测试update");
        userMapper.update(user);
    }

    @Test
    public void insert(){
        User user = new User();
//        user.setSrc("185464");
//        user.setUsername("测试insert");
        userService.insert(user);
        System.out.println(user);
    }



}
