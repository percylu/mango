package com.weehai.mango.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weehai.mango.admin.model.User;
import com.weehai.mango.admin.vo.MenuBean;
import com.weehai.mango.core.service.CurdService;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
public interface UserService extends IService<User>, CurdService<User> {
    /**
     * 查找所有用户
     * @retrun
     */
    List<User> findAll();

    /**
     * 根据用户名查照
     * @parm name
     * @return User
     */
     User findByName(String name);

    /**
     * 查找用户的菜单权限标识集合
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);
    
    /**
     * 查询用户的菜单
     * @param userName
     * @return
     */
    List<MenuBean> findMenu(String userName);
    
    /**
     * 查询用户的角色
     * @param userName
     * @return
     */
    Map<String, String> findRoleByName(String userName);
}
