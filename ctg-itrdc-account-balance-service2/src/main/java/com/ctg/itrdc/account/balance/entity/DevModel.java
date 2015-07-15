package com.ctg.itrdc.account.balance.entity;

import java.io.Serializable;
import java.util.List;

import com.ctg.itrdc.account.balance.model.AcctBalanceModel;


public class DevModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private long objectId;
	private String objectType;//0账户级  1设备级
	private List<AcctBalanceModel> balanceList;
	
	
	public long getObjectId() {
		return objectId;
	}
	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public List<AcctBalanceModel> getBalanceList() {
		return balanceList;
	}
	public void setBalanceList(List<AcctBalanceModel> balanceList) {
		this.balanceList = balanceList;
	}
	
	
}
