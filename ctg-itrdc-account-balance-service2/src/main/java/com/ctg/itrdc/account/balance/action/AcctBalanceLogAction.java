package com.ctg.itrdc.account.balance.action;

import java.util.HashMap;
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
	private long acctBalanceId;
	private long operIncomeId;
	private IAcctBalanceService iAcctBalanceService;
	
	private int rows;
	private int page;
	/**
	 * 
	 * @desc 界面跳转
	 * @author ls
	 * @return
	 */
	public String acctBalanceLogGo(){
		logger.debug("acctBalanceLogGo().");
		return "success";
	}
	
	/**
	 * 
	 * @desc 账本日志查询
	 * @author ls
	 * @return
	 */
	public String acctBalanceLog(){
		logger.debug("acctBalanceLog()......start......");
		Map<String, Object> mapList = null;
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("acctId", acctId);
			map.put("balanceTypeId", balanceTypeId);
			map.put("rows", rows);
			map.put("page", ((page-1)*rows));
			mapList = iAcctBalanceService.queryAcctBalanceLog(map);
			
			writeJson(mapList);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		} finally {
			logger.debug("acctBalanceLog()......end......");
		}
		
		return "success";
	}
	
	/**
	 * 
	 * @desc 余额来源查询
	 * @author ls
	 * @return
	 */
	public String acctBalLogbalSourceQuery(){
		logger.debug("acctBalLogbalSourceQuery()......start......");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sliceKey", acctId);
			map.put("acctBalanceId", acctBalanceId);
			map.put("rows", rows);
			map.put("page", ((page-1)*rows));
			Map<String, Object> result = iAcctBalanceService.acctBalLogbalSourceQuery(map);
			writeJson(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.debug("acctBalLogbalSourceQuery()......end......");
		}
		return "success";
	}
	
	/**
	 * 
	 * @desc 余额支出记录查询
	 * @author ls
	 * @return
	 */
	public String acctBalLogbalPayOutQuery(){
		logger.debug("acctBalLogbalSourceQuery()......start......");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sliceKey", acctId);
			map.put("acctBalanceId", acctBalanceId);
			map.put("operIncomeId", operIncomeId);
			map.put("rows", rows);
			map.put("page", ((page-1)*rows));
			Map<String, Object> result = iAcctBalanceService.acctBalLogbalPayOutQuery(map);
			writeJson(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.debug("acctBalLogbalSourceQuery()......end......");
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

	public long getAcctBalanceId() {
		return acctBalanceId;
	}

	public void setAcctBalanceId(long acctBalanceId) {
		this.acctBalanceId = acctBalanceId;
	}

	public long getOperIncomeId() {
		return operIncomeId;
	}

	public void setOperIncomeId(long operIncomeId) {
		this.operIncomeId = operIncomeId;
	}
	
	
}
