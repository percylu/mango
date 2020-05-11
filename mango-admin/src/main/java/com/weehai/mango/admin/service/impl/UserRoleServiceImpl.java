package com.weehai.mango.admin.service.impl;

import com.weehai.mango.admin.model.UserRole;
import com.weehai.mango.admin.dao.UserRoleMapper;
import com.weehai.mango.admin.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
	@Autowired
	UserRoleMapper userRoleMapper;
	@Override
	public int deleteByRoleId(Long id) {
		// TODO Auto-generated method stub
		QueryWrapper<UserRole> wrapper=new QueryWrapper<>();
		wrapper.eq("role_id",id);
		return userRoleMapper.delete(wrapper);
	}

}
