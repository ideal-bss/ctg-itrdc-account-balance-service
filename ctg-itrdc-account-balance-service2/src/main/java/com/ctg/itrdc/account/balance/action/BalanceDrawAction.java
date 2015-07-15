package com.ctg.itrdc.account.balance.action;

import java.util.HashMap;
import java.util.Map;


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
	private String drawAmount;
	private String objectId;
	private String acctBalanceIdArray;
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
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("acctId", acctId);
			model.put("drawAmount", drawAmount);
			model.put("objectId", objectId);
			model.put("acctBalanceIdArray", acctBalanceIdArray);
			drawHint = iAcctBalanceService.balanceDraw(model);
			
		} catch (Exception e) {
			drawHint = "余额支取失败：" + e.getMessage();
			e.printStackTrace();
		} finally {
			writeJson(drawHint);
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

	public String getAcctBalanceIdArray() {
		return acctBalanceIdArray;
	}

	public void setAcctBalanceIdArray(String acctBalanceIdArray) {
		this.acctBalanceIdArray = acctBalanceIdArray;
	}

	@Autowired
	public void setiAcctBalanceService(IAcctBalanceService iAcctBalanceService) {
		this.iAcctBalanceService = iAcctBalanceService;
	}

	
}
