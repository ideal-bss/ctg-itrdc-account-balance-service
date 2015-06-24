package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 余额来源类型
 * @author ls
 * @date:2015-6-16
 * @version:
 */
public class BalanceSourceTypeModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long balanceSourceTypeId;
	
	private String balanceSourceTypeDesc;
	
	private String statusCd;
	
	private String createStaff;
	
	private String updateStaff;
	
	private Date createDate;
	
	private Date statusDate;
	
	private Date updateDate;

	public long getBalanceSourceTypeId() {
		return balanceSourceTypeId;
	}

	public void setBalanceSourceTypeId(long balanceSourceTypeId) {
		this.balanceSourceTypeId = balanceSourceTypeId;
	}

	public String getBalanceSourceTypeDesc() {
		return balanceSourceTypeDesc;
	}

	public void setBalanceSourceTypeDesc(String balanceSourceTypeDesc) {
		this.balanceSourceTypeDesc = balanceSourceTypeDesc;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff;
	}

	public String getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(String updateStaff) {
		this.updateStaff = updateStaff;
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
