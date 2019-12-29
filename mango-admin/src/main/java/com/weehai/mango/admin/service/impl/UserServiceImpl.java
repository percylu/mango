package com.weehai.mango.admin.service.impl;

import com.weehai.mango.admin.model.User;
import com.weehai.mango.admin.dao.UserMapper;
import com.weehai.mango.admin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weehai.mango.core.page.MyBatisPageHelper;
import com.weehai.mango.core.page.PageRequest;
import com.weehai.mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MyBatisPageHelper.findPage(pageRequest,UserMapper.class,userMapper);
    }
}
