package com.luan.dao;

import com.luan.model.Assay;

public interface AssayMapper {
    int deleteByPrimaryKey(String id);

    int insert(Assay record);

    int insertSelective(Assay record);

    Assay selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Assay record);

    int updateByPrimaryKeyWithBLOBs(Assay record);

    int updateByPrimaryKey(Assay record);
}