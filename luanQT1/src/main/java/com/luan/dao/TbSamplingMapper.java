package com.luan.dao;

import com.luan.model.TbSampling;

public interface TbSamplingMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSampling record);

    int insertSelective(TbSampling record);

    TbSampling selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSampling record);

    int updateByPrimaryKey(TbSampling record);
}