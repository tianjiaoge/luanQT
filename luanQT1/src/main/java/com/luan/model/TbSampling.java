package com.luan.model;

import java.io.Serializable;
import java.util.Date;

public class TbSampling implements Serializable {
    private String id;

    private String billno;

    private Date time;

    private String supplier;

    private String coaltype;

    private String carnum;

    private String identifying;

    private String biller;

    private String auditor;

    private String note;

    private Date createtime;

    private Integer state;

    private Integer heyangstate;

    private String ext3;

    private String ext4;

    private Date checktime;

    private String cardid;

    private String cargoname;

    private String recevier;

    private String linkid;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno == null ? null : billno.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getCoaltype() {
        return coaltype;
    }

    public void setCoaltype(String coaltype) {
        this.coaltype = coaltype == null ? null : coaltype.trim();
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum == null ? null : carnum.trim();
    }

    public String getIdentifying() {
        return identifying;
    }

    public void setIdentifying(String identifying) {
        this.identifying = identifying == null ? null : identifying.trim();
    }

    public String getBiller() {
        return biller;
    }

    public void setBiller(String biller) {
        this.biller = biller == null ? null : biller.trim();
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getHeyangstate() {
        return heyangstate;
    }

    public void setHeyangstate(Integer heyangstate) {
        this.heyangstate = heyangstate;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    public String getCargoname() {
        return cargoname;
    }

    public void setCargoname(String cargoname) {
        this.cargoname = cargoname == null ? null : cargoname.trim();
    }

    public String getRecevier() {
        return recevier;
    }

    public void setRecevier(String recevier) {
        this.recevier = recevier == null ? null : recevier.trim();
    }

    public String getLinkid() {
        return linkid;
    }

    public void setLinkid(String linkid) {
        this.linkid = linkid == null ? null : linkid.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", billno=").append(billno);
        sb.append(", time=").append(time);
        sb.append(", supplier=").append(supplier);
        sb.append(", coaltype=").append(coaltype);
        sb.append(", carnum=").append(carnum);
        sb.append(", identifying=").append(identifying);
        sb.append(", biller=").append(biller);
        sb.append(", auditor=").append(auditor);
        sb.append(", note=").append(note);
        sb.append(", createtime=").append(createtime);
        sb.append(", state=").append(state);
        sb.append(", heyangstate=").append(heyangstate);
        sb.append(", ext3=").append(ext3);
        sb.append(", ext4=").append(ext4);
        sb.append(", checktime=").append(checktime);
        sb.append(", cardid=").append(cardid);
        sb.append(", cargoname=").append(cargoname);
        sb.append(", recevier=").append(recevier);
        sb.append(", linkid=").append(linkid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}