package com.luan.dao;

import com.luan.model.OpLog;

public interface OpLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(OpLog record);

    int insertSelective(OpLog record);

    OpLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OpLog record);

    int updateByPrimaryKey(OpLog record);
}