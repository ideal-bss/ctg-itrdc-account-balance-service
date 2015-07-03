package com.ctg.itrdc.account.balance.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ctg.itrdc.account.balance.service.IAcctBalanceService;

/**
 * @author ls
 * @date:2015-5-26
 * @version:
 */
@Controller
public class BalanceDrawAction extends BaseAction {
	private String acctId;
	private String objectType;
	private String drawAmount;
	private String objectId;
	private String requestId;
	private IAcctBalanceService iAcctBalanceService;
	
	private static Logger logger = Logger.getLogger(BalanceDrawAction.class);
	/**
	 * 余额支取跳转
	 * @author ls
	 * @return
	 */
	public String balanceDrawGo(){
		logger.debug("balance draw go.");
		return "success";
	}
	
	/**
	 * 
	 * @desc 余额支取操作
	 * @author ls
	 * @return
	 */
	public String balanceDraw(){
		logger.debug("balance draw start.");
		String drawHint = "";
		JSONArray json = new JSONArray();
		List<String> responseList = new ArrayList<String>();
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("acctId", acctId);
			model.put("objectType", objectType);
			model.put("drawAmount", drawAmount);
			model.put("objectId", objectId);
			model.put("requestId", requestId);
			drawHint = iAcctBalanceService.balanceDraw(model);
			responseList.add(drawHint);
			
		} catch (Exception e) {
			responseList.add("余额支取失败：" + e.getMessage());
			e.printStackTrace();
		} finally {
			json.add(responseList);
			writeJson(json);
			logger.debug("balance draw end.");
		}
		
		return "success";
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	
	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getDrawAmount() {
		return drawAmount;
	}

	public void setDrawAmount(String drawAmount) {
		this.drawAmount = drawAmount;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	@Autowired
	public void setiAcctBalanceService(IAcctBalanceService iAcctBalanceService) {
		this.iAcctBalanceService = iAcctBalanceService;
	}

	
}
