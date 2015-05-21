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
import com.ctg.itrdc.account.balance.model.BalanceShareRuleModel;
import com.ctg.itrdc.account.balance.service.IAcctBalanceService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 余额账本
 * @author hxj
 *
 */
@Controller
public class AcctBalanceAction extends BaseAction{
	private String objectId;//余额对象标识
	private String objectType;//余额对象类型
	private String shareRuleTypeId;//共享规则类型
	private String shareRuleTypePri;//共享规则优先级
	private String upperAmount;//扣费上限
	private String lowerAmount;//扣费下限
	
//	private String acctBalanceId;
    private String balanceTypeId;//余额类型标识
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
	
	private String radio;
	private String acct_sub_id;
	/**
	 * 余额账本查询
	 */
	public void balanceQueryGo(){
		AcctBalanceModel model=new AcctBalanceModel();
		System.out.println("radio:"+radio+"   acct_sub_id:"+acct_sub_id);
		if(radio.equals("0")){
			//设备号
			
			model.setAcctId(Long.parseLong(acct_sub_id));
		}else{
			//合同号
			
			model.setSubAcctId(Long.parseLong(acct_sub_id));
		}
		writeJson(iAcctBalanceService.selectBalance(model));
	}
	/**
	 * 余额账本单条查询
	 */
	public void balanceQueryById(){
		AcctBalanceModel model=new AcctBalanceModel();
		model.setAcctBalanceId((long)1);
		model.setSliceKey((long)1);
		writeJson(iAcctBalanceService.selectBalance(model));
	}
	
	/**
	 * 普通余额存入界面展现
	 * @return
	 * @throws Exception
	 */
	public String balanceAddGo()throws Exception{
		System.out.println("..............................");
		return "success";
	}
	/**
	 * 普通余额存入
	 * @return
	 * @throws Exception
	 */
	public String balanceAdd()throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		try {
			//账本对象
			AcctBalanceModel acctBalanceModel=new AcctBalanceModel();
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
			acctBalanceModel.setSliceKey(Long.parseLong(subAcctId));
			
			
			//共享规则对象
			BalanceShareRuleModel shareModel=new BalanceShareRuleModel();
			shareModel.setObjectId(Long.parseLong(objectId));
			shareModel.setObjectType(objectType);
			shareModel.setShareRuleTypeId(Long.parseLong(shareRuleTypeId));
			shareModel.setShareRuleTypePri(Long.parseLong(shareRuleTypePri));
			shareModel.setUpperAmount(Long.parseLong(upperAmount));
			shareModel.setLowerAmount(Long.parseLong(lowerAmount));
			shareModel.setSliceKey(Long.parseLong(subAcctId));
			
			iAcctBalanceService.insertAcctBalance(acctBalanceModel, shareModel);
			writeJson(new ArrayList());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("balanceAdd...");
		return "success";
	}

	public String deducBalance(){
		
		return "success";
	}
	
	public IAcctBalanceService getiAcctBalanceService() {
		return iAcctBalanceService;
	}
	@Autowired
	public void setiAcctBalanceService(IAcctBalanceService iAcctBalanceService) {
		this.iAcctBalanceService = iAcctBalanceService;
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
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getShareRuleTypeId() {
		return shareRuleTypeId;
	}
	public void setShareRuleTypeId(String shareRuleTypeId) {
		this.shareRuleTypeId = shareRuleTypeId;
	}
	public String getShareRuleTypePri() {
		return shareRuleTypePri;
	}
	public void setShareRuleTypePri(String shareRuleTypePri) {
		this.shareRuleTypePri = shareRuleTypePri;
	}
	public String getUpperAmount() {
		return upperAmount;
	}
	public void setUpperAmount(String upperAmount) {
		this.upperAmount = upperAmount;
	}
	public String getLowerAmount() {
		return lowerAmount;
	}
	public void setLowerAmount(String lowerAmount) {
		this.lowerAmount = lowerAmount;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getAcct_sub_id() {
		return acct_sub_id;
	}
	public void setAcct_sub_id(String acct_sub_id) {
		this.acct_sub_id = acct_sub_id;
	}
	
}
