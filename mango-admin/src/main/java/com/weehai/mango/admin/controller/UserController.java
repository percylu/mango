package com.weehai.mango.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weehai.mango.admin.model.User;
import com.weehai.mango.admin.service.UserService;
import com.weehai.mango.core.http.HttpResult;
import com.weehai.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
}

