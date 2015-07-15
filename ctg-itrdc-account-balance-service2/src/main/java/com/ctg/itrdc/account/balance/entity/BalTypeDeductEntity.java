package com.ctg.itrdc.account.balance.entity;

import java.util.List;

/**
 * 用于抵扣的余额账本类型
 * @author 
 */
public class BalTypeDeductEntity {
	/**
	 * id 数据库自增主键.
	 */
	private long id;
	/**
	 * id 余额类型标识.
	 */
	private long balanceTypeId;
	/**
	 * id 优先级.
	 */
	private int priority;
	/**
	 * id 专款专用标识.
	 */
	private long spePaymentId;
	/**
	 * id 余额类型名称.
	 */
	private String balanceTypeName;
	/**
	 * id 状态.
	 */
	private int state;
	/**
	 * id 创建时间.
	 */
	private String createDate;
	/**
	 * id 更新时间.
	 */
	private String updateDate;
	/**
	 * balItemGroupId 所属账目组标识
	 */
	private long balItemGroupId;
	
	/**
	 * itemGroupMens 账目组包含的账目
	 */
	private List<BalAcctItemGrpMemEntity> itemGroupMems;

	public long getBalItemGroupId() {
		return balItemGroupId;
	}

	public List<BalAcctItemGrpMemEntity> getItemGroupMems() {
        return itemGroupMems;
    }

    public void setItemGroupMems(List<BalAcctItemGrpMemEntity> itemGroupMems) {
        this.itemGroupMems = itemGroupMems;
    }

    public void setBalItemGroupId(long balItemGroupId) {
		this.balItemGroupId = balItemGroupId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBalanceTypeId() {
		return balanceTypeId;
	}

	public void setBalanceTypeId(long balanceTypeId) {
		this.balanceTypeId = balanceTypeId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public long getSpePaymentId() {
		return spePaymentId;
	}

	public void setSpePaymentId(long spePaymentId) {
		this.spePaymentId = spePaymentId;
	}

	public String getBalanceTypeName() {
		return balanceTypeName;
	}

	public void setBalanceTypeName(String balanceTypeName) {
		this.balanceTypeName = balanceTypeName;
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
