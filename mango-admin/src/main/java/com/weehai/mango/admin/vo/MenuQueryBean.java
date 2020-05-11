package com.weehai.mango.admin.vo;

import java.util.List;

import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;

import lombok.Data;

/**
 * 菜单封装对象
 * @author PercyLu
 * @date Jan 05, 2020
 */
@Data
public class MenuQueryBean {

    private int page;
    private int limit;
    private String title;

}
