package com.ctg.itrdc.account.balance.entity;

import java.util.List;
/**
 * BalAcctBalanceDeductEntity 用于抵扣的余额帐本
 * 
 * @author 
 * @version 1.0
 * @time: 
 */
public class BalAcctBalanceDeductEntity {
	/**
	 * id 数据库自增主键.
	 */
	private long id;
	/**
	 * acctBalanceId 余额帐本标识.
	 */
	private long acctBalanceId;
	/**
	 * balanceTypeId 余额类型标识.
	 */
	private long balanceTypeId;
	/**
	 * acctId 帐户标识.
	 */
	private long acctId;
	/**
	 * effDate 生效时间.
	 */
	private String effDate;
	/**
	 * expDate 生效时间.
	 */
	private String expDate;
	/**
	 * balance 余额.
	 */
	private long balance;
	/**
	 * cycleUpper 扣费上限金额.
	 */
	private long cycleUpper;
	/**
	 * cycleLower 扣费下限金额.
	 */
	private long cycleLower;
	/**
	 * cycleUpperType 扣费上限类型.
	 */
	private int cycleUpperType;
	/**
	 * cycleLowerType 扣费下限类型.
	 */
	private int cycleLowerType;
	/**
	 * state 余额帐本状态.
	 */
	private int state;
	/**
	 * frozenState 余额冻结状态.
	 */
	private int frozenState;
	/**
	 * createDate 创建时间.
	 */
	private String createDate;
	/**
	 * updateDate 修改时间.
	 */
	private String updateDate;
	/**
	 * sliceKey 分片关键字.
	 */
	private long sliceKey;
	/**
	 * balType 余额账本类型
	 */
	private BalTypeDeductEntity balType;
	/**
	 * balShareRules 账本共享规则集
	 */
	private List<BalShareRuleEntity> balShareRules;

	public BalTypeDeductEntity getBalType() {
		return balType;
	}

	public void setBalType(BalTypeDeductEntity balType) {
		this.balType = balType;
	}

	public List<BalShareRuleEntity> getBalShareRules() {
		return balShareRules;
	}

	public void setBalShareRules(List<BalShareRuleEntity> balShareRules) {
		this.balShareRules = balShareRules;
	}

	/**
	 * @return id属性
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            设置id属性
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return acctBalanceId属性
	 */
	public long getAcctBalanceId() {
		return acctBalanceId;
	}

	/**
	 * @param acctBalanceId
	 *            设置acctBalanceId属性
	 */
	public void setAcctBalanceId(long acctBalanceId) {
		this.acctBalanceId = acctBalanceId;
	}

	/**
	 * @return balanceTypeId属性
	 */
	public long getBalanceTypeId() {
		return balanceTypeId;
	}

	/**
	 * @param balanceTypeId
	 *            设置balanceTypeId属性
	 */
	public void setBalanceTypeId(long balanceTypeId) {
		this.balanceTypeId = balanceTypeId;
	}

	/**
	 * @return acctId属性
	 */
	public long getAcctId() {
		return acctId;
	}

	/**
	 * @param acctId
	 *            设置acctId属性
	 */
	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}

	/**
	 * @return effDate属性
	 */
	public String getEffDate() {
		return effDate;
	}

	/**
	 * @param effDate
	 *            设置effDate属性
	 */
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}

	/**
	 * @return expDate属性
	 */
	public String getExpDate() {
		return expDate;
	}

	/**
	 * @param expDate
	 *            设置expDate属性
	 */
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	/**
	 * @return balance属性
	 */
	public long getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            设置balance属性
	 */
	public void setBalance(long balance) {
		this.balance = balance;
	}

	/**
	 * @return cycleUpper属性
	 */
	public long getCycleUpper() {
		return cycleUpper;
	}

	/**
	 * @param cycleUpper
	 *            设置cycleUpper属性
	 */
	public void setCycleUpper(long cycleUpper) {
		this.cycleUpper = cycleUpper;
	}

	/**
	 * @return cycleLower属性
	 */
	public long getCycleLower() {
		return cycleLower;
	}

	/**
	 * @param cycleLower
	 *            设置cycleLower属性
	 */
	public void setCycleLower(long cycleLower) {
		this.cycleLower = cycleLower;
	}

	/**
	 * @return cycleUpperType属性
	 */
	public int getCycleUpperType() {
		return cycleUpperType;
	}

	/**
	 * @param cycleUpperType
	 *            设置cycleUpperType属性
	 */
	public void setCycleUpperType(int cycleUpperType) {
		this.cycleUpperType = cycleUpperType;
	}

	/**
	 * @return cycleLowerType属性
	 */
	public int getCycleLowerType() {
		return cycleLowerType;
	}

	/**
	 * @param cycleLowerType
	 *            设置cycleLowerType属性
	 */
	public void setCycleLowerType(int cycleLowerType) {
		this.cycleLowerType = cycleLowerType;
	}

	/**
	 * @return state属性
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state
	 *            设置state属性
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return frozenState属性
	 */
	public int getFrozenState() {
		return frozenState;
	}

	/**
	 * @param frozenState
	 *            设置frozenState属性
	 */
	public void setFrozenState(int frozenState) {
		this.frozenState = frozenState;
	}

	/**
	 * @return createDate属性
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            设置createDate属性
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return updateDate属性
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            设置updateDate属性
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return sliceKey属性
	 */
	public long getSliceKey() {
		return sliceKey;
	}

	/**
	 * @param sliceKey
	 *            设置sliceKey属性
	 */
	public void setSliceKey(long sliceKey) {
		this.sliceKey = sliceKey;
	}

}
