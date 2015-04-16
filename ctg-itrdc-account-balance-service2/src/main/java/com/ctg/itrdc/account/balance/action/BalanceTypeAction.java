package com.ctg.itrdc.account.balance.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 余额类型
 * @author hxj
 *
 */
@Controller
public class BalanceTypeAction extends ActionSupport{
	private String balanceTypeId;
	private String priority;
	private String spePaymentId;
	private String balanceTypeName;
	private String state;
	
	public String balanceTypeQuery()throws Exception{
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
	public String balanceTypeQueryGo()throws Exception{
		return SUCCESS;
	}
	
	
	public String getBalanceTypeId() {
		return balanceTypeId;
	}
	public void setBalanceTypeId(String balanceTypeId) {
		this.balanceTypeId = balanceTypeId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSpePaymentId() {
		return spePaymentId;
	}
	public void setSpePaymentId(String spePaymentId) {
		this.spePaymentId = spePaymentId;
	}
	public String getBalanceTypeName() {
		return balanceTypeName;
	}
	public void setBalanceTypeName(String balanceTypeName) {
		this.balanceTypeName = balanceTypeName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
