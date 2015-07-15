package com.ctg.itrdc.account.balance.service;

import java.util.List;
import java.util.Map;

import com.ctg.itrdc.account.balance.model.AcctBalanceModel;
import com.ctg.itrdc.account.balance.model.BalanceShareRuleModel;

public interface IAcctBalanceService {
	public void insertAcctBalance(AcctBalanceModel model,BalanceShareRuleModel shareModel);
	public void insertBalance(AcctBalanceModel model,BalanceShareRuleModel shareModel);
	public void selectAcctBalance(Map<String, String> map);
	public List<AcctBalanceModel> selectBalance(AcctBalanceModel model);
	public AcctBalanceModel selectBalanceById(AcctBalanceModel model);
	public Map<String, Object> queryBalance(Map<String, Object> model);
	public String balanceDraw(Map<String,Object> model);
	public String balanceTransfer(Map<String, Object> map);
	public String balanceReverse(Map<String, Object> record);
	public Map<String, Object> queryAcctBalanceLog(Map<String, Object> map);
	public Map<String, Object> queryBalFrozen(Map<String,Object> mapQuery);
	public String balanceFrozen(Map<String, Object> record);
	public String BalanceUnFrozen(String[] balanceFrozenIdArray);
	
	public Map<String, Object> acctBalLogbalSourceQuery(Map<String, Object> record);
	
	public Map<String, Object> acctBalLogbalPayOutQuery(Map<String, Object> record);
}
