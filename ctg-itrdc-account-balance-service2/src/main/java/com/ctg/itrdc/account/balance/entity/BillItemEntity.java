package com.ctg.itrdc.account.balance.entity;
/**
 * BillItemEntity
 * 销账余额存入.
 * @throws 请求帐目参数
 * @author 
 * @version 1.0
 * @time: 
 */
public class BillItemEntity {
    /**
     * amount
     * 费用.
     */
	private long amount;
	/**
     * acctItemId
     * 帐目标识.
     */
	private long acctItemId;
	/**
     * acctItemTypeId
     * 帐目类型标识.
     */
	private long acctItemTypeId;
	/**
     * acctItemSourceId
     * 帐目来源标识.
     */
	private long acctItemSourceId;
    /**
     * @return amount属性
     */
    public long getAmount() {
        return amount;
    }
    /**
     * @param amount 设置amount属性
     */
    public void setAmount(long amount) {
        this.amount = amount;
    }
    /**
     * @return acctItemId属性
     */
    public long getAcctItemId() {
        return acctItemId;
    }
    /**
     * @param acctItemId 设置acctItemId属性
     */
    public void setAcctItemId(long acctItemId) {
        this.acctItemId = acctItemId;
    }
    /**
     * @return acctItemTypeId属性
     */
    public long getAcctItemTypeId() {
        return acctItemTypeId;
    }
    /**
     * @param acctItemTypeId 设置acctItemTypeId属性
     */
    public void setAcctItemTypeId(long acctItemTypeId) {
        this.acctItemTypeId = acctItemTypeId;
    }
    /**
     * @return acctItemSourceId属性
     */
    public long getAcctItemSourceId() {
        return acctItemSourceId;
    }
    /**
     * @param acctItemSourceId 设置acctItemSourceId属性
     */
    public void setAcctItemSourceId(long acctItemSourceId) {
        this.acctItemSourceId = acctItemSourceId;
    }

}
