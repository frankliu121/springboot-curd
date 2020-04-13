package lf.ssm.core.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname BaseService
 * @Date 2020/4/8 9:58
 * @Created by 刘锋
 */
public interface IBaseService<T extends BaseModel> extends IService<T>{
    /**
     * 查询所有
     * @return
     */
    List<T> selectList();

    /**
     * 分页查询
     * @param current
     * @param size
     * @param wrapper
     * @return
     */
    IPage<T> selectPage(long current, long size, @Param("ew") Wrapper<T> wrapper);
}
