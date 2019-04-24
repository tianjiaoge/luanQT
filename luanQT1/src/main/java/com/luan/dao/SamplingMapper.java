package com.luan.dao;

import com.luan.model.Sampling;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SamplingMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sampling record);

    int insertSelective(Sampling record);

    Sampling selectByPrimaryKey(String id);
    int updateByPrimaryKeySelective(Sampling record);

    int updateByPrimaryKey(Sampling record);

   Map<String,Object> selectMaxMergeCodeAndCountBySender(@Param("ruleMargeId") String ruleMargeId, @Param("depatmentCode") String depatmentCode);

   String selectMaxSampleCode(String depatmentCode);

   Sampling selectByLinkId(String lingkId);

   Map<String,Object> selectMargeItem(String sampleCode);

   int updateMargeStateByMargeCode(String margeCode);

   List<Map<String,Object>> getSampleOperateData(String depatmentCode);

   List<Map<String,Object>> selectSamplingCount();

}