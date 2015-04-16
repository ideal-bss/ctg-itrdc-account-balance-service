package com.ctg.itrdc.account.balance.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ctg.itrdc.account.balance.model.AcctBalanceModel;
import com.ctg.itrdc.account.balance.service.IAcctBalanceService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 余额账本
 * @author hxj
 *
 */
@Controller
public class AcctBalanceAction extends ActionSupport{
	private String acctBalanceId;
    private String balanceTypeId;
    private String paymentRuleId;
    private String subAcctId;
    private String acctId;
    private String effDate;
    private String expDate;
    private String balance;
    private String reserveBalance;
    private String cycleUpper;
    private String cycleLower;
    private String createDate;
    private String statusCd;
    private String statusDate;
    private String cycleType;
    private String remark;
    private String needInvoiceAmount;
	private IAcctBalanceService iAcctBalanceService;
	/**
	 * 普通余额存入界面展现
	 * @return
	 * @throws Exception
	 */
	public String balanceAddGo()throws Exception{
		return SUCCESS;
	}
	/**
	 * 普通余额存入
	 * @return
	 * @throws Exception
	 */
	public String balanceAdd()throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		AcctBalanceModel acctBalanceModel=new AcctBalanceModel();
		acctBalanceModel.setAcctBalanceId(Long.parseLong(acctBalanceId));
		acctBalanceModel.setBalanceTypeId(Long.parseLong(balanceTypeId));
		acctBalanceModel.setPaymentRuleId(Long.parseLong(paymentRuleId));
		acctBalanceModel.setSubAcctId(Long.parseLong(subAcctId));
		acctBalanceModel.setAcctId(Long.parseLong(acctId));
		acctBalanceModel.setEffDate(sdf.parse(effDate));
		acctBalanceModel.setExpDate(sdf.parse(expDate));
		acctBalanceModel.setBalance(Long.parseLong(balance));
		acctBalanceModel.setReserveBalance(Long.parseLong(reserveBalance));
		acctBalanceModel.setCycleUpper(Long.parseLong(cycleUpper));
		acctBalanceModel.setCycleLower(Long.parseLong(cycleLower));
		acctBalanceModel.setStatusCd(statusCd);
		acctBalanceModel.setStatusDate(sdf.parse(statusDate));
		acctBalanceModel.setCycleType(cycleType);
		acctBalanceModel.setRemark(remark);
		acctBalanceModel.setNeedInvoiceAmount(Long.parseLong(needInvoiceAmount));
		
		iAcctBalanceService.insertAcctBalance(acctBalanceModel);
		
		System.out.println("......."+acctBalanceId);
		List list=new ArrayList();
		HttpServletResponse res =ServletActionContext.getResponse();
		JSONArray json = new JSONArray();
		json.addAll(list);
		System.out.println(json.toString());
		PrintWriter writer = res.getWriter();
		writer.print(json);
		writer.flush();
		writer.close();
		return SUCCESS;
	}

	
	public IAcctBalanceService getiAcctBalanceService() {
		return iAcctBalanceService;
	}
	@Autowired
	public void setiAcctBalanceService(IAcctBalanceService iAcctBalanceService) {
		this.iAcctBalanceService = iAcctBalanceService;
	}
	public String getAcctBalanceId() {
		return acctBalanceId;
	}
	public void setAcctBalanceId(String acctBalanceId) {
		this.acctBalanceId = acctBalanceId;
	}
	public String getBalanceTypeId() {
		return balanceTypeId;
	}
	public void setBalanceTypeId(String balanceTypeId) {
		this.balanceTypeId = balanceTypeId;
	}
	public String getPaymentRuleId() {
		return paymentRuleId;
	}
	public void setPaymentRuleId(String paymentRuleId) {
		this.paymentRuleId = paymentRuleId;
	}
	public String getSubAcctId() {
		return subAcctId;
	}
	public void setSubAcctId(String subAcctId) {
		this.subAcctId = subAcctId;
	}
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
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
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getReserveBalance() {
		return reserveBalance;
	}
	public void setReserveBalance(String reserveBalance) {
		this.reserveBalance = reserveBalance;
	}
	public String getCycleUpper() {
		return cycleUpper;
	}
	public void setCycleUpper(String cycleUpper) {
		this.cycleUpper = cycleUpper;
	}
	public String getCycleLower() {
		return cycleLower;
	}
	public void setCycleLower(String cycleLower) {
		this.cycleLower = cycleLower;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}
	public String getCycleType() {
		return cycleType;
	}
	public void setCycleType(String cycleType) {
		this.cycleType = cycleType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNeedInvoiceAmount() {
		return needInvoiceAmount;
	}
	public void setNeedInvoiceAmount(String needInvoiceAmount) {
		this.needInvoiceAmount = needInvoiceAmount;
	}
	
}
