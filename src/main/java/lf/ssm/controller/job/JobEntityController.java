package lf.ssm.controller.job;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lf.ssm.core.base.BaseResult;
import lf.ssm.entity.job.JobEntity;
import lf.ssm.service.job.IJobEntityService;
import lf.ssm.util.JobUtil;
import lf.ssm.util.ValidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lf.ssm.core.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

/**
 * <p>
 *  任务实体控制器
 * </p>
 *
 * @author liufeng
 * @since 2020-04-10
 */
@Api(description = "定时任务控制器",tags = "JobEntityController")
@RestController
@RequestMapping("/jobEntity")
public class JobEntityController extends BaseController {

    @Autowired
    private IJobEntityService service;

    /**
     * 跳转页面
     * @param page
     * @return
     */
    @GetMapping("/page/{page}")
    @ApiOperation("跳转页面")
    public ModelAndView toPage(@PathVariable("page") String page){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vue/"+page);
        return modelAndView;
    }

    /**
     * 查询所有任务
     * @author liufeng
     * @date 2020/4/11 13:24
     * @return java.util.List<lf.ssm.entity.job.JobEntity>
     */
    @GetMapping("/list")
    @ApiOperation("查询所有任务")
    public IPage<JobEntity> list( long pageNum, long pageSize){
        IPage<JobEntity> page = service.selectPage(pageNum, pageSize, new QueryWrapper<JobEntity>().orderByDesc("status","create_time"));
        //处理corn表达式显示
        if (!CollectionUtils.isEmpty(page.getRecords())){
            page.getRecords().forEach(r->{
                r.setCronExpressionVal(JobUtil.translateToChinese(r.getCronExpression()));
            });
        }
        return page;
    }

    /**
     * 添加定时任务
     * @author liufeng
     * @date 2020/4/11 13:24
     * @return lf.ssm.entity.base.BaseResult
     */
    @PostMapping("/addJob")
    @ApiOperation("添加定时任务")
    public BaseResult addJob(@RequestBody @Validated({ValidType.Add.class}) JobEntity job, BindingResult bindingResult){
        BaseResult result = BaseResult.successResult();
        job.setTriggerName(JobUtil.TRIGGER_PREFIX+ job.getName());
        job.setJobGroup(JobUtil.DEFAULT_GROUP);
        job.setTriggerGroup(JobUtil.DEFAULT_GROUP);
        service.saveTask(job);
        return result;
    }

    /**
     * 更新定时任务
     * @author liufeng
     * @date 2020/4/11 13:24
     * @return lf.ssm.entity.base.BaseResult
     */
    @PostMapping("/updateJob")
    @ApiOperation("更新定时任务")
    public BaseResult updateJob(@RequestBody @Valid JobEntity job){
        BaseResult result = BaseResult.successResult();
        service.updateTask((job));
        return result;
    }

    /**
     * 移除定时任务
     * @author liufeng
     * @date 2020/4/11 13:24
     * @return lf.ssm.entity.base.BaseResult
     */
    @PostMapping("/removeJob/{id}")
    @ApiOperation("移除定时任务")
    public BaseResult removeJob(@PathVariable("id") Long id){
        BaseResult result = BaseResult.successResult();
        if (id == null || id == 0){
            return result.error(2,"id不能为空");
        }
        JobEntity jobEntity = service.selectById(id);
        if (jobEntity == null){
            return result.error(3,"未查询到实体");
        }
        service.remove(jobEntity);
        return result;
    }

    /**
     * 暂停定时任务
     * @author liufeng
     * @date 2020/4/11 13:24
     * @return lf.ssm.entity.base.BaseResult
     */
    @PostMapping("/pauseJob/{id}")
    @ApiOperation("暂停定时任务")
    public BaseResult pauseJob(@PathVariable("id") Long id){
        BaseResult result = BaseResult.successResult();
        if (id == null || id == 0){
            return result.error(2,"id不能为空");
        }
        JobEntity jobEntity = service.selectById(id);
        if (jobEntity == null){
            return result.error(3,"未查询到实体");
        }
        service.pause(jobEntity);
        return result;
    }

    /**
     * 恢复定时任务
     * @author liufeng
     * @date 2020/4/11 13:24
     * @return lf.ssm.entity.base.BaseResult
     */
    @PostMapping("/resumeJob/{id}")
    @ApiOperation("恢复定时任务")
    public BaseResult resumeJob(@PathVariable("id") Long id){
        BaseResult result = BaseResult.successResult();
        if (id == null || id == 0){
            return result.error(2,"id不能为空");
        }
        JobEntity jobEntity = service.selectById(id);
        if (jobEntity == null){
            return result.error(3,"未查询到实体");
        }
        service.resume(jobEntity);
        return result;
    }

    /**
     * 触发一次任务
     * @author liufeng
     * @date 2020/4/11 13:24
     * @return lf.ssm.entity.base.BaseResult
     */
    @PostMapping("/triggerJob/{id}")
    @ApiOperation("触发一次任务")
    public BaseResult triggerJob(@PathVariable("id") Long id){
        BaseResult result = BaseResult.successResult();
        if (id == null || id == 0){
            return result.error(2,"id不能为空");
        }
        JobEntity jobEntity = service.selectById(id);
        if (jobEntity == null){
            return result.error(3,"未查询到实体");
        }
        service.trigger(jobEntity);
        return result;
    }
}
