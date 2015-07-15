package com.ctg.itrdc.account.balance.entity;
/**
 * 抵扣账本明细
 * @author 
 *
 */
public class BalDeductInfo {
    
    /**
     * 余额账本标识
     */
    private long balanceId;
    /**
     * 余额账本类型标识
     */
    private long balanceTypeId;
    /**
     * 共享规则标识
     */
    private long shareRuleId;
    /**
     * 需要抵扣的金额
     */
    private long amount;
    /**
     * 已抵扣的金额
     */
    private long deductAmount;
    /**
     * 账本原始金额
     */
    private long oldAmount;
    /**
     * 产品实例标识
     */
    private long servId;
    /**
     * 号码
     */
    private String servNbr;
    /**
     * 账目标识
     */
    private long itemId;
    /**
     * 账目类型标识
     */
    private long itemType;
    /**
     * 账目来源标识
     */
    private long itemSourceId;
    /**
     * 账目周期标识
     */
    private long billCycleId;
    
    public long getBillCycleId() {
        return billCycleId;
    }
    public void setBillCycleId(long billCycleId) {
        this.billCycleId = billCycleId;
    }
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
    public long getShareRuleId() {
        return shareRuleId;
    }
    public void setShareRuleId(long shareRuleId) {
        this.shareRuleId = shareRuleId;
    }
    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
    public long getDeductAmount() {
        return deductAmount;
    }
    public void setDeductAmount(long deductAmount) {
        this.deductAmount = deductAmount;
    }
    public long getServId() {
        return servId;
    }
    public void setServId(long servId) {
        this.servId = servId;
    }
    public long getItemId() {
        return itemId;
    }
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
    public long getItemType() {
        return itemType;
    }
    public void setItemType(long itemType) {
        this.itemType = itemType;
    }
    public long getItemSourceId() {
        return itemSourceId;
    }
    public void setItemSourceId(long itemSourceId) {
        this.itemSourceId = itemSourceId;
    }
    public long getOldAmount() {
        return oldAmount;
    }
    public void setOldAmount(long oldAmount) {
        this.oldAmount = oldAmount;
    }
    public String getServNbr() {
        return servNbr;
    }
    public void setServNbr(String servNbr) {
        this.servNbr = servNbr;
    }
}
