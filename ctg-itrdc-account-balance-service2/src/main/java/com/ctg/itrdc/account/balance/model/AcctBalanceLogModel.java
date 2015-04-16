package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;
import java.util.Date;

public class AcctBalanceLogModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private Long balanceLogId;

    private Long acctBalanceId;

    private Long operIncomeId;

    private Long srcAmount;

    private Long operPayoutId;

    private Long payoutAmount;

    private Long billingCycleId;

    private String statusCd;

    private Date statusDate;

    private Long sliceKey;

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

    public Long getOperIncomeId() {
        return operIncomeId;
    }

    public void setOperIncomeId(Long operIncomeId) {
        this.operIncomeId = operIncomeId;
    }

    public Long getSrcAmount() {
        return srcAmount;
    }

    public void setSrcAmount(Long srcAmount) {
        this.srcAmount = srcAmount;
    }

    public Long getOperPayoutId() {
        return operPayoutId;
    }

    public void setOperPayoutId(Long operPayoutId) {
        this.operPayoutId = operPayoutId;
    }

    public Long getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(Long payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public Long getBillingCycleId() {
        return billingCycleId;
    }

    public void setBillingCycleId(Long billingCycleId) {
        this.billingCycleId = billingCycleId;
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

    public Long getSliceKey() {
        return sliceKey;
    }

    public void setSliceKey(Long sliceKey) {
        this.sliceKey = sliceKey;
    }
}