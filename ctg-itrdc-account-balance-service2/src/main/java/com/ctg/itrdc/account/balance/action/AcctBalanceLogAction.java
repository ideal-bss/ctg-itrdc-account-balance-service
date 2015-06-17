package com.ctg.itrdc.account.balance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ctg.itrdc.account.balance.service.IAcctBalanceService;

/**
 * @desc 余额账本日志查询
 * @author ls
 * @date:2015-6-15
 * @version:
 */
@Controller
public class AcctBalanceLogAction extends BaseAction {
	Logger logger = Logger.getLogger(AcctBalanceLogAction.class);
	private long acctId;
	private long balanceTypeId;
	private IAcctBalanceService iAcctBalanceService;
	/**
	 * 
	 * @desc 界面跳转
	 * @author ls
	 * @return
	 */
	public String acctBalanceLogGo(){
		logger.info("acctBalanceLogGo().");
		return "success";
	}
	
	/**
	 * 
	 * @desc 账本日志查询
	 * @author ls
	 * @return
	 */
	public String acctBalanceLog(){
		logger.info("acctBalanceLog()......start......");
		List<Object> resultList = null;
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("acctId", acctId);
			map.put("balanceTypeId", balanceTypeId);
			resultList = iAcctBalanceService.queryAcctBalanceLog(map);
			
			writeJson(resultList);
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		} finally {
			logger.info("acctBalanceLog()......end......");
		}
		
		return "success";
	}

	public long getAcctId() {
		return acctId;
	}

	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}

	public long getBalanceTypeId() {
		return balanceTypeId;
	}

	public void setBalanceTypeId(long balanceTypeId) {
		this.balanceTypeId = balanceTypeId;
	}

	public IAcctBalanceService getiAcctBalanceService() {
		return iAcctBalanceService;
	}
	
	@Autowired
	public void setiAcctBalanceService(IAcctBalanceService iAcctBalanceService) {
		this.iAcctBalanceService = iAcctBalanceService;
	}
	
	
}
