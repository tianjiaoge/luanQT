package com.luan.service.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luan.dao.AssayMapper;
import com.luan.dao.RuleMergeMapper;
import com.luan.dao.SamplingMapper;
import com.luan.dao.TbSamplingMapper;
import com.luan.entity.SessionUser;
import com.luan.model.Assay;
import com.luan.model.RuleMerge;
import com.luan.model.Sampling;
import com.luan.model.TbSampling;
import com.luan.service.SamplingService;
import com.luan.utils.DateUtils;
import com.luan.utils.ProperUtils;
import com.luan.utils.SessionUtils;
import com.luan.utils.UUIDGenerator;
import com.luan.webSocket.SpringWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class SamplingServiceImpl implements SamplingService {
    @Autowired
    SpringWebSocketHandler springWebSocketHandler;
    @Autowired
    TbSamplingMapper tbSamplingMapper;
    @Autowired
    RuleMergeMapper ruleMergeMapper;
    @Autowired
    SamplingMapper samplingMapper;
    @Autowired
    AssayMapper assayMapper;

    private final static int MAX_CODE = 9999;
    /**
     * 操作提示音
     */
    public enum HINT_TONE {
        //空卡，刷卡成功，扫码错误，扫码成功，重复扫码
        EmptyCard, ShuaKaSuccess, SaoMaError, SaoMaSuccess,SaoMaOverdue
    }

    @Override
    public String addSampling(String id) {
        //==============获取当前请求ip的session用户信息==============================
        SessionUser sessionUser = (SessionUser) SessionUtils.getIpObject();
        String department = sessionUser.getDepartments().get(0);
        String userId = sessionUser.getUser().getId();
        //======================通过id查找数据源数据==================================
        TbSampling tbSampling = tbSamplingMapper.selectByPrimaryKey(id);
        if (tbSampling == null) {
            //数据不存在通知页面给出提示
            refreshSampleOperateData(userId, department, HINT_TONE.EmptyCard);
            //给接口返回-1 不打印二维码
            return "-1";
        }
        //查询卡号是否存在
        Sampling sampling = samplingMapper.selectByLinkId(tbSampling.getLinkid());
        if (sampling != null) {
            //重复刷卡
            return sampling.getSampleCode();
        }
        //==============================获取采样数字编号===========================
        String sampleCodeNumStr = generatorSampleCodeNumStr(department);
        //采样码A0001
        String sampleCode = department + sampleCodeNumStr;
        //合样码A+班次+时间+随机四位码
        String margeCode = department + DateUtils.getMargeCodeTime() + sampleCodeNumStr;
        //=============================添加数据逻辑=======================================
        //根据煤的来源地和类型查询合样规则表
        RuleMerge ruleMerge = getRuleMerge(tbSampling, userId);
        Map<String, Object> map = samplingMapper.selectMaxMergeCodeAndCountBySender(ruleMerge.getId(), department);
        //获取当前来源地已经刷卡的车辆数
        Long sampleCodeCount = (Long) map.get("count");
        if (sampleCodeCount % ruleMerge.getCount() != 0) {
            //有等待合并的数据 直接取等待合并数据的合样编码
            margeCode = (String) map.get("mergeCode");
        }
        insertSampling(tbSampling, ruleMerge, sampleCode, margeCode, department, userId);
        refreshSampleOperateData(userId, department, HINT_TONE.ShuaKaSuccess);
        return sampleCode;
    }

    /**
     * 拼装采样编码
     * @param department 厂区部门
     * @return 采样码
     */
    private String generatorSampleCodeNumStr(String department) {
        //查询数据库最大编号
        String lastNewSampleCode = samplingMapper.selectMaxSampleCode(department);
        //获取编号 进行加1处理
        int sampleCodeNum = lastNewSampleCode == null ? 1 : Integer.parseInt(lastNewSampleCode.substring(1)) + 1;
        //最大不能超过4位数
        if (sampleCodeNum > MAX_CODE) {
            sampleCodeNum = 1;
        }
        //采样编号0001
        return String.format("%04d", sampleCodeNum);
    }

    /* @Override
     public JSONObject loadCountData() {
         JSONObject jsonObject = new JSONObject();
         //1 返回  x 01-01
         //2 返回  采样数量
         //3 返回  合样数量
         List<Object> xaxis = new ArrayList<>();
         List<Object> samplings = new ArrayList<>();
         List<Object> erges = new ArrayList<>();
         List<Map<String, Object>> maps = samplingMapper.selectSamplingCount();
         for (Map<String,Object> map:maps){
             xaxis.add(map.get("date"));
             samplings.add(map.get("count"));
         }
         jsonObject.put("xaxis",xaxis);
         jsonObject.put("samplings",samplings);
         jsonObject.put("erges",erges);

         return jsonObject;
     }*/
    @Override
    public JSONObject loadCountData() {
        JSONObject jsonObject = new JSONObject();
        //1 返回  x 01-01
        //2 返回  采样数量
        //3 返回  合样数量
        List<Object> xaxis = new ArrayList<>();
        List<Object> samplings = new ArrayList<>();
        List<Object> merges = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        for (int i = 0; i < 5; i++) {
            String xCellStyle = DateUtils.getDateTimeFormat(c.getTime(), "MM-dd");
            xaxis.add(0, xCellStyle);
            samplings.add(0);
            merges.add(0);
            c.setTime(new Date(c.getTimeInMillis() - 24 * 60 * 60 * 1000));
        }
        List<Map<String, Object>> maps = samplingMapper.selectSamplingCount();
        for (Map<String, Object> map : maps) {
            int dateIndex = xaxis.indexOf(map.get("date"));
            if(dateIndex!=-1){
                samplings.set(dateIndex, map.get("count"));
            }
        }
        jsonObject.put("xaxis", xaxis);
        jsonObject.put("samplings", samplings);
        jsonObject.put("merges", merges);

        return jsonObject;
    }

    @Override
    public void loadData() {
        //==============获取session用户信息==============================
        SessionUser sessionUser = (SessionUser) SessionUtils.getObject();
        String department = sessionUser.getDepartments().get(0);
        String userId = sessionUser.getUser().getId();
        //========================================================
        refreshSampleOperateData(userId, department, HINT_TONE.ShuaKaSuccess);
    }

    @Override
    public String saoMa(String sampleCode) {
        //==============获取当前请求ip的session用户信息==============================
        SessionUser sessionUser = (SessionUser) SessionUtils.getIpObject();
        String department = sessionUser.getDepartments().get(0);
        String userId = sessionUser.getUser().getId();
        //======================通过id查找数据源数据==================================
        Map<String, Object> margeItem = samplingMapper.selectMargeItem(sampleCode);
        //采样编号不存在 编码错误
        if (margeItem == null) {
            refreshSampleOperateData(userId, department, HINT_TONE.SaoMaError);
            return "-1";
        }
        int mergeState = Integer.parseInt((String) margeItem.get("mergeState"));
        //合样状态为1 已合样
        if (mergeState==1){
            refreshSampleOperateData(userId,department,HINT_TONE.SaoMaOverdue);
            return "-1";
        }
        int margeRule = (int) margeItem.get("mergeRule");
        int saoMaCount =  ((BigDecimal) margeItem.get("saoMaCount")).intValue();
        String resultStr;
        if (margeRule != saoMaCount + 1) {
            String id = (String) margeItem.get("id");
            Sampling sampling = new Sampling();
            sampling.setId(id);
            sampling.setState(true);
            samplingMapper.updateByPrimaryKeySelective(sampling);
            resultStr ="-1";
        } else {
            String ruleMergeId = (String) margeItem.get("ruleMergeId");
            String sampleCodes = (String) margeItem.get("sampleCodes");
            String carnums = (String) margeItem.get("carnums");
            String margeCode = (String) margeItem.get("mergeCode");
            String sender = (String) margeItem.get("sender");
            String type = (String) margeItem.get("type");
            //修改采样码状态
            samplingMapper.updateMargeStateByMargeCode(margeCode);
            //添加到化验表
            insertAssay(department, userId, ruleMergeId, sampleCodes, carnums, margeCode, sender, type);
            resultStr = margeCode;
        }
        refreshSampleOperateData(userId, department,  HINT_TONE.SaoMaSuccess);
        return resultStr;
    }

    private void insertAssay(String department, String userId, String ruleMergeId, String sampleCodes, String carnums, String margeCode, String sender, String type) {
        Assay assay = new Assay();
        assay.setId(UUIDGenerator.getUUID());
        assay.setMargeCode(margeCode);
        assay.setSender(sender);
        assay.setType(type);
        assay.setRuleMergeId(ruleMergeId);
        assay.setDepatmentCode(department);
        assay.setSampleCodes(sampleCodes);
        assay.setMargeCarnums(carnums);
        assay.setState(0);
        assay.setCreateTime(new Date());
        assay.setCreateId(userId);
        assayMapper.insertSelective(assay);
    }

    /**
     * 添加采样数据
     *
     * @param tbSampling 数据源
     * @param ruleMerge  合样规则
     * @param sampleCode 采样二维码
     * @param mergeCode  合样二维数字
     * @param department 部门识别码
     * @param createId   创建人id
     * @return 返回插入结果
     */
    private int insertSampling(TbSampling tbSampling, RuleMerge ruleMerge, String sampleCode, String mergeCode, String department, String createId) {
        Sampling sampling = new Sampling();
        sampling.setId(UUIDGenerator.getUUID());
        //采样编码
        sampling.setSampleCode(sampleCode);
        //发卡id
        sampling.setLinkId(tbSampling.getLinkid());
        sampling.setSender(tbSampling.getSupplier());
        sampling.setType(tbSampling.getCoaltype());
        sampling.setCargoname(tbSampling.getCargoname());
        sampling.setCarnum(tbSampling.getCarnum());
        sampling.setDepartmentId(tbSampling.getExt3());
        sampling.setRuleMergeId(ruleMerge.getId());
        sampling.setMergeCode(mergeCode);
        sampling.setMergeRule(ruleMerge.getCount());
        sampling.setCreateId(createId);
        sampling.setCreateTime(new Date());
        sampling.setDepatmentCode(department);
        return samplingMapper.insertSelective(sampling);
    }

    /**
     * 获取合样规则
     *
     * @param tbSampling 合样数据
     * @param createId  创建人id
     */
    private RuleMerge getRuleMerge(TbSampling tbSampling, String createId) {
        RuleMerge ruleMerge = ruleMergeMapper.selectBySupplier(tbSampling.getSupplier(), tbSampling.getCoaltype());
        if (ruleMerge==null){
            ruleMerge = new RuleMerge();
            ruleMerge.setCount(Integer.parseInt(ProperUtils.getProperty("ruleMergeCount", "3")));
            ruleMerge.setId(UUIDGenerator.getUUID());
            ruleMerge.setCreateId(createId);
            ruleMerge.setCreateTime(new Date());
            ruleMerge.setSender(tbSampling.getSupplier());
            ruleMerge.setType(tbSampling.getCoaltype());
            ruleMergeMapper.insertSelective(ruleMerge);
        }
        return ruleMerge;
    }


    /**
     * 更新采样合样操作数据
     *
     * @param userId  接收信息的用户
     * @param department  //厂区
     * @param hintTone 信息状态
     */
    private void refreshSampleOperateData(String userId, String department, HINT_TONE hintTone) {
        List<Map<String, Object>> sampleOperateDatas = samplingMapper.getSampleOperateData(department);
        Map<String, Object> retMap = new HashMap<>(16);
        retMap.put("hintTone", hintTone);
        retMap.put("data", sampleOperateDatas);
        String toJSONString = JSON.toJSONString(retMap);
        springWebSocketHandler.sendMessageToUser(userId, new TextMessage(toJSONString));
    }
}
