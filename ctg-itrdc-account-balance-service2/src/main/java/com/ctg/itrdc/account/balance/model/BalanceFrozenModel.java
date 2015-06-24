package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 
 * @author ls
 * @date:2015-6-19
 * @version:
 */
public class BalanceFrozenModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long balanceFrozenId;
	
	private long acctBalanceId;
	
	private long acctId;
	
	private long frozenAmount;
	
	private long balanceAmount;
	
	private String frozenState;
	
	private String reason;
	
	private String staffId;
	
	private Date createDate;
	
	private Date updateDate;
	
	private Date effDate;
	
	private Date expDate;
	
	private long sliceKey;
	
	private long servId;

	public long getBalanceFrozenId() {
		return balanceFrozenId;
	}

	public void setBalanceFrozenId(long balanceFrozenId) {
		this.balanceFrozenId = balanceFrozenId;
	}

	public long getAcctBalanceId() {
		return acctBalanceId;
	}

	public void setAcctBalanceId(long acctBalanceId) {
		this.acctBalanceId = acctBalanceId;
	}

	public long getAcctId() {
		return acctId;
	}

	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}

	public long getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(long frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public String getFrozenState() {
		return frozenState;
	}

	public void setFrozenState(String frozenState) {
		this.frozenState = frozenState;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	public long getSliceKey() {
		return sliceKey;
	}

	public void setSliceKey(long sliceKey) {
		this.sliceKey = sliceKey;
	}

	public long getServId() {
		return servId;
	}

	public void setServId(long servId) {
		this.servId = servId;
	}

	public long getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(long balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	
	
}
