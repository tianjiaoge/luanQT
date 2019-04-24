package com.luan.dao;

import com.luan.model.RoleDepartment;

public interface RoleDepartmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleDepartment record);

    int insertSelective(RoleDepartment record);

    RoleDepartment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleDepartment record);

    int updateByPrimaryKey(RoleDepartment record);

}