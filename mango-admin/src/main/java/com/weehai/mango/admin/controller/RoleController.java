package com.weehai.mango.admin.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weehai.mango.admin.constant.Constants;
import com.weehai.mango.admin.dao.UserRoleMapper;
import com.weehai.mango.admin.model.Role;
import com.weehai.mango.admin.service.MenuService;
import com.weehai.mango.admin.service.RoleMenuService;
import com.weehai.mango.admin.service.RoleService;
import com.weehai.mango.admin.service.UserRoleService;
import com.weehai.mango.admin.vo.LoginBean;
import com.weehai.mango.admin.vo.MenuBean;
import com.weehai.mango.admin.vo.RoleQueryBean;
import com.weehai.mango.admin.vo.RoleBean;
import com.weehai.mango.core.http.HttpResult;
import com.weehai.mango.core.page.PageResult;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 角色管理 前端控制器
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@RestController
public class RoleController {
	@Autowired
    private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleMenuService roleMenuService;
	@Autowired
	private UserRoleService userRoleMapper;
	
	@GetMapping("/routes")
	public HttpResult routes(HttpServletRequest request) {

    	List<MenuBean> menuBeans=menuService.findAll();
		return HttpResult.ok(menuBeans);
	}
	
	@PostMapping(value="/roles",produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpResult roles(@RequestBody RoleQueryBean roleQueryBean,HttpServletRequest request) {
//		Principal principal = request.getUserPrincipal();
//    	String name=principal.getName();
		String name=roleQueryBean.getName();
		int page=roleQueryBean.getPage();
		int limit=roleQueryBean.getLimit();

    	PageResult pageResult=roleService.findAll(name, page, limit);
    	
    	List<Map<String, Object>> roles=(List<Map<String, Object>>) pageResult.getContent();

//    	return HttpResult.ok(pageResult);
    	List<RoleBean> roleVos=new ArrayList<RoleBean>();
    	for(Map<String, Object> role:roles) {
    		Long id=(Long)role.get("id");
    		List<MenuBean> menus=menuService.findMenuByRoleId(id);
        	RoleBean roleVo=new RoleBean();
        	roleVo.setKey(String.valueOf(id));
        	roleVo.setName((String)role.get("name"));
        	roleVo.setRoutes(menus);
        	roleVo.setDescription((String)role.get("remark"));
        	roleVos.add(roleVo);
    	}
		return HttpResult.ok(roleVos);
	}
	@PostMapping(value="/role")
	public HttpResult role(@RequestBody RoleBean roleVo,HttpServletRequest request) {
		Long id=roleService.InsetRole(roleVo);
		if(id==-1L) {
			return HttpResult.error("角色已存在");
		}
		  //添加角色菜单权限
        roleMenuService.insertRoleMenu(id,roleVo.getRoutes());
		JSONObject object = new JSONObject();
		object.put("key", id);
		return HttpResult.ok(object);
	}
	
	@DeleteMapping(value="/role/{id}")
	public HttpResult deleteRole(@PathVariable("id") Long id, HttpServletRequest request) {
		roleService.deleteById(id);
		roleMenuService.deleteByRoleId(id);
		userRoleMapper.deleteByRoleId(id);
		return HttpResult.ok("success");
	}
	
	@PostMapping(value="/role/{id}")
	public HttpResult UpdateRole(@PathVariable("id") Long id,@RequestBody RoleBean roleVo) {
		roleService.updateRole(roleVo);
		roleMenuService.updateRoleMenu(id, roleVo.getRoutes());
		return HttpResult.ok("success");
	}
	
	@DeleteMapping(value="/deleteRoles")
	public HttpResult deleteRoles(@RequestBody List<Long> ids) {
		for(Long id:ids) {
			roleService.deleteById(id);
			roleMenuService.deleteByRoleId(id);
			userRoleMapper.deleteByRoleId(id);
		}
		return HttpResult.ok("success");
	}
}

