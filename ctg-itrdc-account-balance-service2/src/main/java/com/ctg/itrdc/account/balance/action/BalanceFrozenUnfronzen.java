package com.ctg.itrdc.account.balance.action;

import java.util.HashMap;
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
	
	private int rows;
	private int page;
	/**
	 * 
	 * @desc 余额冻结查询界面跳转
	 * @author ls
	 * @return
	 */
	public String balanceFrozenQueryGo(){
		logger.debug("balanceFrozenGo().");
		return "success";
	}
	
	/**
	 * 
	 * @desc 余额冻结查询
	 * @author ls
	 * @return
	 */
	public String balanceFrozenQuery(){
		logger.debug("balanceFrozenQuery()......start......");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("acctId", acctId);
		map.put("acctBalanceId", acctBalanceId);
		map.put("rows", rows);
		map.put("page", ((page-1)*rows));
		Map<String, Object> resultMap = iAcctBalanceService.queryBalFrozen(map);
		writeJson(resultMap);
		logger.debug("balanceFrozenQuery()......end......");
		return "success";
	}
	
	/**
	 * 
	 * @desc 余额冻结
	 * @author ls
	 * @return
	 */
	public String balanceFrozen(){
		logger.debug("balanceFrozen()......start......");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subAcctId", subAcctId);
		map.put("acctBalanceId", acctBalanceId);
		map.put("frozenAmount", frozenAmount);
		String hint = iAcctBalanceService.balanceFrozen(map);
		writeJson(hint);
		logger.debug("balanceFrozen()......end......");
		return "success";
	}
	
	/**
	 * 
	 * @desc 余额解冻
	 * @author ls
	 * @return
	 */
	public String balanceUnFrozen(){
		logger.debug("balanceUnFrozen()......start......");
		try {
			String json = "";
			String []balFrozenIdArray = balFrozenId.split(",");
			json = iAcctBalanceService.BalanceUnFrozen(balFrozenIdArray);
			writeJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("balanceUnFrozen()......end......");
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
