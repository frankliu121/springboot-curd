package lf.ssm.mapper.test;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lf.ssm.entity.test.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname UserMapper
 * @Date 2019/4/18 20:10
 * @Created by liufeng
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     *更新
     * @author liufeng
     * @date 2019/4/19 11:48
     * @return int
     */
    int update(User u); //update需要动态的判断进行修改 写起来麻烦就不用注解了(一对一,一对多同理),直接使用xml里的

    /**
     *查询所有
     * @author liufeng
     * @date 2019/4/19 11:49
     * @return java.util.List<lf.ssm.vo.User>
     */
    @Select("select * from user")   //如果字段与数据库一一对应的话,可以不用ResultMap会做自动映射
    @ResultMap("BaseResultMap")    //可以引用xml里定义好的resultMap ,用id引用
    public List<User> findAll();

    /**
     *根据id查询
     * @author liufeng
     * @date 2019/4/19 11:49
     * @return lf.ssm.vo.User
     */
    @Select("select * from user where id=#{0}")
    @ResultMap("BaseResultMap")
    public User selById(Long id);

    /**
     *查询所有数量
     * @author liufeng
     * @date 2019/4/19 11:50
     * @return java.lang.Integer
     */
    @Select("select count(*) from user")
    public Integer totalCount();

    /**
     *分页
     * @author liufeng
     * @date 2019/4/19 11:50
     * @return java.util.List<lf.ssm.vo.User>
     */
    @Select("select * from user limit #{start},#{pageSize}")  //多参数不支持下标取值(遇到的坑),所以对于参数建议使用@Param注解
    @ResultMap("BaseResultMap")
    List<User> limit(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     *根据id删除
     * @author liufeng
     * @date 2019/4/19 11:50
     * @return int
     */
    @Delete("delete from user where id=#{0}")
    int delById(Long id);

}
