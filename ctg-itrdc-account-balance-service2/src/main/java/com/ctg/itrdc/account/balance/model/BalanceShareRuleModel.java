package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;
import java.util.Date;

public class BalanceShareRuleModel implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long shareRuleId;

    private Long acctBalanceId;

    private Long shareRuleTypeId;

    private String objectType;

    private Long objectId;

    private Long shareRuleTypePri;

    private Long upperAmount;

    private Long lowerAmount;

    private Date effDate;

    private Date expDate;

    private String statusCd;

    private String createStaff;

    private String updateStaff;

    private Date createDate;

    private Date statusDate;

    private Date updateDate;

    private Long sliceKey;
    
    /*private Long acctId;
    
    

    public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}
*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getShareRuleId() {
        return shareRuleId;
    }

    public void setShareRuleId(Long shareRuleId) {
        this.shareRuleId = shareRuleId;
    }

    public Long getAcctBalanceId() {
        return acctBalanceId;
    }

    public void setAcctBalanceId(Long acctBalanceId) {
        this.acctBalanceId = acctBalanceId;
    }

    public Long getShareRuleTypeId() {
        return shareRuleTypeId;
    }

    public void setShareRuleTypeId(Long shareRuleTypeId) {
        this.shareRuleTypeId = shareRuleTypeId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType == null ? null : objectType.trim();
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getShareRuleTypePri() {
        return shareRuleTypePri;
    }

    public void setShareRuleTypePri(Long shareRuleTypePri) {
        this.shareRuleTypePri = shareRuleTypePri;
    }

    public Long getUpperAmount() {
        return upperAmount;
    }

    public void setUpperAmount(Long upperAmount) {
        this.upperAmount = upperAmount;
    }

    public Long getLowerAmount() {
        return lowerAmount;
    }

    public void setLowerAmount(Long lowerAmount) {
        this.lowerAmount = lowerAmount;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getSliceKey() {
        return sliceKey;
    }

    public void setSliceKey(Long sliceKey) {
        this.sliceKey = sliceKey;
    }
}