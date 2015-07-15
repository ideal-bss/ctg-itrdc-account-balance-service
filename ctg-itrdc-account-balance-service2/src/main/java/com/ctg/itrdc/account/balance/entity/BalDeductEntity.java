package com.ctg.itrdc.account.balance.entity;

import java.util.List;

/**
 * 抵扣账本
 * @author 
 *
 */
public class BalDeductEntity {
    
    /**
     * 抵扣账本标识
     */
    private long balanceId;
    /**
     * 抵扣账本类型标识
     */
    private long balanceTypeId;
    /**
     * 抵扣账本金额
     */
    private long amount;
    /**
     * 账务周期标识
     */
    private long billingCycleId;
    /**
     * 抵扣账本剩余金额
     */
    private long curAmount;
    
    /**
     * 抵扣账本来源信息
     */
    private List<BalDeductSourceEntity> sources;
    /**
     * 抵扣产品实例信息
     */
    private List<BalDeductServEntity> servs;
    public long getBalanceId() {
        return balanceId;
    }
    public void setBalanceId(long balanceId) {
        this.balanceId = balanceId;
    }
    public long getBalanceTypeId() {
        return balanceTypeId;
    }
    public void setBalanceTypeId(long balanceTypeId) {
        this.balanceTypeId = balanceTypeId;
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
    public List<BalDeductSourceEntity> getSources() {
        return sources;
    }
    public void setSources(List<BalDeductSourceEntity> sources) {
        this.sources = sources;
    }
    public List<BalDeductServEntity> getServs() {
        return servs;
    }
    public void setServs(List<BalDeductServEntity> servs) {
        this.servs = servs;
    }
    public long getBillingCycleId() {
        return billingCycleId;
    }
    public void setBillingCycleId(long billingCycleId) {
        this.billingCycleId = billingCycleId;
    }
    
}
