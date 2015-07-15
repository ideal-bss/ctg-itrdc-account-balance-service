package com.ctg.itrdc.account.balance.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ctg.itrdc.account.balance.service.IAcctBalanceService;

/**
 * @desc 余额转账
 * @author ls
 * @date:2015-6-11
 * @version:
 */
@Controller
public class BalanceTransferAction extends BaseAction {
	Logger logger = Logger.getLogger(BalanceTransferAction.class);
	private long acctBalanceId;
	private int origBalanceTypeId;
	private long origAcctId;
	private long amount;
	private long acctId;
	private int objectType;
	private String objectId;
	
	private IAcctBalanceService iAcctBalanceService;
	/**
	 * 
	 * @desc 余额转账 界面跳转
	 * @author ls
	 * @return
	 */
	public String balanceTarnsferGo(){
		logger.debug("balanceTarnsferGo().");
		return "success";
	}
	
	/**
	 * 
	 * @desc 余额转账
	 * @author ls
	 * @return
	 */
	public String balanceTarnsfer() {
		logger.debug("balanceTarnsfer() ......余额转账 start......");
		String json = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("origBalanceTypeId", origBalanceTypeId);
		map.put("acctBalanceId", acctBalanceId);
		map.put("origAcctId", origAcctId);
		map.put("amount", amount);
		map.put("acctId", acctId);
		map.put("objectType", objectType);
		map.put("objectId", objectId);
		logger.debug("map:" + map);
		
		json = iAcctBalanceService.balanceTransfer(map);
		
		writeJson(json);
		logger.debug("balanceTarnsfer() ......余额转账 end......");
		return "success";
	}

	
	
	public int getOrigBalanceTypeId() {
		return origBalanceTypeId;
	}

	public void setOrigBalanceTypeId(int origBalanceTypeId) {
		this.origBalanceTypeId = origBalanceTypeId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getAcctId() {
		return acctId;
	}

	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}

	public int getObjectType() {
		return objectType;
	}

	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public long getAcctBalanceId() {
		return acctBalanceId;
	}

	public void setAcctBalanceId(long acctBalanceId) {
		this.acctBalanceId = acctBalanceId;
	}

	public long getOrigAcctId() {
		return origAcctId;
	}

	public void setOrigAcctId(long origAcctId) {
		this.origAcctId = origAcctId;
	}

	@Autowired
	public void setiAcctBalanceService(IAcctBalanceService iAcctBalanceService) {
		this.iAcctBalanceService = iAcctBalanceService;
	}
	
	
	
}
