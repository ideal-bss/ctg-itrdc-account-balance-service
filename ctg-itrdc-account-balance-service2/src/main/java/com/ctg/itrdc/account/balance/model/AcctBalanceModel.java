package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;
import java.util.Date;

public class AcctBalanceModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long acctBalanceId;

    private Long balanceTypeId;

    private Long paymentRuleId;

    private Long subAcctId;

    private Long acctId;

    private Date effDate;

    private Date expDate;

    private Long balance;

    private Long reserveBalance;

    private Long cycleUpper;

    private Long cycleLower;

    private Date createDate;

    private String statusCd;

    private Date statusDate;

    private String cycleType;

    private String remark;

    private Long needInvoiceAmount;

    private Long sliceKey;

	public Long getAcctBalanceId() {
		return acctBalanceId;
	}

	public void setAcctBalanceId(Long acctBalanceId) {
		this.acctBalanceId = acctBalanceId;
	}

	public Long getBalanceTypeId() {
		return balanceTypeId;
	}

	public void setBalanceTypeId(Long balanceTypeId) {
		this.balanceTypeId = balanceTypeId;
	}

	public Long getPaymentRuleId() {
		return paymentRuleId;
	}

	public void setPaymentRuleId(Long paymentRuleId) {
		this.paymentRuleId = paymentRuleId;
	}

	public Long getSubAcctId() {
		return subAcctId;
	}

	public void setSubAcctId(Long subAcctId) {
		this.subAcctId = subAcctId;
	}

	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getReserveBalance() {
		return reserveBalance;
	}

	public void setReserveBalance(Long reserveBalance) {
		this.reserveBalance = reserveBalance;
	}

	public Long getCycleUpper() {
		return cycleUpper;
	}

	public void setCycleUpper(Long cycleUpper) {
		this.cycleUpper = cycleUpper;
	}

	public Long getCycleLower() {
		return cycleLower;
	}

	public void setCycleLower(Long cycleLower) {
		this.cycleLower = cycleLower;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getCycleType() {
		return cycleType;
	}

	public void setCycleType(String cycleType) {
		this.cycleType = cycleType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getNeedInvoiceAmount() {
		return needInvoiceAmount;
	}

	public void setNeedInvoiceAmount(Long needInvoiceAmount) {
		this.needInvoiceAmount = needInvoiceAmount;
	}

	public Long getSliceKey() {
		return sliceKey;
	}

	public void setSliceKey(Long sliceKey) {
		this.sliceKey = sliceKey;
	}
    
    
	
}
