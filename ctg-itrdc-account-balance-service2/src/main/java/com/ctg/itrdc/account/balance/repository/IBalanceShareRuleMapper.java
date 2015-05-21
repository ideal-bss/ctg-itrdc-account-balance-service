package com.ctg.itrdc.account.balance.repository;

import java.util.Map;

import com.ctg.itrdc.account.balance.dao.Mapper;
import com.ctg.itrdc.account.balance.model.BalanceShareRuleModel;

public interface IBalanceShareRuleMapper extends Mapper<BalanceShareRuleModel>{
    
    int selectRuleByObjectId(BalanceShareRuleModel model);
    
    Map<String, Object> selectRuleType(BalanceShareRuleModel model);
    
    int deleteRuleByAcctBalanceId(BalanceShareRuleModel model);
}
