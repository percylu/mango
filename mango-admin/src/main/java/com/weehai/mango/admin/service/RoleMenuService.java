package com.weehai.mango.admin.service;

import com.weehai.mango.admin.model.RoleMenu;
import com.weehai.mango.admin.vo.MenuBean;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色菜单 服务类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
public interface RoleMenuService extends IService<RoleMenu> {
	int insertRoleMenu(Long id,List<MenuBean> menuBeans);
	void updateRoleMenu(Long id,List<MenuBean> menuBeans);

	int deleteByRoleId(Long id);
	
}
