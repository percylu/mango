package com.weehai.mango.admin.vo;

import java.util.List;

import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;

/**
 * 菜单封装对象
 * @author PercyLu
 * @date Jan 05, 2020
 */
public class RoleBean {

    private String key;
    private String name;
    private String description;
    private List<MenuBean> routes;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<MenuBean> getRoutes() {
		return routes;
	}
	public void setRoutes(List<MenuBean> routes) {
		this.routes = routes;
	}


}
