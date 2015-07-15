package com.ctg.itrdc.account.balance.repository;

import java.util.List;
import java.util.Map;

import com.ctg.itrdc.account.balance.model.BalancePayoutModel;

/**
 * @desc 
 * @author ls
 * @date:2015-6-11
 * @version:
 */
public interface IBalancePayoutMapper {
	void insert(BalancePayoutModel record);
	BalancePayoutModel selectByPrimaryKey(BalancePayoutModel record);
	
	List<BalancePayoutModel> selectPayOutByAcctBalLog(Map<String, Object> record);
	int selectPayOutByAcctBalLogTotal(Map<String, Object> record);
}
