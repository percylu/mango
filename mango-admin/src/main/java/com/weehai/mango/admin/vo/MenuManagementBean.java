package com.weehai.mango.admin.vo;

import java.util.List;
import  com.fasterxml.jackson.annotation.JsonInclude;

import javafx.scene.Parent;
import lombok.Data;

import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;

/**
 * 菜单封装对象
 * @author PercyLu
 * @date Jan 05, 2020
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuManagementBean {
	private Long id;
	private Long parent;
	private String name;
	private String title;
	private String icon;
    private String path;
    private String component;
    private Integer type;
    private Integer orderNum;
    private boolean menu;
    private String perms;
    private String delFlag;
    private boolean hasChildren;
}