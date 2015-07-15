package com.ctg.itrdc.account.balance.repository;

import java.util.List;
import java.util.Map;

import com.ctg.itrdc.account.balance.model.BalanceSourceModel;


/**
 * @desc 余额来源
 * @author ls
 * @date:2015-6-10
 * @version:
 */
public interface IBalanceSourceMapper {
	BalanceSourceModel selectByPrimaryKey(BalanceSourceModel record);
	
	List<BalanceSourceModel> selectByAcctBalanceId(BalanceSourceModel record);
	
	void updateByPrimaryKey(BalanceSourceModel record);
	
	long insert(BalanceSourceModel record);
	
	List<BalanceSourceModel> selectSourceByAcctBalLog(Map<String, Object> record);
	
	int selectSourceByAcctBalLogTotal(Map<String, Object> record);
}
