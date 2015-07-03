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
	private int origBalanceTypeId;
	private long origAcctId;
	private long requestId;
	private int origObjectType;
	private String origObjectId;
	private long amount;
	private int balanceTypeId;
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
		map.put("origAcctId", origAcctId);
		map.put("requestId", requestId);
		map.put("origObjectType", origObjectType);
		map.put("origObjectId", origObjectId);
		map.put("amount", amount);
		map.put("balanceTypeId", balanceTypeId);
		map.put("acctId", acctId);
		map.put("objectType", objectType);
		map.put("objectId", objectId);
		logger.debug("map:" + map);
		
		if (balanceTypeId == origBalanceTypeId) {
			json = iAcctBalanceService.balanceTransfer(map);
		} else {
			json = "源余额类型和目的余额类型不一致！";
		}
		
		
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

	public long getOrigAcctId() {
		return origAcctId;
	}

	public void setOrigAcctId(long origAcctId) {
		this.origAcctId = origAcctId;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public int getOrigObjectType() {
		return origObjectType;
	}

	public void setOrigObjectType(int origObjectType) {
		this.origObjectType = origObjectType;
	}

	public String getOrigObjectId() {
		return origObjectId;
	}

	public void setOrigObjectId(String origObjectId) {
		this.origObjectId = origObjectId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getBalanceTypeId() {
		return balanceTypeId;
	}

	public void setBalanceTypeId(int balanceTypeId) {
		this.balanceTypeId = balanceTypeId;
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

	@Autowired
	public void setiAcctBalanceService(IAcctBalanceService iAcctBalanceService) {
		this.iAcctBalanceService = iAcctBalanceService;
	}
	
	
	
}
