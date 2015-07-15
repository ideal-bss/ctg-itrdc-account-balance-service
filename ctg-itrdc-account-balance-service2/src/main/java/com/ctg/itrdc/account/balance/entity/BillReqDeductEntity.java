package com.ctg.itrdc.account.balance.entity;

import java.util.List;
/**
 * BillReqDeductDto
 * 销账余额抵扣.
 * @throws 请求帐户参数
 * @author 
 * @version 1.0
 * @time: 
 */
public class BillReqDeductEntity {
    /**
     * acctId
     * 帐户标识.
     */
	private long acctId;
	/**
     * loweCharge
     * 欠费总额.
     */
	private long loweCharge;
	/**
     * billingCycleId
     * 帐务周期.
     */
    private long billingCycleId;
    /**
     * debtsInfo
     * 产品实例欠费明细组.
     */
    private List<BillServItemEntity> debtsInfo;
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
     * @return loweCharge属性
     */
    public long getLoweCharge() {
        return loweCharge;
    }
    /**
     * @param loweCharge 设置loweCharge属性
     */
    public void setLoweCharge(long loweCharge) {
        this.loweCharge = loweCharge;
    }
    /**
     * @return billingCycleId属性
     */
    public long getBillingCycleId() {
        return billingCycleId;
    }
    /**
     * @param billingCycleId 设置billingCycleId属性
     */
    public void setBillingCycleId(long billingCycleId) {
        this.billingCycleId = billingCycleId;
    }
    /**
     * @return debtsInfo属性
     */
    public List<BillServItemEntity> getDebtsInfo() {
        return debtsInfo;
    }
    /**
     * @param debtsInfo 设置debtsInfo属性
     */
    public void setDebtsInfo(List<BillServItemEntity> debtsInfo) {
        this.debtsInfo = debtsInfo;
    }

}
