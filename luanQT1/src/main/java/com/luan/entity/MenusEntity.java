package com.luan.entity;

import com.luan.model.Menu;

import java.util.List;

public class MenusEntity  extends Menu {

    List<Menu> secondMenus;

    public List<Menu> getSecondMenus() {
        return secondMenus;
    }

    public void setSecondMenus(List<Menu> secondMenus) {
        this.secondMenus = secondMenus;
    }
}
