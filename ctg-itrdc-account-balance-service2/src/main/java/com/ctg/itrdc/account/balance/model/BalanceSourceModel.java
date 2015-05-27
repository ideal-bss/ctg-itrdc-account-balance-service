package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;
import java.util.Date;

public class BalanceSourceModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private Long operIncomeId;

    private Long acctBalanceId;

    private String operType;

    private String staffId;

    private Date operDate;

    private Long amount;

    private Long curAmount;

    private Long balanceRelationId;

    private Long balanceSourceTypeId;

    private Date curStatusDate;

    private String statusCd;

    private Date statusDate;

    private Long paymentId;

    private String sourceDesc;

    private Long invOffer;

    private Long allowDraw;

    private String curStatus;

    private Long sliceKey;

    public Long getOperIncomeId() {
        return operIncomeId;
    }

    public void setOperIncomeId(Long operIncomeId) {
        this.operIncomeId = operIncomeId;
    }

    public Long getAcctBalanceId() {
        return acctBalanceId;
    }

    public void setAcctBalanceId(Long acctBalanceId) {
        this.acctBalanceId = acctBalanceId;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCurAmount() {
        return curAmount;
    }

    public void setCurAmount(Long curAmount) {
        this.curAmount = curAmount;
    }

    public Long getBalanceRelationId() {
        return balanceRelationId;
    }

    public void setBalanceRelationId(Long balanceRelationId) {
        this.balanceRelationId = balanceRelationId;
    }

    public Long getBalanceSourceTypeId() {
        return balanceSourceTypeId;
    }

    public void setBalanceSourceTypeId(Long balanceSourceTypeId) {
        this.balanceSourceTypeId = balanceSourceTypeId;
    }

    public Date getCurStatusDate() {
        return curStatusDate;
    }

    public void setCurStatusDate(Date curStatusDate) {
        this.curStatusDate = curStatusDate;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getSourceDesc() {
        return sourceDesc;
    }

    public void setSourceDesc(String sourceDesc) {
        this.sourceDesc = sourceDesc == null ? null : sourceDesc.trim();
    }

    public Long getInvOffer() {
        return invOffer;
    }

    public void setInvOffer(Long invOffer) {
        this.invOffer = invOffer;
    }

    public Long getAllowDraw() {
        return allowDraw;
    }

    public void setAllowDraw(Long allowDraw) {
        this.allowDraw = allowDraw;
    }

    public String getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus == null ? null : curStatus.trim();
    }

    public Long getSliceKey() {
        return sliceKey;
    }

    public void setSliceKey(Long sliceKey) {
        this.sliceKey = sliceKey;
    }
}
