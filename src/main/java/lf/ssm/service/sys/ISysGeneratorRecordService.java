package lf.ssm.service.sys;

import lf.ssm.core.base.BaseResult;
import lf.ssm.entity.sys.SysGeneratorRecord;
import lf.ssm.core.base.IBaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liufeng
 * @since 2020-04-14
 */
public interface ISysGeneratorRecordService extends IBaseService<SysGeneratorRecord> {

    BaseResult generatorCode(SysGeneratorRecord record);
}
