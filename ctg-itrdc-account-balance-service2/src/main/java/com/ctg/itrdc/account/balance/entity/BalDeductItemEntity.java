package com.ctg.itrdc.account.balance.entity;

/**
 * 抵扣用到的账目信息
 * @author 
 *
 */
public class BalDeductItemEntity {
    
    private long itemId;
    private long itemTypeId;
    private long sourceId;
    private long amount;
    private long curAmount;
    public long getItemId() {
        return itemId;
    }
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
    public long getItemTypeId() {
        return itemTypeId;
    }
    public void setItemTypeId(long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }
    public long getSourceId() {
        return sourceId;
    }
    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
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
