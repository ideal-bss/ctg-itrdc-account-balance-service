package com.ctg.itrdc.account.balance.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ctg.itrdc.account.balance.service.IAcctBalanceService;

/**
 * @desc 余额冲正
 * @author ls
 * @date:2015-6-15
 * @version:
 */
@Controller
public class BalanceReverseAction extends BaseAction {
	Logger logger = Logger.getLogger(BalanceReverseAction.class);
	private IAcctBalanceService iAcctBalanceService;
	private long operIncomeId;
	
	/**
	 * 
	 * @desc 冲正界面跳转
	 * @author ls
	 * @return
	 */
	public String balanceReverseGo(){
		logger.debug("balanceReverseGo.");
		return "success";
	}
	
	/**
	 * 
	 * @desc 冲正
	 * @author ls
	 * @return
	 */
	public String balanceReverse(){
		logger.debug("balanceReverse start.");
		String hint = null;
		hint = iAcctBalanceService.balanceReverse(operIncomeId);
		if (hint == null || hint.trim().length()==0) {
			hint = "未知错误！";
			logger.info(hint);
		}
		writeJson(hint);
		logger.debug("balanceReverse end.");
		return "success";
	}

	public long getOperIncomeId() {
		return operIncomeId;
	}

	public void setOperIncomeId(long operIncomeId) {
		this.operIncomeId = operIncomeId;
	}

	public IAcctBalanceService getiAcctBalanceService() {
		return iAcctBalanceService;
	}
	@Autowired
	public void setiAcctBalanceService(IAcctBalanceService iAcctBalanceService) {
		this.iAcctBalanceService = iAcctBalanceService;
	}
	
	
}
