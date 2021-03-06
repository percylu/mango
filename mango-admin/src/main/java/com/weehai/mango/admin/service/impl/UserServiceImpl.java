package com.weehai.mango.admin.service.impl;

import com.weehai.mango.admin.model.Menu;
import com.weehai.mango.admin.model.Role;
import com.weehai.mango.admin.model.User;
import com.weehai.mango.admin.dao.RoleMapper;
import com.weehai.mango.admin.dao.UserMapper;
import com.weehai.mango.admin.service.MenuService;
import com.weehai.mango.admin.service.UserService;
import com.weehai.mango.admin.vo.MenuBean;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecwid.consul.v1.acl.model.NewAcl;
import com.weehai.mango.core.page.MyBatisPageHelper;
import com.weehai.mango.core.page.PageRequest;
import com.weehai.mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
	@Autowired
    private MenuService menuService;
    @Override
	public Map<String, String> findRoleByName(String userName) {
		// TODO Auto-generated method stub
    	Map<String, String> map=new HashMap<String, String>();
    	User user=userMapper.findByName(userName);
    	Role role=roleMapper.findRoleByUserId(user.getId());
    	map.put("roles", role.getName());
    	map.put("introduction", user.getNickName());
    	map.put("avatar", user.getAvatar());
    	map.put("name", user.getName());
		return map;
	}


    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public Set<String> findPermissions(String userName) {
        Set<String> perms = new HashSet<>();
        List<Menu> sysMenus = menuService.findByUser(userName);
        for(Menu sysMenu:sysMenus) {
            if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MyBatisPageHelper.findPage(pageRequest,UserMapper.class,userMapper);
    }

	@Override
	public List<MenuBean> findMenu(String userName) {
		// TODO Auto-generated method stub
		List<MenuBean> sysMenus = menuService.findMenu(userName);

		return sysMenus;
	}
	
    
}
