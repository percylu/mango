package com.weehai.mango.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weehai.mango.admin.constant.Constants;
import com.weehai.mango.admin.dao.UserMapper;
import com.weehai.mango.admin.model.User;
import com.weehai.mango.admin.service.MenuService;
import com.weehai.mango.admin.service.UserService;
import com.weehai.mango.admin.vo.InfoBean;
import com.weehai.mango.admin.vo.MenuBean;
import com.weehai.mango.core.http.HttpResult;
import com.weehai.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户管理 前端控制器
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService;

    @GetMapping(value = "/findAll")
    public Object findAll(){
        return userService.findAll();
    }

    @GetMapping(value="/getUserById")
    public User getUserById(int id){
        return userService.getById(id);

    }

    @PostMapping(value = "/removeIds")
    public boolean removeByIds(@RequestBody List<Integer> list){


        return userService.removeByIds(list);

    }

    @PostMapping(value = "/remove")
    public boolean remove(@RequestBody int id){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return userService.remove(queryWrapper);
    }


    
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(userService.findPage(pageRequest));
    }
    
    @GetMapping(value="/info")
    public HttpResult info(HttpServletRequest request) {
    	Principal principal = request.getUserPrincipal();
    	String name=principal.getName();
    	List<MenuBean> menuBeans=userService.findMenu(name);
    	Map<String,String> user=userService.findRoleByName(name);
    	InfoBean infoBean=new InfoBean();
    	//map.put("introduction", value)
    	infoBean.setAvatar(Constants.BASE_URL+user.get("avatar"));
    	infoBean.setIntroduction(user.get("introduction"));
    	infoBean.setName(user.get("name"));
    	List<String> roles=new ArrayList<String>();
    	roles.add(user.get("roles"));
    	infoBean.setRoles(roles);
    	infoBean.setMenus(menuBeans);
        return HttpResult.ok(infoBean);
    }
    
    @PostMapping(value="/logout")
    public HttpResult logout() {
    	return HttpResult.ok("success");
    }
}

