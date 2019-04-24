package com.luan.dao;

import com.luan.model.AssayTemplate;

public interface AssayTemplateMapper {
    int deleteByPrimaryKey(String id);

    int insert(AssayTemplate record);

    int insertSelective(AssayTemplate record);

    AssayTemplate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssayTemplate record);

    int updateByPrimaryKeyWithBLOBs(AssayTemplate record);

    int updateByPrimaryKey(AssayTemplate record);
}