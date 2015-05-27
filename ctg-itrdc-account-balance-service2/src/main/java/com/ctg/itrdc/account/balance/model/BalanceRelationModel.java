package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;

public class BalanceRelationModel implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long balanceRelationId;

    private Long acctBalanceId;

    private String objectType;

    private Long objectId;
    
    private Long sliceKey;

    public Long getBalanceRelationId() {
        return balanceRelationId;
    }

    public void setBalanceRelationId(Long balanceRelationId) {
        this.balanceRelationId = balanceRelationId;
    }

    public Long getAcctBalanceId() {
        return acctBalanceId;
    }

    public void setAcctBalanceId(Long acctBalanceId) {
        this.acctBalanceId = acctBalanceId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType == null ? null : objectType.trim();
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

	public Long getSliceKey() {
		return sliceKey;
	}

	public void setSliceKey(Long sliceKey) {
		this.sliceKey = sliceKey;
	}
    
}
