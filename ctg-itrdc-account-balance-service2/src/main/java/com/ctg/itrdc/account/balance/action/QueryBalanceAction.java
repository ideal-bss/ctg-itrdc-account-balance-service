package com.ctg.itrdc.account.balance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ctg.itrdc.account.balance.service.IAcctBalanceService;
/**
 * @author ls
 * @date:2015-6-2
 * @version:
 */
@Controller
public class QueryBalanceAction extends BaseAction {
	private static Logger loggers = Logger.getLogger(QueryBalanceAction.class);
	
	
	private String objectId;
	private String balanceTypeId;
	private String ObjectIdType;
	private IAcctBalanceService iAcctBalanceService;
	/**
	 * 
	 * @author ls
	 * @return success
	 */
	public String queryBalanceGo() {
		loggers.info("queryBalanceGo()");
		return "success";
	}
	
	public String queryBalance() {
		loggers.info("queryBalance()");
		
		String resultFlag = "success";
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("objectId", objectId);
			model.put("balanceTypeId", balanceTypeId);
			model.put("objectType", ObjectIdType);
			List<Object> resultList = iAcctBalanceService.queryBalance(model);
			if (resultList == null || resultList.isEmpty() ||((HashMap)resultList.get(0)).containsKey("errorInfo")) {
				loggers.info("账本不存在！");
				resultFlag = "error";
			}
			
			writeJson(resultList);
			
		} catch (Exception e) {
			loggers.error(e.getMessage());
			e.printStackTrace();
		}
		return resultFlag;
	}
	
	

	@Autowired
	public void setiAcctBalanceService(IAcctBalanceService iAcctBalanceService) {
		this.iAcctBalanceService = iAcctBalanceService;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getBalanceTypeId() {
		return balanceTypeId;
	}

	public void setBalanceTypeId(String balanceTypeId) {
		this.balanceTypeId = balanceTypeId;
	}

	public String getObjectIdType() {
		return ObjectIdType;
	}

	public void setObjectIdType(String objectIdType) {
		ObjectIdType = objectIdType;
	}
	
	
}
