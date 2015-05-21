package com.ctg.itrdc.account.balance.repository;

import java.util.List;
import java.util.Map;

import com.ctg.itrdc.account.balance.dao.Mapper;
import com.ctg.itrdc.account.balance.model.AcctBalanceModel;

public interface IAcctBalanceMapper extends Mapper<AcctBalanceModel>{
	
	int selectAcctBalance(Map<String, String> map);
    
    int updateBalance(Map<String, Object> map);
    
    List<AcctBalanceModel> selectBalanceByAcct(AcctBalanceModel model);
    
    Long selectAcctBalanceId(AcctBalanceModel model);
    
    int deleteAcctBalanceById(AcctBalanceModel model);
}
