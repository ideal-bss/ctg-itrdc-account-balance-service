package com.ctg.itrdc.account.balance.entity;
/**
 * BalShareRuleEntity
 * 余额共享规则 BALANCE_SHARE_RULE.
 * @author 
 * @version 1.0
 * @time: 
 */
public class BalShareRuleEntity {
    /**
     * id
     * 数据库自增主键.
     */
	private long id;
	/**
     * shareRuleId
     * 共享规则标识.
     */
	private long shareRuleId;
	/**
     * acctBalanceId
     * 余额帐本标识.
     */
	private long acctBalanceId;
	/**
     * acctId
     * 帐户标识.
     */
	private long acctId;
	/**
     * shareRuleTypeId
     * 共享规则类型标识.
     */
	private long shareRuleTypeId;
	/**
     * objectType
     * 余额对象类型.
     */
	private int objectType;
	/**
     * objectId
     * 余额对象标识.
     */
	private long objectId;
	/**
     * shareRuleTypePri
     * 共享规则优先级.
     */
	private int shareRuleTypePri;
	/**
     * upperAmount
     * 扣费上限金额.
     */
	private long upperAmount;
	/**
     * lowerAmount
     * 扣费下限金额.
     */
	private long lowerAmount;
	/**
     * effDate
     * 生效时间.
     */
	private String effDate;
	/**
     * expDate
     * 失效时间.
     */
	private String expDate;
	/**
     * sliceKey
     * 分片关键字.
     */
	private long sliceKey;
	/**
     * state
     * 状态.
     */
	private int state;
	/**
     * createDate
     * 创建时间.
     */
	private String createDate;
	/**
     * updateDate
     * 更新时间.
     */
	private String updateDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getShareRuleId() {
		return shareRuleId;
	}
	public void setShareRuleId(long shareRuleId) {
		this.shareRuleId = shareRuleId;
	}
	public long getAcctBalanceId() {
		return acctBalanceId;
	}
	public void setAcctBalanceId(long acctBalanceId) {
		this.acctBalanceId = acctBalanceId;
	}
	public long getAcctId() {
		return acctId;
	}
	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}
	public long getShareRuleTypeId() {
		return shareRuleTypeId;
	}
	public void setShareRuleTypeId(long shareRuleTypeId) {
		this.shareRuleTypeId = shareRuleTypeId;
	}
	public int getObjectType() {
		return objectType;
	}
	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}
	public long getObjectId() {
		return objectId;
	}
	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}
	public int getShareRuleTypePri() {
		return shareRuleTypePri;
	}
	public void setShareRuleTypePri(int shareRuleTypePri) {
		this.shareRuleTypePri = shareRuleTypePri;
	}
	public long getUpperAmount() {
		return upperAmount;
	}
	public void setUpperAmount(long upperAmount) {
		this.upperAmount = upperAmount;
	}
	public long getLowerAmount() {
		return lowerAmount;
	}
	public void setLowerAmount(long lowerAmount) {
		this.lowerAmount = lowerAmount;
	}
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public long getSliceKey() {
		return sliceKey;
	}
	public void setSliceKey(long sliceKey) {
		this.sliceKey = sliceKey;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}
