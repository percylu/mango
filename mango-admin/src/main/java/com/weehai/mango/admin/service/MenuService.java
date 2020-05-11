package com.weehai.mango.admin.service;

import com.weehai.mango.admin.model.Menu;
import com.weehai.mango.admin.model.User;
import com.weehai.mango.admin.vo.MenuBean;
import com.weehai.mango.admin.vo.MenuManagementBean;
import com.weehai.mango.core.page.PageResult;
import com.weehai.mango.core.service.CurdService;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
public interface MenuService extends IService<Menu>, CurdService<Menu> {
    /**
     * 查找所有菜单权限
     * @param  username
     * @return
     */
    List<MenuBean> findAll();
    
	/**
     *  根据所有菜单权限
     * @return
     */
	List<MenuManagementBean> findAll(String title,int page,int limit);
    
    /**
     * 根据用户用查找菜单权限
     * @param  username
     * @return
     */
    List<Menu> findByUser(String username);
    
    /**
     * 根据用户查找菜单
     * @param username
     * @return
     */
    List<MenuBean> findMenu(String username);
    
    /**
     * 根据角色查找菜单
     * @param role_id
     * @return
     */
    List<MenuBean> findMenuByRoleId(Long id);
    
    /**
     * 根据菜单名查找菜单
     * @param name
     * @return
     */
    
    Menu findByName(String name);
    
    /**
     * 根据父ID查找菜单
     * @param parent_id
     * @return
     * @throws IllegalAccessException 
     */
    List<MenuManagementBean> findMenuByParentId(Long parent_id) throws IllegalAccessException;
    
    int updateIsMenu(Long id);
}
