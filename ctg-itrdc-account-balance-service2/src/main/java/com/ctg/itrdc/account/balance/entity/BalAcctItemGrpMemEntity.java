package com.ctg.itrdc.account.balance.entity;
/**
 * BalAcctItemGrpMemEntity
 * ...........
 * @author 
 * @version 1.0
 * @time: 
 */
public class BalAcctItemGrpMemEntity {
    /**
     * id
     * 数据库自增主键.
     */
	private long id;
    /**
     * acctItemTypeId
     * 帐目类型标识.
     */
	private long acctItemTypeId;
	/**
     * itemSourceId
     * 帐目来源标识.
     */
	private long itemSourceId;
	/**
     * acctItemGroupId
     * 帐目组标识.
     */
	private long acctItemGroupId;
	/**
     * stateDate
     * 状态时间.
     */
	private String stateDate;
	/**
     * state
     * 状态.
     */
	private String state;
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
     * @return itemSourceId属性
     */
    public long getItemSourceId() {
        return itemSourceId;
    }
    /**
     * @param itemSourceId 设置itemSourceId属性
     */
    public void setItemSourceId(long itemSourceId) {
        this.itemSourceId = itemSourceId;
    }
    /**
     * @return acctItemGroupId属性
     */
    public long getAcctItemGroupId() {
        return acctItemGroupId;
    }
    /**
     * @param acctItemGroupId 设置acctItemGroupId属性
     */
    public void setAcctItemGroupId(long acctItemGroupId) {
        this.acctItemGroupId = acctItemGroupId;
    }
    /**
     * @return stateDate属性
     */
    public String getStateDate() {
        return stateDate;
    }
    /**
     * @param stateDate 设置stateDate属性
     */
    public void setStateDate(String stateDate) {
        this.stateDate = stateDate;
    }
    /**
     * @return state属性
     */
    public String getState() {
        return state;
    }
    /**
     * @param state 设置state属性
     */
    public void setState(String state) {
        this.state = state;
    }

}
