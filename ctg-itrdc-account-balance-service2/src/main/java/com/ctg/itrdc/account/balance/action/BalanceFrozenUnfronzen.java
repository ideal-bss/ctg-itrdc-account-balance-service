package com.ctg.itrdc.account.balance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ctg.itrdc.account.balance.service.IAcctBalanceService;

/**
 * @desc 余额冻结解冻和冻结查询
 * @author ls
 * @date:2015-6-18
 * @version:
 */
@Controller
public class BalanceFrozenUnfronzen extends BaseAction {
	Logger logger = Logger.getLogger(BalanceFrozenUnfronzen.class);
	private IAcctBalanceService iAcctBalanceService;
	private long acctId;
	private long acctBalanceId;
	private long frozenAmount;
	private long subAcctId;
	private String balFrozenId;
	/**
	 * 
	 * @desc 余额冻结查询界面跳转
	 * @author ls
	 * @return
	 */
	public String balanceFrozenQueryGo(){
		logger.info("balanceFrozenGo().");
		return "success";
	}
	
	/**
	 * 
	 * @desc 余额冻结查询
	 * @author ls
	 * @return
	 */
	public String balanceFrozenQuery(){
		logger.info("balanceFrozenQuery()......start......");
		List<Object> resultList = iAcctBalanceService.queryBalFrozen(acctId);
		writeJson(resultList);
		logger.info("balanceFrozenQuery()......end......");
		return "success";
	}
	
	/**
	 * 
	 * @desc 余额冻结
	 * @author ls
	 * @return
	 */
	public String balanceFrozen(){
		logger.info("balanceFrozen()......start......");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subAcctId", subAcctId);
		map.put("acctBalanceId", acctBalanceId);
		map.put("frozenAmount", frozenAmount);
		String hint = iAcctBalanceService.balanceFrozen(map);
		writeJson(hint);
		logger.info("balanceFrozen()......end......");
		return "success";
	}
	
	/**
	 * 
	 * @desc 余额解冻
	 * @author ls
	 * @return
	 */
	public String balanceUnFrozen(){
		logger.info("balanceUnFrozen()......start......");
		try {
			String json = "";
			String []balFrozenIdArray = balFrozenId.split(",");
			json = iAcctBalanceService.BalanceUnFrozen(balFrozenIdArray);
			writeJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("balanceUnFrozen()......end......");
		return "success";
	}

	public IAcctBalanceService getiAcctBalanceService() {
		return iAcctBalanceService;
	}
	
	@Autowired
	public void setiAcctBalanceService(IAcctBalanceService iAcctBalanceService) {
		this.iAcctBalanceService = iAcctBalanceService;
	}

	public long getAcctId() {
		return acctId;
	}

	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}

	public long getAcctBalanceId() {
		return acctBalanceId;
	}

	public void setAcctBalanceId(long acctBalanceId) {
		this.acctBalanceId = acctBalanceId;
	}

	public long getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(long frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public long getSubAcctId() {
		return subAcctId;
	}

	public void setSubAcctId(long subAcctId) {
		this.subAcctId = subAcctId;
	}

	public String getBalFrozenId() {
		return balFrozenId;
	}

	public void setBalFrozenId(String balFrozenId) {
		this.balFrozenId = balFrozenId;
	}
	
	
}
