package com.luan.model;

import java.io.Serializable;
import java.util.Date;

public class Assay implements Serializable {
    /** id*/
    private String id;

    /** 合样编码*/
    private String margeCode;

    /** 来源地*/
    private String sender;

    /** 来源分类*/
    private String type;

    /** 合样规则id*/
    private String ruleMergeId;

    /** 厂区识别码*/
    private String depatmentCode;

    /** 所有采样编码*/
    private String sampleCodes;

    /** 合样车号*/
    private String margeCarnums;

    /** 状态 0：未录入，1：带审核，2：审核通过，3：审核未通过*/
    private Integer state;

    /** 模板id*/
    private String templateId;

    /** 模板名称*/
    private String templateName;

    /** 创建时间*/
    private Date createTime;

    /** 创建人*/
    private String createName;

    /** 创建人id*/
    private String createId;

    /** 添加数据时间*/
    private Date addTime;

    /** 添加数据人*/
    private String addName;

    /** 添加数据人id*/
    private String addId;

    /** 审核人*/
    private String confirmId;

    /** 审核时间*/
    private Date confirmTime;

    /** 审核人*/
    private String confirmName;

    /** 备注*/
    private String note;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMargeCode() {
        return margeCode;
    }

    public void setMargeCode(String margeCode) {
        this.margeCode = margeCode == null ? null : margeCode.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRuleMergeId() {
        return ruleMergeId;
    }

    public void setRuleMergeId(String ruleMergeId) {
        this.ruleMergeId = ruleMergeId == null ? null : ruleMergeId.trim();
    }

    public String getDepatmentCode() {
        return depatmentCode;
    }

    public void setDepatmentCode(String depatmentCode) {
        this.depatmentCode = depatmentCode == null ? null : depatmentCode.trim();
    }

    public String getSampleCodes() {
        return sampleCodes;
    }

    public void setSampleCodes(String sampleCodes) {
        this.sampleCodes = sampleCodes == null ? null : sampleCodes.trim();
    }

    public String getMargeCarnums() {
        return margeCarnums;
    }

    public void setMargeCarnums(String margeCarnums) {
        this.margeCarnums = margeCarnums == null ? null : margeCarnums.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName == null ? null : addName.trim();
    }

    public String getAddId() {
        return addId;
    }

    public void setAddId(String addId) {
        this.addId = addId == null ? null : addId.trim();
    }

    public String getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(String confirmId) {
        this.confirmId = confirmId == null ? null : confirmId.trim();
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getConfirmName() {
        return confirmName;
    }

    public void setConfirmName(String confirmName) {
        this.confirmName = confirmName == null ? null : confirmName.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", margeCode=").append(margeCode);
        sb.append(", sender=").append(sender);
        sb.append(", type=").append(type);
        sb.append(", ruleMergeId=").append(ruleMergeId);
        sb.append(", depatmentCode=").append(depatmentCode);
        sb.append(", sampleCodes=").append(sampleCodes);
        sb.append(", margeCarnums=").append(margeCarnums);
        sb.append(", state=").append(state);
        sb.append(", templateId=").append(templateId);
        sb.append(", templateName=").append(templateName);
        sb.append(", createTime=").append(createTime);
        sb.append(", createName=").append(createName);
        sb.append(", createId=").append(createId);
        sb.append(", addTime=").append(addTime);
        sb.append(", addName=").append(addName);
        sb.append(", addId=").append(addId);
        sb.append(", confirmId=").append(confirmId);
        sb.append(", confirmTime=").append(confirmTime);
        sb.append(", confirmName=").append(confirmName);
        sb.append(", note=").append(note);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}