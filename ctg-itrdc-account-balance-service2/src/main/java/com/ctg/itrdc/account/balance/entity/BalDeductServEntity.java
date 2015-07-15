package com.ctg.itrdc.account.balance.entity;

import java.util.List;


public class BalDeductServEntity {
    
    /**
     * 抵扣产品实例标识
     */
    private long servId;
    /**
     * 抵扣金额
     */
    private long amount;
    /**
     * 抵扣剩余金额
     */
    private long curAmount;
    /**
     * 抵扣号码
     */
    private String nbr;
    
    /**
     * 抵扣账目信息
     */
    private List<BalDeductItemEntity> items;
    
    public long getServId() {
        return servId;
    }
    public void setServId(long servId) {
        this.servId = servId;
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
    public String getNbr() {
        return nbr;
    }
    public void setNbr(String nbr) {
        this.nbr = nbr;
    }
    public List<BalDeductItemEntity> getItems() {
        return items;
    }
    public void setItems(List<BalDeductItemEntity> items) {
        this.items = items;
    }
}
