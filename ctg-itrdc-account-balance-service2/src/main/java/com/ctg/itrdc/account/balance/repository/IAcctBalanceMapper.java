package com.ctg.itrdc.account.balance.repository;

import java.util.List;
import java.util.Map;

import com.ctg.itrdc.account.balance.dao.Mapper;
import com.ctg.itrdc.account.balance.model.AcctBalanceModel;
import com.ctg.itrdc.account.balance.model.BalanceRelationModel;
import com.ctg.itrdc.account.balance.model.BalanceSourceModel;

public interface IAcctBalanceMapper extends Mapper<AcctBalanceModel>{
	
	int selectAcctBalance(Map<String, String> map);
    
    int updateBalance(Map<String, Object> map);
    
    List<AcctBalanceModel> selectBalanceByAcct(AcctBalanceModel model);
    
    Long selectAcctBalanceId(AcctBalanceModel model);
    
    int deleteAcctBalanceById(AcctBalanceModel model);
    
    int insertSourceSelective(BalanceSourceModel model);
    
    int insertRelation(BalanceRelationModel model);
    
    long selectRelationId(BalanceRelationModel model);
    
    List<AcctBalanceModel> selectByAcctId(Map<String, Object> record);
    
    int updateByPrimaryKeySelective(AcctBalanceModel record);
    
    int updateBalanceByAcctBalId(Map<String, Object> record);
}
