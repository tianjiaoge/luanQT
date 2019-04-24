package com.luan.entity;

import com.luan.model.User;

import java.util.List;

public class SessionUser {

    private User user;

    /**
     * 部门
     */
    private List<String>departments;
    private List<MenusEntity> button;
    //一级菜单
    private List<MenusEntity> menus;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MenusEntity> getButton() {
        return button;
    }

    public void setButton(List<MenusEntity> button) {
        this.button = button;
    }

    public List<MenusEntity> getMenus() {
        return menus;
    }

    public void setMenus(List<MenusEntity> menus) {
        this.menus = menus;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "user=" + user +
                ", button=" + button +
                ", menus=" + menus +
                '}';
    }
}
