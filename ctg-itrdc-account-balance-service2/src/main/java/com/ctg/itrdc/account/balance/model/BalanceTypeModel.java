package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;
import java.util.Date;

public class BalanceTypeModel implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long balanceTypeId;

    private Long priority;

    private Long spePaymentId;

    private Long measureMethodId;

    private String balanceTypeName;

    private String allowDraw;

    private String invOffer;

    private String ifEarning;

    private String ifPayold;

    private String ifSaveback;

    private String ifPrincipal;

    private String statusCd;

    private String createStaff;

    private String updateStaff;

    private Date createDate;

    private Date statusDate;

    private Date updateDate;

    public Long getBalanceTypeId() {
        return balanceTypeId;
    }

    public void setBalanceTypeId(Long balanceTypeId) {
        this.balanceTypeId = balanceTypeId;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Long getSpePaymentId() {
        return spePaymentId;
    }

    public void setSpePaymentId(Long spePaymentId) {
        this.spePaymentId = spePaymentId;
    }

    public Long getMeasureMethodId() {
        return measureMethodId;
    }

    public void setMeasureMethodId(Long measureMethodId) {
        this.measureMethodId = measureMethodId;
    }

    public String getBalanceTypeName() {
        return balanceTypeName;
    }

    public void setBalanceTypeName(String balanceTypeName) {
        this.balanceTypeName = balanceTypeName == null ? null : balanceTypeName.trim();
    }

    public String getAllowDraw() {
        return allowDraw;
    }

    public void setAllowDraw(String allowDraw) {
        this.allowDraw = allowDraw == null ? null : allowDraw.trim();
    }

    public String getInvOffer() {
        return invOffer;
    }

    public void setInvOffer(String invOffer) {
        this.invOffer = invOffer == null ? null : invOffer.trim();
    }

    public String getIfEarning() {
        return ifEarning;
    }

    public void setIfEarning(String ifEarning) {
        this.ifEarning = ifEarning == null ? null : ifEarning.trim();
    }

    public String getIfPayold() {
        return ifPayold;
    }

    public void setIfPayold(String ifPayold) {
        this.ifPayold = ifPayold == null ? null : ifPayold.trim();
    }

    public String getIfSaveback() {
        return ifSaveback;
    }

    public void setIfSaveback(String ifSaveback) {
        this.ifSaveback = ifSaveback == null ? null : ifSaveback.trim();
    }

    public String getIfPrincipal() {
        return ifPrincipal;
    }

    public void setIfPrincipal(String ifPrincipal) {
        this.ifPrincipal = ifPrincipal == null ? null : ifPrincipal.trim();
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