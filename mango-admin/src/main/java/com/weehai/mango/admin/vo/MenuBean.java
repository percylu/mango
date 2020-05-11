package com.weehai.mango.admin.vo;

import java.util.List;
import  com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;

/**
 * 菜单封装对象
 * @author PercyLu
 * @date Jan 05, 2020
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuBean {
	private Long id;
    private String path;
    private String component;
    private String redirect;
    private String alwaysShow;
    private int hidden;

    private String name;
    private Meta meta;
    private List<ChildMenuBean> children;
    public class Meta{
	   
    public Meta(String title, String icon, List<String> roles) {
		super();
		this.title = title;
		this.icon = icon;
		this.roles = roles;
	}
    public Meta() {
    	
    }
	private String title;
	private String icon;
	private List<String> roles;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
   }
	
    public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	public String getAlwaysShow() {
		return alwaysShow;
	}
	public void setAlwaysShow(String alwaysShow) {
		this.alwaysShow = alwaysShow;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public List<ChildMenuBean> getChildren() {
		return children;
	}
	public void setChildren(List<ChildMenuBean> children) {
		this.children = children;
	}
	public Meta Mate(String title, String icon, List<String> roles) {
		// TODO Auto-generated method stub
		return new Meta(title, icon, roles);
	}
	   
   
   
}
