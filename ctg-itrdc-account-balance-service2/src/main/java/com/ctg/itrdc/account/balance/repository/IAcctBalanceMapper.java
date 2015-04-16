package com.ctg.itrdc.account.balance.repository;

import java.util.Map;

import com.ctg.itrdc.account.balance.model.AcctBalanceModel;

public interface IAcctBalanceMapper {
	
	int selectAcctBalance(Map<String, String> map);
	
	int insert(AcctBalanceModel record);
	
	int insertSelective(AcctBalanceModel record);
	
	AcctBalanceModel selectByPrimaryKey(Long acctBalanceId);
	
	int updateByPrimaryKeySelective(AcctBalanceModel record);

    int updateByPrimaryKey(AcctBalanceModel record);
    
    int updateBalance(Map<String, Object> map);
}
