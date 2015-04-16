package com.ctg.itrdc.account.balance.repository;

import com.ctg.itrdc.account.balance.model.BalanceAcctItemPayedModel;

public interface IBalanceAcctItemPayedMapper {

    int deleteByPrimaryKey(Long operPayoutId);

    int insert(BalanceAcctItemPayedModel record);

    int insertSelective(BalanceAcctItemPayedModel record);

    BalanceAcctItemPayedModel selectByPrimaryKey(Long operPayoutId);

    int updateByPrimaryKeySelective(BalanceAcctItemPayedModel record);

    int updateByPrimaryKey(BalanceAcctItemPayedModel record);
}
