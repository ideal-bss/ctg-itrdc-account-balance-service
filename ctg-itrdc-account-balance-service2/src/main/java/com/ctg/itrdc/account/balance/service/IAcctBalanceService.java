package com.ctg.itrdc.account.balance.service;

import java.util.Map;

import com.ctg.itrdc.account.balance.model.AcctBalanceModel;

public interface IAcctBalanceService {
	public void insertAcctBalance(AcctBalanceModel model);
	public void selectAcctBalance(Map<String, String> map);
	
}
