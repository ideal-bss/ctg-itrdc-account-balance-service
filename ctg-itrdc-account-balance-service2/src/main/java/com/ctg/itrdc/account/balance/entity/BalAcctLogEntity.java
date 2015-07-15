package com.ctg.itrdc.account.balance.entity;
/**
 * BalAcctEntity
 * 余额帐本日志 ACCT_BALANCE_LOG.
 * @author 
 * @version 1.0
 * @time: 
 */
public class BalAcctLogEntity {
    /**
     * id
     * 数据库自增主键.
     */
	private long id;
	/**
     * balLogId
     * 余额帐本日志标识.
     */
	private long balLogId;
	/**
     * acctBalId
     * 余额帐本标识.
     */
	private long acctBalId;
	/**
     * acctId
     * 支出帐户标识.
     */
	private long acctId;
	/**
     * operIncomeId
     * 来源操作流水.
     */
	private long operIncomeId;
	/**
     * srcAmount
     * 余额来源原金额.
     */
	private long srcAmount;
	/**
     * operPayId
     * 支出操作流水.
     */
	private long operPayId;
	/**
     * payAmount
     * 支出金额.
     */
	private long payAmount;
	/**
     * createDate
     * 创建时间.
     */
	private String createDate;
	/**
     * sliceKey
     * 分片关键字.
     */
	private long sliceKey;
    /**
     * @return id属性
     */
    public long getId() {
        return id;
    }
    /**
     * @param id 设置id属性
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * @return balLogId属性
     */
    public long getBalLogId() {
        return balLogId;
    }
    /**
     * @param balLogId 设置balLogId属性
     */
    public void setBalLogId(long balLogId) {
        this.balLogId = balLogId;
    }
    /**
     * @return acctBalId属性
     */
    public long getAcctBalId() {
        return acctBalId;
    }
    /**
     * @param acctBalId 设置acctBalId属性
     */
    public void setAcctBalId(long acctBalId) {
        this.acctBalId = acctBalId;
    }
    /**
     * @return acctId属性
     */
    public long getAcctId() {
        return acctId;
    }
    /**
     * @param acctId 设置acctId属性
     */
    public void setAcctId(long acctId) {
        this.acctId = acctId;
    }
    /**
     * @return operIncomeId属性
     */
    public long getOperIncomeId() {
        return operIncomeId;
    }
    /**
     * @param operIncomeId 设置operIncomeId属性
     */
    public void setOperIncomeId(long operIncomeId) {
        this.operIncomeId = operIncomeId;
    }
    /**
     * @return srcAmount属性
     */
    public long getSrcAmount() {
        return srcAmount;
    }
    /**
     * @param srcAmount 设置srcAmount属性
     */
    public void setSrcAmount(long srcAmount) {
        this.srcAmount = srcAmount;
    }
    /**
     * @return operPayId属性
     */
    public long getOperPayId() {
        return operPayId;
    }
    /**
     * @param operPayId 设置operPayId属性
     */
    public void setOperPayId(long operPayId) {
        this.operPayId = operPayId;
    }
    /**
     * @return payAmount属性
     */
    public long getPayAmount() {
        return payAmount;
    }
    /**
     * @param payAmount 设置payAmount属性
     */
    public void setPayAmount(long payAmount) {
        this.payAmount = payAmount;
    }
    /**
     * @return createDate属性
     */
    public String getCreateDate() {
        return createDate;
    }
    /**
     * @param createDate 设置createDate属性
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    /**
     * @return sliceKey属性
     */
    public long getSliceKey() {
        return sliceKey;
    }
    /**
     * @param sliceKey 设置sliceKey属性
     */
    public void setSliceKey(long sliceKey) {
        this.sliceKey = sliceKey;
    }
}
