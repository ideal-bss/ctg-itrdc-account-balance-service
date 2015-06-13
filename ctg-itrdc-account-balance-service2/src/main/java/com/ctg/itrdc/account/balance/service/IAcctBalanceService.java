package com.ctg.itrdc.account.balance.service;

import java.util.List;
import java.util.Map;

import com.ctg.itrdc.account.balance.model.AcctBalanceModel;
import com.ctg.itrdc.account.balance.model.BalanceShareRuleModel;

public interface IAcctBalanceService {
	public void insertAcctBalance(AcctBalanceModel model,BalanceShareRuleModel shareModel);
	public void selectAcctBalance(Map<String, String> map);
	public List<AcctBalanceModel> selectBalance(AcctBalanceModel model);
	public AcctBalanceModel selectBalanceById(AcctBalanceModel model);
	public List<Object> queryBalance(Map<String, Object> model);
	public String balanceDraw(Map<String,Object> model);
	public String balanceTransfer(Map<String, Object> map);
	
}
