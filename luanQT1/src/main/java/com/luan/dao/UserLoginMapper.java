package com.luan.dao;

import com.luan.model.UserLogin;

public interface UserLoginMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserLogin record);

    int updateByPrimaryKey(UserLogin record);
}