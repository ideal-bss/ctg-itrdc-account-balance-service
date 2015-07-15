package com.ctg.itrdc.account.balance.entity;
/**
 * 抵扣所用的账本来源信息
 * @author 
 *
 */
public class BalDeductSourceEntity {
    
    /**
     * 抵扣操作流水号
     */
    private long operIncomeId;
    /**
     * 抵扣金额
     */
    private long amount;
    /**
     * 抵扣所剩金额
     */
    private long curAmount;
    public long getOperIncomeId() {
        return operIncomeId;
    }
    public void setOperIncomeId(long operIncomeId) {
        this.operIncomeId = operIncomeId;
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
