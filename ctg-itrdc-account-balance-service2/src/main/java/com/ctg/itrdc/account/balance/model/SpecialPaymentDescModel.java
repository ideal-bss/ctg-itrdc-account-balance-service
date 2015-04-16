package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;
import java.util.Date;

public class SpecialPaymentDescModel implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long spePaymentId;

    private String spePaymentDesc;

    private String statusCd;

    private String createStaff;

    private String updateStaff;

    private Date createDate;

    private Date statusDate;

    private Date updateDate;

    public Long getSpePaymentId() {
        return spePaymentId;
    }

    public void setSpePaymentId(Long spePaymentId) {
        this.spePaymentId = spePaymentId;
    }

    public String getSpePaymentDesc() {
        return spePaymentDesc;
    }

    public void setSpePaymentDesc(String spePaymentDesc) {
        this.spePaymentDesc = spePaymentDesc == null ? null : spePaymentDesc.trim();
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}