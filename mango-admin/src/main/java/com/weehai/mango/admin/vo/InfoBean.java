package com.weehai.mango.admin.vo;

import java.util.List;

import com.alibaba.druid.stat.TableStat.Name;

/**
 * 登录接口封装对象
 * @author PercyLu
 * @date Jan 05, 2020
 */
public class InfoBean {

    private List<String> roles;
    private String introduction;
    private String avatar;
    private String name;
    private List<MenuBean> menus;
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MenuBean> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuBean> menus) {
		this.menus = menus;
	}


    
}
