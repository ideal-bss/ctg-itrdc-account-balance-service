package com.ctg.itrdc.account.balance.action;

import java.util.HashMap;
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
	
	private int rows;
	private int page;
	/**
	 * 
	 * @author ls
	 * @return success
	 */
	public String queryBalanceGo() {
		loggers.debug("queryBalanceGo()");
		return "success";
	}
	
	public String queryBalance() {
		loggers.debug("queryBalance()......start......");
		
		String resultFlag = "success";
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("objectId", getObjectId());
			model.put("balanceTypeId", balanceTypeId);
			model.put("objectType", ObjectIdType);
			model.put("rows", rows);
			model.put("page", ((page-1)*rows));
			Map<String, Object> map = iAcctBalanceService.queryBalance(model);
			writeJson(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			loggers.debug("queryBalance()......end......");
		}
		return resultFlag;
	}
	
	

	@Autowired
	public void setiAcctBalanceService(IAcctBalanceService iAcctBalanceService) {
		this.iAcctBalanceService = iAcctBalanceService;
	}

	public String getObjectId() {
		if (objectId != null) {
			objectId = objectId.trim();
		}
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

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	
}
