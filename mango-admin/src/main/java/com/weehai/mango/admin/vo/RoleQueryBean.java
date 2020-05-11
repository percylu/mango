package com.weehai.mango.admin.vo;

/**
 * 登录接口封装对象
 * @author PercyLu
 * @date Jan 05, 2020
 */
public class RoleQueryBean {

    private int page;
    private int limit;
    private String name;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    


}
