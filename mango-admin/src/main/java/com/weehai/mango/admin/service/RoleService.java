package com.weehai.mango.admin.service;

import com.weehai.mango.admin.model.Role;
import com.weehai.mango.admin.vo.RoleBean;
import com.weehai.mango.core.page.PageResult;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色管理 服务类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
public interface RoleService extends IService<Role> {
	/**
     *  根据所有角色
     * @return
     */
	PageResult findAll(String name,int page,int limit);
	
	/**
     *  根据用户ID查找用户角色
     * @return
     */
	Role findRoleById(Long userId);
	
	/**
     *  根据用户名查找用户角色
     * @return
     */
	Role findRoleByUsername(String name);
	
	Long InsetRole(RoleBean roleVo);
	int deleteById(Long id);
	void updateRole(RoleBean roleVo);
}
