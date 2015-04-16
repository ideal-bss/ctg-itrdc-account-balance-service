package com.ctg.itrdc.account.balance.repository;

import com.ctg.itrdc.account.balance.model.BalanceShareRuleTypeModel;

public interface IBalanceShareRuleTypeMapper {

    int deleteByPrimaryKey(Long shareRuleTypeId);

    int insert(BalanceShareRuleTypeModel record);

    int insertSelective(BalanceShareRuleTypeModel record);

    BalanceShareRuleTypeModel selectByPrimaryKey(Long shareRuleTypeId);

    int updateByPrimaryKeySelective(BalanceShareRuleTypeModel record);

    int updateByPrimaryKey(BalanceShareRuleTypeModel record);
}
