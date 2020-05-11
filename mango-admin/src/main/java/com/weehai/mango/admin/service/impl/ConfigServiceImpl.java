package com.weehai.mango.admin.service.impl;

import com.weehai.mango.admin.model.Config;
import com.weehai.mango.admin.dao.ConfigMapper;
import com.weehai.mango.admin.service.ConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

}
