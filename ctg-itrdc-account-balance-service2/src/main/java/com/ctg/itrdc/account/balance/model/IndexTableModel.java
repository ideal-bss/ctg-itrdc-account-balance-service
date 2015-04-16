package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;

public class IndexTableModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long indexTableId;

    private Long balanceLogId;

    private Long acctBalanceId;

    private Long operPayoutId;

    private Long balanceRelationId;

    private Long balanceReserveId;

    private Long shareRuleId;

    private Long operIncomeId;

    private Long sliceKeyId;

    private Long sliceKey;

	public Long getIndexTableId() {
		return indexTableId;
	}

	public void setIndexTableId(Long indexTableId) {
		this.indexTableId = indexTableId;
	}

	public Long getBalanceLogId() {
		return balanceLogId;
	}

	public void setBalanceLogId(Long balanceLogId) {
		this.balanceLogId = balanceLogId;
	}

	public Long getAcctBalanceId() {
		return acctBalanceId;
	}

	public void setAcctBalanceId(Long acctBalanceId) {
		this.acctBalanceId = acctBalanceId;
	}

	public Long getOperPayoutId() {
		return operPayoutId;
	}

	public void setOperPayoutId(Long operPayoutId) {
		this.operPayoutId = operPayoutId;
	}

	public Long getBalanceRelationId() {
		return balanceRelationId;
	}

	public void setBalanceRelationId(Long balanceRelationId) {
		this.balanceRelationId = balanceRelationId;
	}

	public Long getBalanceReserveId() {
		return balanceReserveId;
	}

	public void setBalanceReserveId(Long balanceReserveId) {
		this.balanceReserveId = balanceReserveId;
	}

	public Long getShareRuleId() {
		return shareRuleId;
	}

	public void setShareRuleId(Long shareRuleId) {
		this.shareRuleId = shareRuleId;
	}

	public Long getOperIncomeId() {
		return operIncomeId;
	}

	public void setOperIncomeId(Long operIncomeId) {
		this.operIncomeId = operIncomeId;
	}

	public Long getSliceKeyId() {
		return sliceKeyId;
	}

	public void setSliceKeyId(Long sliceKeyId) {
		this.sliceKeyId = sliceKeyId;
	}

	public Long getSliceKey() {
		return sliceKey;
	}

	public void setSliceKey(Long sliceKey) {
		this.sliceKey = sliceKey;
	}
    
    
}
