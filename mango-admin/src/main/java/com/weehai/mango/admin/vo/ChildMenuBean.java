package com.weehai.mango.admin.vo;

import java.util.List;

import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.weehai.mango.admin.vo.MenuBean.Meta;

import lombok.Data;

/**
 * 菜单封装对象
 * @author PercyLu
 * @date Jan 05, 2020
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ChildMenuBean {
	private Long id;
    private String path;
    private String component;
    private String name;
    private int hidden;
    private Meta meta;
    private List<PermsBean> children;
   public List<PermsBean> getChildren() {
		return children;
	}
	public void setChildren(List<PermsBean> children) {
		this.children = children;
	}
public class Meta{
	public Meta() {
	    	
	}
    
	public Meta(String title, List<String> roles) {
    	super();
    	this.title = title;
    	this.roles = roles;
	}
	private String title;
	private List<String> roles;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	public Meta Mate(String title,  List<String> roles) {
		// TODO Auto-generated method stub
		return new Meta(title, roles);
	}   
}
