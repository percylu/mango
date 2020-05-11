package com.weehai.mango.admin.service.impl;

import com.weehai.mango.admin.model.Menu;
import com.weehai.mango.admin.model.Role;
import com.weehai.mango.admin.model.RoleMenu;
import com.weehai.mango.admin.model.UserRole;
import com.weehai.mango.admin.dao.RoleMapper;
import com.weehai.mango.admin.dao.RoleMenuMapper;
import com.weehai.mango.admin.service.MenuService;
import com.weehai.mango.admin.service.RoleMenuService;
import com.weehai.mango.admin.vo.ChildMenuBean;
import com.weehai.mango.admin.vo.MenuBean;
import com.weehai.mango.admin.vo.PermsBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.event.MenuListener;

import org.apache.poi.hpsf.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
	@Autowired
	MenuService menuService;
	@Autowired
	RoleMenuMapper roleMenuMapper;

	@Override
	public int insertRoleMenu(Long id, List<MenuBean> menuBeans) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = principal.toString();
		for (MenuBean menuBean : menuBeans) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRoleId(id);
			Menu menu = menuService.findByName(menuBean.getName());
			roleMenu.setMenuId(menu.getId());

			roleMenu.setCreateBy(userName);
			LocalDateTime localDateTime = LocalDateTime.now();
			roleMenu.setCreateTime(localDateTime);
			roleMenu.setLastUpdateBy(userName);
			roleMenu.setLastUpdateTime(localDateTime);
			roleMenuMapper.insert(roleMenu);
			List<ChildMenuBean> childMenuBeans = menuBean.getChildren();
			for (ChildMenuBean childMenuBean : childMenuBeans) {
				RoleMenu childMenu = new RoleMenu();
				childMenu.setCreateBy(userName);
				childMenu.setCreateTime(localDateTime);
				childMenu.setLastUpdateBy(userName);
				childMenu.setLastUpdateTime(localDateTime);
				childMenu.setRoleId(id);
				Menu menus = menuService.findByName(childMenuBean.getName());
				childMenu.setMenuId(menus.getId());
				roleMenuMapper.insert(childMenu);

				// 三级菜单
				List<PermsBean> permsBeans = childMenuBean.getChildren();
				if(permsBeans instanceof List) {
					for (PermsBean permsBean : permsBeans) {
						RoleMenu permMenu = new RoleMenu();
						permMenu.setCreateBy(userName);
						permMenu.setCreateTime(localDateTime);
						permMenu.setLastUpdateBy(userName);
						permMenu.setLastUpdateTime(localDateTime);
						permMenu.setRoleId(id);
						permMenu.setMenuId(permsBean.getId());
						System.out.print(permsBean.getId());
						System.out.print(permMenu.getId());
						roleMenuMapper.insert(permMenu);
					}
				}

			}

		}
		return 0;
	}

	@Override
	public int deleteByRoleId(Long id) {
		// TODO Auto-generated method stub
		QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
		wrapper.eq("role_id", id);
		return roleMenuMapper.delete(wrapper);
	}

	@Override
	public void updateRoleMenu(Long id, List<MenuBean> menuBeans) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = principal.toString();
		List<Long> menuIds = new ArrayList<Long>();
		LocalDateTime localDateTime = LocalDateTime.now();

		for (MenuBean menuBean : menuBeans) {

			Menu menu = menuService.findByName(menuBean.getName());
			menuIds.add(menu.getId());

			List<ChildMenuBean> childMenuBeans = menuBean.getChildren();
			for (ChildMenuBean childMenuBean : childMenuBeans) {

				Menu childMenus = menuService.findByName(childMenuBean.getName());
				menuIds.add(childMenus.getId());

				// 三级菜单
				List<PermsBean> permsBeans = childMenuBean.getChildren();
				if (permsBeans instanceof List) {
					for (PermsBean permsBean : permsBeans) {
						menuIds.add(permsBean.getId());
					}
				}
			}
		}
		// 原本存在的menu id
		QueryWrapper<RoleMenu> wrapper = new QueryWrapper<RoleMenu>();
		wrapper.select("menu_id").eq("role_id", id);
		List<RoleMenu> roleMenus = roleMenuMapper.selectList(wrapper);
		// 取出menu_id列
		List<Long> orgMenuIds = roleMenus.stream().map(RoleMenu -> RoleMenu.getMenuId()).collect(Collectors.toList());
		// 删除orgMenuIds没有的menu，增加orgMenuIds有的menu
		for (Long menu : orgMenuIds) {
			int isMenu = menuIds.indexOf(menu);
			if (isMenu == -1) {
				// 不存在则删除
				RoleMenu roleMenu = new RoleMenu();
				Map<String, Object> delMap = new HashMap<String, Object>();
				delMap.put("menu_id", menu);
				roleMenuMapper.deleteByMap(delMap);

			}
		}

		for (Long menu : menuIds) {
			int isMenu = orgMenuIds.indexOf(menu);
			if (isMenu == -1) {
				// 不存在则增加
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(id);
				roleMenu.setMenuId(menu);
				roleMenu.setCreateBy(userName);
				roleMenu.setCreateTime(localDateTime);
				roleMenu.setLastUpdateBy(userName);
				roleMenu.setLastUpdateTime(localDateTime);
				roleMenuMapper.insert(roleMenu);
			}
		}

	}

}
