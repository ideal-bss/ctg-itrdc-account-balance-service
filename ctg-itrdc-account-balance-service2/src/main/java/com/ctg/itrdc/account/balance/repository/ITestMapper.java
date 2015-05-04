package com.ctg.itrdc.account.balance.repository;

import java.util.Map;

import com.ctg.itrdc.account.balance.dao.Mapper;
import com.ctg.itrdc.account.balance.model.AcctBalanceModel;


public interface ITestMapper extends Mapper<AcctBalanceModel>{
	int updateTest(Map map);
	
	int selectTest();
	
	String selectSequence();
	
	Long selectGlobalSequence();
	
	int updateGlobalSequence();
	
	int selectAcctBalance();
	
	int insertAcctBalance();
	
	int insertGlobalSequence();
	
	int selectHit(Map<String, String> map);
}
