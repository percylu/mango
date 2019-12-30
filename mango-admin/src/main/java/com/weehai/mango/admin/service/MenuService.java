package com.weehai.mango.admin.service;

import com.weehai.mango.admin.model.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
public interface MenuService extends IService<Menu> {
    /**
     * 根据用户用查找菜单权限
     * @param  username
     * @return
     */
    List<Menu> findByUser(String username);
}
