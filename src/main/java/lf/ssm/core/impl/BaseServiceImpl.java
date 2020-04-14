package lf.ssm.core.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lf.ssm.core.base.BaseModel;
import lf.ssm.core.base.IBaseMapper;
import lf.ssm.core.base.IBaseService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Classname BaseServiceImpl
 * @Date 2020/4/8 9:58
 * @Created by liufeng
 */
public abstract class BaseServiceImpl<T extends BaseModel,M extends BaseMapper<T>> extends ServiceImpl<M,T> implements IBaseService<T> {

    @Autowired
    protected M dao;

    public BaseServiceImpl() {
        super();
    }

    @Override
    public List<T> selectList() {
        return dao.selectList(new QueryWrapper<T>());
    }

    @Override
    public IPage<T> selectPage(long current, long size,@Param("ew") Wrapper<T> wrapper){
        return super.selectPage(new Page<T>(current,size),wrapper);
    };
}
