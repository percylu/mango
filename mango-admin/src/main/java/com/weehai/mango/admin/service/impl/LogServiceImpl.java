package com.weehai.mango.admin.service.impl;

import com.weehai.mango.admin.model.Log;
import com.weehai.mango.admin.dao.LogMapper;
import com.weehai.mango.admin.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统操作日志 服务实现类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
