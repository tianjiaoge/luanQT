package com.luan.dao;

import com.luan.model.AssayItem;

public interface AssayItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(AssayItem record);

    int insertSelective(AssayItem record);

    AssayItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssayItem record);

    int updateByPrimaryKey(AssayItem record);
}