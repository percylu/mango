package com.weehai.mango.admin.dao;

import com.weehai.mango.admin.model.Role;
import com.weehai.mango.admin.model.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 角色管理 Mapper 接口
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
public interface RoleMapper extends BaseMapper<Role> {
	/**
     *  根据ID查找用户角色
     * @return
     */
    Role findRoleByUserId(@Param(value="userId") Long userId);
    Role findRoleById(@Param(value="Id") Long Id);

    List<Role> findAll(String name,int page,int limit);
}
