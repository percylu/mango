package com.weehai.mango.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weehai.mango.admin.model.User;
import com.weehai.mango.core.page.PageRequest;
import com.weehai.mango.core.page.PageResult;
import com.weehai.mango.core.service.CurdService;

import java.util.List;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
public interface UserService extends IService<User>, CurdService<User> {
    /**
     * 查找所有用户
     * @retrun
     */
    List<User> findAll();

}
