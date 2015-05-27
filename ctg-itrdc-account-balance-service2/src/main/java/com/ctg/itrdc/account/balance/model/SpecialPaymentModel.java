package com.ctg.itrdc.account.balance.model;

import java.util.Date;

public class SpecialPaymentModel {
    private Long spePaymentId;

    private Long partnerId;

    private Long productId;

    private Long acctItemGroupId;

    private String paymentType;

    private String statusCd;

    private String createStaff;

    private String updateStaff;

    private Date createDate;

    private Date statusDate;

    private Date updateDate;
    
    /**
     * 专款专用描述
     */
    private SpecialPaymentDescModel specialPaymentDescModel;
    
    

    public SpecialPaymentDescModel getSpecialPaymentDescModel() {
		return specialPaymentDescModel;
	}

	public void setSpecialPaymentDescModel(
			SpecialPaymentDescModel specialPaymentDescModel) {
		this.specialPaymentDescModel = specialPaymentDescModel;
	}

	public Long getSpePaymentId() {
        return spePaymentId;
    }

    public void setSpePaymentId(Long spePaymentId) {
        this.spePaymentId = spePaymentId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAcctItemGroupId() {
        return acctItemGroupId;
    }

    public void setAcctItemGroupId(Long acctItemGroupId) {
        this.acctItemGroupId = acctItemGroupId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
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