package com.luan.service.impl;

import com.luan.dao.DepartmentMapper;
import com.luan.dao.MenuMapper;
import com.luan.dao.UserMapper;
import com.luan.entity.MenusEntity;
import com.luan.entity.SessionUser;
import com.luan.model.User;
import com.luan.service.UserService;
import com.luan.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    UserMapper userMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    MenuMapper menuMapper;

    @Override
    public Map<String,Object> userLogin(User fromUser) {
        Map<String,Object> retMap = new HashMap<>();
        User user =  userMapper.findUserByUsername(fromUser.getUsername());
        if (user==null){
            retMap.put("msg","账号错误");
            retMap.put("success",false);
            return  retMap;
        }
        if(user.getLocked()){
            retMap.put("msg","账号已锁定");
            retMap.put("success",false);
            return  retMap;
        }
        if (!user.getPassword().equals(fromUser.getPassword())){
            retMap.put("msg","账号错误");
            retMap.put("success",false);
            return retMap;
        }
        //查询用户所属部门数量 超过1个说明为超级管理员直接进入后台
        List<String> departments = departmentMapper.findDepartByUserId(user.getId());
        if (departments.size()==0){
            retMap.put("msg","无权访问");
            retMap.put("success",false);
            return  retMap;
        }
        List<MenusEntity> menus = menuMapper.getMenu(user.getId(),"menu");
        if (menus.size()==0){
            retMap.put("msg","无权访问");
            retMap.put("success",false);
            return  retMap;
        }
        SessionUser sessionUser = new SessionUser();
        sessionUser.setDepartments(departments);
        sessionUser.setMenus(menus);
        sessionUser.setUser(user);
        //部门超过一个 或者菜单权限多于一个 直接进入后台 否则进入采样页面
        if (departments.size()>1 || menus.get(0).getSecondMenus().size()>0){
            List<MenusEntity> buttons = menuMapper.getMenu(user.getId(),"button");
            sessionUser.setButton(buttons);
        }
        SessionUtils.setObject(sessionUser);
        retMap.put("url",sessionUser.getButton()!=null?"main.do":menus.get(0).getMenuUrl());
        retMap.put("success",true);
        return  retMap;
    }

    @Override
    @Cacheable(value = "luanqt",key="'user'+#username")
    public User findUserByusername(String username) {
        User user = userMapper.findUserByUsername(username);
        System.out.println("查询数据库获取user");
        return user;
    }


}
