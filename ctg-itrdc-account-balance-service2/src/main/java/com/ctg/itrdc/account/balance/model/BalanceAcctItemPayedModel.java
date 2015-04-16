package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;
import java.util.Date;

public class BalanceAcctItemPayedModel implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long operPayoutId;

    private Long acctItemId;

    private Long balance;

    private String statusCd;

    private Date statusDate;

    private Long sliceKey;

    public Long getOperPayoutId() {
        return operPayoutId;
    }

    public void setOperPayoutId(Long operPayoutId) {
        this.operPayoutId = operPayoutId;
    }

    public Long getAcctItemId() {
        return acctItemId;
    }

    public void setAcctItemId(Long acctItemId) {
        this.acctItemId = acctItemId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
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