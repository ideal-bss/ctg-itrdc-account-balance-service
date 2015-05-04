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
 * 专款 专用
 * @author hxj
 *
 */
@Controller
public class SpecialPaymentAction extends ActionSupport{
	private String spePaymentId;
	private String partnerId;
	private String productId;
	private String acctItemGroupId;
	
	public String specialQuery()throws Exception{
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
	public String specialQueryGo()throws Exception{
		return SUCCESS;
	}
	public String specialAddGo()throws Exception{
		return SUCCESS;
	}
	public String getSpePaymentId() {
		return spePaymentId;
	}

	public void setSpePaymentId(String spePaymentId) {
		this.spePaymentId = spePaymentId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAcctItemGroupId() {
		return acctItemGroupId;
	}

	public void setAcctItemGroupId(String acctItemGroupId) {
		this.acctItemGroupId = acctItemGroupId;
	}
	
	
}
