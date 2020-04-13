package lf.ssm.mybatisPlus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lf.ssm.entity.job.JobEntity;
import lf.ssm.mapper.job.JobEntityMapper;
import lf.ssm.mapper.test.UserMapper;
import lf.ssm.entity.test.User;
import lf.ssm.service.impl.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class mybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private test.UserServiceImpl userService;

    @Autowired
    private JobEntityMapper jobEntitydao;

    @Test
    public void testUpdate(){
        User user = new User();
        user.setSrc("185464");
        user.setId(10L);
        user.setUsername("测试updateaaa");
//        userMapper.update(user);
    }
    @Test
    public void testSelect(){
        User user = userMapper.selectById(10L);
        System.out.println(user);
        JobEntity jobEntity = jobEntitydao.selectById(1L);
        System.out.println(jobEntity);
    }


    @Test
    public void insert(){
        User user = new User();
        user.setSrc("185464");
        user.setUsername("测试insert");
        userMapper.insert(user);
        System.out.println(user);
    }

    @Test
    // 測試plus的insert方法
    public void plusInsert(){
        User user = new User();
        user.setUsername("测试时间");
        userMapper.insert(user);
        System.out.println(user.getId());// 插入后自动回填id
    }

    @Test
    public void testQuery(){
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        List<User> users = userMapper.selectList(objectQueryWrapper);

        System.out.println(users);
    }


    @Test
    public void testDelete(){
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        userMapper.deleteById(10L);
    }

    @Test
    public  void testPage(){
        QueryWrapper<Object> objectQueryWrapper = new QueryWrapper<>();

        IPage<User> userIPage = userMapper.selectPage(new Page<>(2, 2), null);//page参数 当前页码,每页显示条数
        System.out.println(userIPage);
    }

    @Test
    public void testBase(){
//        Class aClass = userService.testClass();
//        System.out.println(aClass);
        List<User> all = userService.findAll();
        System.out.println(all);
    }

}
