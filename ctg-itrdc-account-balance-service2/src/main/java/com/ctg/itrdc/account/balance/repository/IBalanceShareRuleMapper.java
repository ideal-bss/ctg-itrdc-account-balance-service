package com.ctg.itrdc.account.balance.repository;

import java.util.Map;

import com.ctg.itrdc.account.balance.model.BalanceShareRuleModel;

public interface IBalanceShareRuleMapper {

    int deleteByPrimaryKey(Long shareRuleId);

    int insert(BalanceShareRuleModel record);

    int insertSelective(BalanceShareRuleModel record);

    BalanceShareRuleModel selectByPrimaryKey(Long shareRuleId);

    int updateByPrimaryKeySelective(BalanceShareRuleModel record);

    int updateByPrimaryKey(BalanceShareRuleModel record);
    
    int selectRuleByObjectId(Long objectId);
    
    Map<String, Object> selectRuleType(Map<String, Object> map);
}
