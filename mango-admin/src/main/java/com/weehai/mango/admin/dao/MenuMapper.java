package com.weehai.mango.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weehai.mango.admin.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findByUserName(@Param(value="userName") String userName);
    /**
     * 查找所有菜单
     * @return
     */
    List<Menu>findAll();
    List<Menu>findAllMenu();
    List<Menu> findParentMenu(@Param(value="userName") String userName);
    List<Menu> findChildMenu(@Param(value="parent_id") Long parent_id);
    List<Menu> findChildMenu2(@Param(value="parent_id") Long parent_id);
    List<Menu> findMenuByRoleId(@Param(value="roleId") Long roleId);
    List<Menu> findChildMenuByRoleId(@Param(value="parentId") Long parentId,@Param(value="roleId") 
    		Long roleId);

}
