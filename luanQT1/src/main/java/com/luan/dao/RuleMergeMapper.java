package com.luan.dao;

import com.luan.model.RuleMerge;
import org.apache.ibatis.annotations.Param;

public interface RuleMergeMapper {
    int deleteByPrimaryKey(String id);

    int insert(RuleMerge record);

    int insertSelective(RuleMerge record);

    RuleMerge selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RuleMerge record);

    int updateByPrimaryKey(RuleMerge record);

    RuleMerge selectBySupplier(@Param("sender") String supplier, @Param("type") String coaltType);
}