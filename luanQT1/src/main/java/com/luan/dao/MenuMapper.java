package com.luan.dao;

import com.luan.entity.MenusEntity;
import com.luan.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<MenusEntity> getMenu(@Param("userId") String userId, @Param("menuType") String menuType);
}