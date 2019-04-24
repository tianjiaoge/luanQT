package com.luan.model;

import java.io.Serializable;

public class AssayDetails implements Serializable {
    private String id;

    private String itemName;

    private String standard;

    private String value;

    private String margeCode;

    private String assayId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getMargeCode() {
        return margeCode;
    }

    public void setMargeCode(String margeCode) {
        this.margeCode = margeCode == null ? null : margeCode.trim();
    }

    public String getAssayId() {
        return assayId;
    }

    public void setAssayId(String assayId) {
        this.assayId = assayId == null ? null : assayId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemName=").append(itemName);
        sb.append(", standard=").append(standard);
        sb.append(", value=").append(value);
        sb.append(", margeCode=").append(margeCode);
        sb.append(", assayId=").append(assayId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}