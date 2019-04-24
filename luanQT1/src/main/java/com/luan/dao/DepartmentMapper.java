package com.luan.dao;

import com.luan.model.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<String> findDepartByUserId(String userId);
}