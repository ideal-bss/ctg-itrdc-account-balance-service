package com.ctg.itrdc.account.balance.entity;

import java.io.Serializable;
import java.util.List;

public class AcctModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private long acctId;
	
	private List<DevModel> devModelList;

	public long getAcctId() {
		return acctId;
	}

	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}

	public List<DevModel> getDevModelList() {
		return devModelList;
	}

	public void setDevModelList(List<DevModel> devModelList) {
		this.devModelList = devModelList;
	}

	
	
	
}
