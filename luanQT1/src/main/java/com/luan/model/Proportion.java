package com.luan.model;

import java.io.Serializable;
import java.util.Date;

public class Proportion implements Serializable {
    private String id;

    private String sender;

    private String type;

    private Float value;

    private String depatmentCode;

    private Date proportionTime;

    private Date createTime;

    private String createId;

    private String createName;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getDepatmentCode() {
        return depatmentCode;
    }

    public void setDepatmentCode(String depatmentCode) {
        this.depatmentCode = depatmentCode == null ? null : depatmentCode.trim();
    }

    public Date getProportionTime() {
        return proportionTime;
    }

    public void setProportionTime(Date proportionTime) {
        this.proportionTime = proportionTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sender=").append(sender);
        sb.append(", type=").append(type);
        sb.append(", value=").append(value);
        sb.append(", depatmentCode=").append(depatmentCode);
        sb.append(", proportionTime=").append(proportionTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createId=").append(createId);
        sb.append(", createName=").append(createName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}