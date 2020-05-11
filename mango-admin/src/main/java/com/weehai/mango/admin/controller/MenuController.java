package com.weehai.mango.admin.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weehai.mango.admin.dao.MenuMapper;
import com.weehai.mango.admin.model.Menu;
import com.weehai.mango.admin.service.MenuService;
import com.weehai.mango.admin.vo.ChildMenuBean;
import com.weehai.mango.admin.vo.MenuBean;
import com.weehai.mango.admin.vo.MenuManagementBean;
import com.weehai.mango.admin.vo.MenuQueryBean;
import com.weehai.mango.admin.vo.RoleBean;
import com.weehai.mango.admin.vo.RoleQueryBean;
import com.weehai.mango.core.http.HttpResult;
import com.weehai.mango.core.page.PageResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.jcajce.provider.asymmetric.rsa.CipherSpi.PKCS1v1_5Padding_PublicOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@RestController
public class MenuController {
	@Autowired
    private MenuService menuService;
	@Autowired
	private MenuMapper menuMapper;
	//全部菜单
	@PostMapping(value="/menus",produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpResult menus(@RequestBody MenuQueryBean menuQueryBean,HttpServletRequest request) {

		String title=menuQueryBean.getTitle();
		int page=menuQueryBean.getPage();
		int limit=menuQueryBean.getLimit();
		
    	List<MenuManagementBean> pageResult=menuService.findAll(title, page, limit);
		return HttpResult.ok(pageResult);
	}
	//根据父菜单，选择子菜单
	@GetMapping(value="/menus/{id}")
	public HttpResult menus(@PathVariable("id") Long id) throws IllegalAccessException {
		List<MenuManagementBean> menus = menuService.findMenuByParentId(id);
		
		return HttpResult.ok(menus);
	}
	//更新是否是菜单
	@GetMapping(value="/ismenu/{id}")
	public HttpResult ismenu(@PathVariable("id") Long id) throws IllegalAccessException {
		int isUpdate = menuService.updateIsMenu(id);
		switch (isUpdate) {
		case 0:
			return HttpResult.ok("菜单已禁用！");
		case 1:
			return HttpResult.ok("菜单已激活！");
		case -1:
			return HttpResult.error(201,"菜单管理，不允许隐藏！");
		case -2:
			 return HttpResult.error(201,"菜单不存在！");
		case -3:
			return HttpResult.error(201,"菜单更新失败！");
		default:
			return HttpResult.error(201,"未知错误");
		}
	}
	//获取父级菜单
	@GetMapping(value = "/getParent") 
	public HttpResult getParent() {
		List<MenuBean> menus = menuService.findMenu("");
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(MenuBean menu:menus) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", menu.getId());
			map.put("title", menu.getMeta().getTitle());
			if((menu.getChildren() instanceof List)&&menu.getHidden()==0) {
				List<Map<String,Object>> childList=new ArrayList<Map<String,Object>>();
				List<ChildMenuBean> childMenuBeans =menu.getChildren();
				for(ChildMenuBean child:childMenuBeans) {
					Map<String, Object> childMap = new HashMap<String, Object>();
					childMap.put("id", child.getId());
					childMap.put("title", child.getMeta().getTitle());
					childList.add(childMap);
				}
				if(childList.size()>0) {
					map.put("child", childList);
				}
			}
			
			list.add(map);

		}
		return HttpResult.ok(list);
	}
	
	 

}

