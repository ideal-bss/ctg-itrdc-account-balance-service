package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 
 * @author ls
 * @date:2015-6-10
 * @version:
 */
public class BalancePayoutModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private long operPayoutId;
	
	private long billId;
	
	private String operType;
	
	private String staffId;
	
	private Date operDate;
	
	private long amount;
	
	private long balance;
	
	private int prnCount;
	
	private long balanceRelationId;
	
	private long acctBalanceId;
	
	private String statusCd;
	
	private Date statusDate;
	
	private long balanceSourceId;
	
	private long extSerialId;
	
	private String payoutDesc;
	
	private long sliceKey;

	public long getOperPayoutId() {
		return operPayoutId;
	}

	public void setOperPayoutId(long operPayoutId) {
		this.operPayoutId = operPayoutId;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Date getOperDate() {
		return operDate;
	}

	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public int getPrnCount() {
		return prnCount;
	}

	public void setPrnCount(int prnCount) {
		this.prnCount = prnCount;
	}

	public long getBalanceRelationId() {
		return balanceRelationId;
	}

	public void setBalanceRelationId(long balanceRelationId) {
		this.balanceRelationId = balanceRelationId;
	}

	public long getAcctBalanceId() {
		return acctBalanceId;
	}

	public void setAcctBalanceId(long acctBalanceId) {
		this.acctBalanceId = acctBalanceId;
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

	public long getBalanceSourceId() {
		return balanceSourceId;
	}

	public void setBalanceSourceId(long balanceSourceId) {
		this.balanceSourceId = balanceSourceId;
	}

	public long getExtSerialId() {
		return extSerialId;
	}

	public void setExtSerialId(long extSerialId) {
		this.extSerialId = extSerialId;
	}

	

	public String getPayoutDesc() {
		return payoutDesc;
	}

	public void setPayoutDesc(String payoutDesc) {
		this.payoutDesc = payoutDesc;
	}

	public long getSliceKey() {
		return sliceKey;
	}

	public void setSliceKey(long sliceKey) {
		this.sliceKey = sliceKey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
