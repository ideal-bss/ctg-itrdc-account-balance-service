package com.ctg.itrdc.account.balance.entity;
/**
 * BalPayoutEntity
 * 余额支出记录 BALANCE_PAYOUT.
 * @author
 * @version 1.0
 * @time: 
 */
public class BalPayoutEntity {
    /**
     * id
     * 数据库自增主键.
     */
	private long id;
	/**
     * operPayId
     * 支出操作流水.
     */
	private long operPayId;
	/**
     * acctBalId
     * 余额帐本标识.
     */
	private long acctBalId;
	/**
     * acctId
     * id.
     */
	private long acctId;
	/**
     * billId
     * 销帐流水号.
     */
	private long billId;
	/**
     * paymentId
     * 付款流水.
     */
	private long paymentId;
	/**
     * operType
     * 操作类型.
     */
	private int operType;
	/**
     * staffId
     * 工号.
     */
	private long staffId;
	/**
     * createDate
     * 创建时间.
     */
	private String createDate;
	/**
     * amount
     * 补退金额.
     */
	private long amount;
	/**
     * balance
     * 余额.
     */
	private long balance;
	/**
     * prnCount
     * 打印次数.
     */
	private int prnCount;
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
     * @return billId属性
     */
    public long getBillId() {
        return billId;
    }
    /**
     * @param billId 设置billId属性
     */
    public void setBillId(long billId) {
        this.billId = billId;
    }
    /**
     * @return paymentId属性
     */
    public long getPaymentId() {
        return paymentId;
    }
    /**
     * @param paymentId 设置paymentId属性
     */
    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }
    /**
     * @return operType属性
     */
    public int getOperType() {
        return operType;
    }
    /**
     * @param operType 设置operType属性
     */
    public void setOperType(int operType) {
        this.operType = operType;
    }
    /**
     * @return staffId属性
     */
    public long getStaffId() {
        return staffId;
    }
    /**
     * @param staffId 设置staffId属性
     */
    public void setStaffId(long staffId) {
        this.staffId = staffId;
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
     * @return balance属性
     */
    public long getBalance() {
        return balance;
    }
    /**
     * @param balance 设置balance属性
     */
    public void setBalance(long balance) {
        this.balance = balance;
    }
    /**
     * @return prnCount属性
     */
    public int getPrnCount() {
        return prnCount;
    }
    /**
     * @param prnCount 设置prnCount属性
     */
    public void setPrnCount(int prnCount) {
        this.prnCount = prnCount;
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
