package com.weehai.mango.admin.service.impl;

import com.weehai.mango.admin.model.Role;
import com.weehai.mango.admin.model.User;
import com.weehai.mango.admin.constant.Constants;
import com.weehai.mango.admin.dao.MenuMapper;
import com.weehai.mango.admin.dao.RoleMapper;
import com.weehai.mango.admin.dao.UserMapper;
import com.weehai.mango.admin.service.RoleMenuService;
import com.weehai.mango.admin.service.RoleService;
import com.weehai.mango.admin.service.UserService;
import com.weehai.mango.admin.vo.RoleBean;
import com.weehai.mango.core.http.HttpResult;
import com.weehai.mango.core.page.MyBatisPageHelper;
import com.weehai.mango.core.page.PageRequest;
import com.weehai.mango.core.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.ss.extractor.EmbeddedExtractor.Ole10Extractor;
import org.apache.poi.ss.formula.functions.T;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色管理 服务实现类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserService userService;

	@Override
	public Role findRoleById(Long userId) {
		// TODO Auto-generated method stub
		return roleMapper.findRoleByUserId(userId);
	}
	@Override
	public Role findRoleByUsername(String name) {
		// TODO Auto-generated method stub
		User user = userService.findByName(name);
		
		return roleMapper.findRoleByUserId(user.getId());
	}
	@Override
	public PageResult findAll(String name,int page,int limit) {
		// TODO Auto-generated method stub
		PageRequest pageRequest=new PageRequest();
		pageRequest.setPageSize(limit);
		pageRequest.setPageNum(page);
        QueryWrapper<RoleMapper> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
	if(name!=""&&name!=null&&name!=Constants.ADMIN) {
        wrapper.like("name",name).orderByAsc("id");
	}
	    return MyBatisPageHelper.findPageyQuery(pageRequest, wrapper, roleMapper);
	    
	}
	@Override
	public Long InsetRole(RoleBean roleVo) {
		// TODO Auto-generated method stub
		//创建Role 
		Role role=new Role();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = principal.toString();
		String name = roleVo.getName();
		String desc = roleVo.getDescription();
		QueryWrapper<Role> wrapper=new QueryWrapper<>();
		wrapper.eq("name",name);
		int count=roleMapper.selectCount(wrapper);
		if(count >0 ) {
			return -1L;
		}
		LocalDateTime dateTime=LocalDateTime.now();;
        role.setName(name);
        role.setRemark(desc);
        role.setCreateBy(userName);
        role.setDelFlag(0);
        role.setLastUpdateBy(userName);
        role.setCreateTime(dateTime);
        role.setLastUpdateTime(dateTime);
        int result=roleMapper.insert(role);
        
      
		return role.getId();
	}
	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		int count=roleMapper.deleteById(id);
		return count;
	}
	@Override
	public void updateRole(RoleBean roleVo) {
		// TODO Auto-generated method stub
		Role role = new Role();
		LocalDateTime lastUpdateTime=LocalDateTime.now();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = principal.toString();
		role.setLastUpdateTime(lastUpdateTime);
		role.setLastUpdateBy(userName);
		role.setName(roleVo.getName());
		role.setRemark(roleVo.getDescription());
		QueryWrapper<Role> wrapper = new QueryWrapper<Role>();
		wrapper.eq("id", role.getId());
		roleMapper.update(role, wrapper);
		
	}
	
	
	

}
