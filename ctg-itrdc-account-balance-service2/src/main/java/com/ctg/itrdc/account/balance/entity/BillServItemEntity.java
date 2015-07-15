package com.ctg.itrdc.account.balance.entity;

import java.util.List;

/**
 * BillServItemEntity
 * 销账余额抵扣.
 * @throws 请求产品实例参数
 * @author 
 * @version 1.0
 * @time: 
 */
public class BillServItemEntity {
    /**
     * servId
     * 产品实例ID.
     */
	private long servId;
	/**
     * acctNbr
     * 接入号码.
     */
	private String acctNbr;
	/**
     * acctItemGrp
     * 帐目欠费明细组.
     */
	private List<BillItemEntity> acctItemGrp;
    /**
     * @return servId属性
     */
    public long getServId() {
        return servId;
    }
    /**
     * @param servId 设置servId属性
     */
    public void setServId(long servId) {
        this.servId = servId;
    }
    /**
     * @return acctNbr属性
     */
    public String getAcctNbr() {
        return acctNbr;
    }
    /**
     * @param acctNbr 设置acctNbr属性
     */
    public void setAcctNbr(String acctNbr) {
        this.acctNbr = acctNbr;
    }
    /**
     * @return acctItemGrp属性
     */
    public List<BillItemEntity> getAcctItemGrp() {
        return acctItemGrp;
    }
    /**
     * @param acctItemGrp 设置acctItemGrp属性
     */
    public void setAcctItemGrp(List<BillItemEntity> acctItemGrp) {
        this.acctItemGrp = acctItemGrp;
    }

}
