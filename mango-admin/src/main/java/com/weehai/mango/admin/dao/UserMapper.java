package com.weehai.mango.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weehai.mango.admin.model.User;

import java.util.List;

/**
 * <p>
 * 用户管理 Mapper 接口
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
public interface UserMapper extends BaseMapper<User>{
    /**
     * 查找所有用户
     * @return
     */
    List<User>findAll();


}
