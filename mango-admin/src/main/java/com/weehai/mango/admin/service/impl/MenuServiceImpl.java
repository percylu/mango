package com.weehai.mango.admin.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.HttpConnection;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weehai.mango.admin.constant.Constants;
import com.weehai.mango.admin.dao.MenuMapper;
import com.weehai.mango.admin.dao.RoleMapper;
import com.weehai.mango.admin.model.Menu;
import com.weehai.mango.admin.model.Role;
import com.weehai.mango.admin.service.MenuService;
import com.weehai.mango.admin.util.CommonUtils;
import com.weehai.mango.admin.vo.ChildMenuBean;
import com.weehai.mango.admin.vo.MenuBean;
import com.weehai.mango.admin.vo.MenuManagementBean;
import com.weehai.mango.admin.vo.PermsBean;
import com.weehai.mango.core.page.MyBatisPageHelper;
import com.weehai.mango.core.page.PageRequest;
import com.weehai.mango.core.page.PageResult;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author 卢水柏
 * @since 2019-12-23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<Menu> findByUser(String userName) {
        if(userName == null || "".equals(userName) || Constants.ADMIN.equalsIgnoreCase(userName)) {
            return menuMapper.findAll();
        }
        return menuMapper.findByUserName(userName);
    }
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<MenuBean> findMenu(String username) {
		// TODO Auto-generated method stub
		List<MenuBean> menuBeans = new ArrayList<>();
		List<Menu>menus=null;
		if(username==null||"".equals(username)||Constants.ADMIN.equalsIgnoreCase(username)) {
			menus=menuMapper.findAllMenu();
		}else {
			menus=menuMapper.findParentMenu(username);
		}
		List<String> roleArray= new ArrayList<String>();
		
		for(Menu menu:menus) {
			List<Menu>childMenus=menuMapper.findChildMenu(menu.getId());
			MenuBean menuBean=new MenuBean();
			menuBean.setComponent(menu.getComponent());
			if(menu.getIsMenu().equals(0)) {
				menuBean.setAlwaysShow("false");
			}else {
				menuBean.setAlwaysShow("true");
			}
			menuBean.setId(menu.getId());
			menuBean.setName(menu.getName());
			menuBean.setPath(menu.getPath());
			menuBean.setHidden(menu.getIsMenu()==1?0:1);
			menuBean.setRedirect(menu.getRedirect());
			menuBean.setMeta(menuBean.Mate(menu.getTitle(),menu.getIcon(),roleArray));
			List<ChildMenuBean>childBeans = new ArrayList<>();
			List<PermsBean> cchildren=new ArrayList<>();
			for(Menu childMenu:childMenus) {
				ChildMenuBean childBean=new ChildMenuBean();
				childBean.setId(childMenu.getId());
				childBean.setComponent(childMenu.getComponent());
				childBean.setName(childMenu.getName());
				childBean.setPath(childMenu.getPath());
				childBean.setMeta(childBean.Mate(childMenu.getTitle(),roleArray));
				childBean.setHidden(childMenu.getIsMenu()==1?0:1);
				//第三级权限
				List<Menu>cchildMenus=menuMapper.findChildMenu(childMenu.getId());
				for(Menu cchildMenu:cchildMenus) {
					PermsBean perms = new PermsBean();
					perms.setId(cchildMenu.getId());
					perms.setMeta(perms.Meta(cchildMenu.getTitle()));
					perms.setPath(cchildMenu.getPath());
					perms.setHidden(cchildMenu.getIsMenu()==1?0:1);
					cchildren.add(perms);
				}
				childBean.setChildren(cchildren);
				//
				childBeans.add(childBean);
			}
			menuBean.setChildren(childBeans);
			menuBeans.add(menuBean);
		}
		
		return menuBeans;
	}
	@Override
	public List<MenuBean> findMenuByRoleId(Long id) {
		// TODO Auto-generated method stub
		List<MenuBean> menuBeans = new ArrayList<>();
		Role role=roleMapper.findRoleById(id);
		List<Menu>menus=new ArrayList<Menu>();
		if(role.getName()==null||"".equals(role.getName())||Constants.ADMIN.equalsIgnoreCase(role.getName())){
			menus=menuMapper.findAllMenu();
		}else {
			menus=menuMapper.findMenuByRoleId(id);
		}
	
		for(Menu menu:menus) {

		    List<Menu>childMenus=new ArrayList<>();
			if(role.getName()==null||"".equals(role.getName())||Constants.ADMIN.equalsIgnoreCase(role.getName())){
			  childMenus=menuMapper.findChildMenu2(menu.getId());

			}else {
			  childMenus=menuMapper.findChildMenuByRoleId(menu.getId(),id);
			}
			MenuBean menuBean=new MenuBean();
			menuBean.setComponent(menu.getComponent());
			if(menu.getIsMenu().equals(0)) {
				menuBean.setAlwaysShow("false");
			}else {
				menuBean.setAlwaysShow("true");
			}
			menuBean.setName(menu.getName());
			menuBean.setPath(menu.getPath());
			menuBean.setHidden(menu.getIsMenu()==1?0:1);
			menuBean.setRedirect(menu.getRedirect());
			menuBean.setMeta(menuBean.Mate(menu.getTitle(),menu.getIcon(),null));
			List<ChildMenuBean>childBeans = new ArrayList<>();
			for(Menu childMenu:childMenus) {
				ChildMenuBean childBean=new ChildMenuBean();
			
				childBean.setComponent(childMenu.getComponent());
				childBean.setName(childMenu.getName());
				childBean.setPath(childMenu.getPath());
				childBean.setHidden(childMenu.getIsMenu()==1?0:1);
				childBean.setMeta(childBean.Mate(childMenu.getTitle(),null));
				//三级子菜单
				List<Menu>cchildMenus=new ArrayList<>();
				if(role.getName()==null||"".equals(role.getName())||Constants.ADMIN.equalsIgnoreCase(role.getName())){
					cchildMenus=menuMapper.findChildMenu2(childMenu.getId());
				}else {
					cchildMenus=menuMapper.findChildMenuByRoleId(childMenu.getId(),id);
				}
				List<PermsBean> perms=new ArrayList<PermsBean>();
				for(Menu cchildMenu:cchildMenus) {
					PermsBean perm= new PermsBean();
					perm.setId(cchildMenu.getId());
					perm.setMeta(perm.Meta(cchildMenu.getTitle()));
					perm.setPath(cchildMenu.getPath());
					perm.setHidden(cchildMenu.getIsMenu()==1?0:1);
					perms.add(perm);
				}
				childBean.setChildren(perms);
				childBeans.add(childBean);
			}
			menuBean.setChildren(childBeans);
			menuBeans.add(menuBean);
		}
		
		return menuBeans;
	}
	@Override
	public List<MenuBean> findAll() {
		// TODO Auto-generated method stub
		List<MenuBean> menuBeans = new ArrayList<>();
		List<Menu>menus=null;
		menus=menuMapper.findAllMenu();
	
		List<String> roleArray= new ArrayList<String>();
		
		for(Menu menu:menus) {
			List<Menu>childMenus=menuMapper.findChildMenu2(menu.getId());
			MenuBean menuBean=new MenuBean();
			menuBean.setComponent(menu.getComponent());
			if(menu.getIsMenu().equals(0)) {
				menuBean.setAlwaysShow("false");
			}else {
				menuBean.setAlwaysShow("true");
			}
			menuBean.setName(menu.getName());
			menuBean.setPath(menu.getPath());
			menuBean.setRedirect(menu.getRedirect());
			menuBean.setHidden(menu.getIsMenu()==1?0:1);
			menuBean.setMeta(menuBean.Mate(menu.getTitle(),menu.getIcon(),roleArray));
			List<ChildMenuBean>childBeans = new ArrayList<>();
			for(Menu childMenu:childMenus) {
				ChildMenuBean childBean=new ChildMenuBean();
			
				childBean.setComponent(childMenu.getComponent());
				childBean.setName(childMenu.getName());
				childBean.setPath(childMenu.getPath());
				childBean.setHidden(childMenu.getIsMenu()==1?0:1);
				childBean.setMeta(childBean.Mate(childMenu.getTitle(),roleArray));
				
				//三级子菜单
				List<Menu>cchildMenus=menuMapper.findChildMenu2(childMenu.getId());
				List<PermsBean> perms=new ArrayList<PermsBean>();
				for(Menu cchildMenu:cchildMenus) {
					PermsBean perm= new PermsBean();
					perm.setId(cchildMenu.getId());
					perm.setMeta(perm.Meta(cchildMenu.getTitle()));
					perm.setPath(cchildMenu.getPath());
					perm.setHidden(cchildMenu.getIsMenu()==1?0:1);
					perms.add(perm);
				}
				//
				childBean.setChildren(perms);
				childBeans.add(childBean);
			}
			menuBean.setChildren(childBeans);
			menuBeans.add(menuBean);
		}
		
		return menuBeans;
	}
	@Override
	public Menu findByName(String name) {
		// TODO Auto-generated method stub
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        Menu menu=menuMapper.selectOne(wrapper);
		return menu;
	}
	
	@Override
	public List<MenuManagementBean> findAll(String title,int page,int limit) {
		// TODO Auto-generated method stub
		PageRequest pageRequest=new PageRequest();
		pageRequest.setPageSize(limit);
		pageRequest.setPageNum(page);
        QueryWrapper<MenuMapper> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0).orderByAsc("id");
		if(title!=""&&title!=null) {
	    wrapper.like("title",title).orderByAsc("id");
		}
	    PageResult pageResult= MyBatisPageHelper.findPageyQuery(pageRequest, wrapper, menuMapper);
	    
	    List<Map<String, Object>> menus=(List<Map<String, Object>>) pageResult.getContent();

    	List<MenuManagementBean> menuBeans=new ArrayList<MenuManagementBean>();
    	for(Map<String, Object> menu:menus) {
    		//一级菜单
    		MenuManagementBean parent = setMenuBean(menu);
    		menuBeans.add(parent);
    	}
    	return menuBeans;
	    
	}
	
	private MenuManagementBean setMenuBean(Map<String,Object>menu) {
		MenuManagementBean menuBean=new MenuManagementBean();
		menuBean.setId((Long)menu.get("id"));
		menuBean.setName((String)menu.get("name"));
		menuBean.setTitle((String)menu.get("title"));
		menuBean.setPath((String)menu.get("path"));
		menuBean.setOrderNum((Integer)menu.get("order_num"));
		menuBean.setIcon((String)menu.get("icon"));
		menuBean.setComponent((String)menu.get("component"));
		Integer delFlag=(Integer)menu.get("delFlag");
		menuBean.setDelFlag("正常");
		if(delFlag instanceof Integer){
			if(delFlag==1) {
				menuBean.setDelFlag("禁用");
			}else {
				menuBean.setDelFlag("正常");
			}
		}
		Integer isMenu=(Integer)menu.get("is_menu");
		menuBean.setMenu(false);
		if(isMenu instanceof Integer) {
			if(isMenu==1) {
				menuBean.setMenu(true);
			}else {
				menuBean.setMenu(false);
			}
		}
		menuBean.setType((Integer)menu.get("type"));
		menuBean.setPerms((String)menu.get("perms"));
		menuBean.setParent(0L);
		//二级菜单
		List<Menu>childMenus=menuMapper.findChildMenu((Long)menu.get("id"));
		if(childMenus.size()>0) {
			menuBean.setHasChildren(true);
		}else {
			menuBean.setHasChildren(false);
		}
		return menuBean;
	}
	private MenuManagementBean setMenuBean(Menu menu) {
		MenuManagementBean menuBean=new MenuManagementBean();
		menuBean.setId(menu.getId());
		menuBean.setName(menu.getName());
		menuBean.setTitle(menu.getTitle());
		menuBean.setPath(menu.getPath());
		menuBean.setOrderNum(menu.getOrderNum());
		menuBean.setIcon(menu.getIcon());
		menuBean.setComponent(menu.getComponent());
		Integer delFlag=menu.getDelFlag();
		menuBean.setDelFlag("正常");
		if(delFlag instanceof Integer){
			if(delFlag==1) {
				menuBean.setDelFlag("禁用");
			}else {
				menuBean.setDelFlag("正常");
			}
		}
		Integer isMenu=menu.getIsMenu();
		menuBean.setMenu(false);
		if(isMenu instanceof Integer) {
			if(isMenu==1) {
				menuBean.setMenu(true);
			}else {
				menuBean.setMenu(false);
			}
		}
		menuBean.setType(menu.getType());
		menuBean.setPerms(menu.getPerms());
		menuBean.setParent(0L);
		//二级菜单
		List<Menu>childMenus=menuMapper.findChildMenu(menu.getId());
		if(childMenus.size()>0) {
			menuBean.setHasChildren(true);
		}else {
			menuBean.setHasChildren(false);
		}
		return menuBean;
	}

	@Override
	public List<MenuManagementBean> findMenuByParentId(Long parent_id) throws IllegalAccessException {
		// TODO Auto-generated method stub
		List<Menu> menus = menuMapper.findChildMenu2(parent_id);
		List<MenuManagementBean> menuBeans=new ArrayList<>();
		for(Menu menu:menus) {
    		MenuManagementBean parent = setMenuBean(menu);
    		parent.setParent(parent_id);
    		menuBeans.add(parent);
		}
		return menuBeans;
	}
	@Override
	public int updateIsMenu(Long id) {
		// TODO Auto-generated method stub
 		QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        Menu menu=menuMapper.selectOne(wrapper);
        //如果是菜单管理，不允许隐藏
        if(menu.getName().equals("menu")) {
        	return -1;
        }
        int ismenu=0;
        //菜单不存在
        if(!(menu instanceof Menu)) return -2;
        if (menu.getIsMenu()==1) {
        	ismenu=0;
        }else {
        	ismenu=1;
        }
        menu.setIsMenu(ismenu);
        int result=menuMapper.update(menu, wrapper);
        //菜单更新失败
        if (result<0) {
        	return -3;
        }
		return ismenu;
	}
      
}
