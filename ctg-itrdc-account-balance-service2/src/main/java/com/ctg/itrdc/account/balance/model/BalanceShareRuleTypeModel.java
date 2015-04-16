package com.ctg.itrdc.account.balance.model;

import java.io.Serializable;
import java.util.Date;

public class BalanceShareRuleTypeModel implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long shareRuleTypeId;

    private String shareRuleTypeName;

    private String statusCd;

    private String createStaff;

    private String updateStaff;

    private Date createDate;

    private Date statusDate;

    private Date updateDate;

    public Long getShareRuleTypeId() {
        return shareRuleTypeId;
    }

    public void setShareRuleTypeId(Long shareRuleTypeId) {
        this.shareRuleTypeId = shareRuleTypeId;
    }

    public String getShareRuleTypeName() {
        return shareRuleTypeName;
    }

    public void setShareRuleTypeName(String shareRuleTypeName) {
        this.shareRuleTypeName = shareRuleTypeName == null ? null : shareRuleTypeName.trim();
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
}