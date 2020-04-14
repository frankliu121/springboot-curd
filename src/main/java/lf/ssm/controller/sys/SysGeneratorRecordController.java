package lf.ssm.controller.sys;


import io.swagger.annotations.ApiOperation;
import lf.ssm.core.base.BaseResult;
import lf.ssm.entity.job.JobEntity;
import lf.ssm.entity.sys.SysGeneratorRecord;
import lf.ssm.service.sys.ISysGeneratorRecordService;
import lf.ssm.util.JobUtil;
import lf.ssm.util.ValidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import lf.ssm.core.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liufeng
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/sysGeneratorRecord")
public class SysGeneratorRecordController extends BaseController {

    @Autowired
    private ISysGeneratorRecordService service;

    @PostMapping("/generate")
    @ApiOperation("生成代码")
    public BaseResult generate(@RequestBody @Validated({ValidType.Add.class}) SysGeneratorRecord record, BindingResult bindingResult){
        record.setIp(getIpAddr(request));
        return service.generatorCode(record);
    }
}
