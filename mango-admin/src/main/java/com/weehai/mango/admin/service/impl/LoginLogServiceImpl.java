package com.weehai.mango.admin.service.impl;

import com.weehai.mango.admin.model.LoginLog;
import com.weehai.mango.admin.dao.LoginLogMapper;
import com.weehai.mango.admin.service.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统登录日志 服务实现类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
