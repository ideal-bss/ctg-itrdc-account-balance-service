package com.ctg.itrdc.account.balance.entity;

import java.util.List;

/**
 * 抵扣用的帐户信息
 * @author 
 *
 */
public class BalDeductAcctEntity {
    
    /**
     * 抵扣账户标识
     */
    private long acctId;
    /**
     * 抵扣金额
     */
    private long amount;
    /**
     * 抵扣剩余金额
     */
    private long curAmount;
    /**
     * 分片标识
     */
    private long sliceKey;
    
    /**
     * 抵扣账本信息
     */
    private List<BalDeductEntity> balances;
    
    public long getSliceKey() {
        return sliceKey;
    }
    
    public void setSliceKey(long sliceKey) {
        this.sliceKey = sliceKey;
    }
    public List<BalDeductEntity> getBalances() {
        return balances;
    }
    
    public void setBalances(List<BalDeductEntity> balances) {
        this.balances = balances;
    }
    
    public long getAcctId() {
        return acctId;
    }
    
    public void setAcctId(long acctId) {
        this.acctId = acctId;
    }
    
    public long getAmount() {
        return amount;
    }
    
    public void setAmount(long amount) {
        this.amount = amount;
    }
    
    public long getCurAmount() {
        return curAmount;
    }
    
    public void setCurAmount(long curAmount) {
        this.curAmount = curAmount;
    }
    
}
