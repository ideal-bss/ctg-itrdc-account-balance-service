package com.ctg.itrdc.account.balance.repository;

import com.ctg.itrdc.account.balance.model.AcctBalanceLogModel;

public interface IAcctBalanceLogMapper{

    int deleteByPrimaryKey(Long balanceLogId);

    int insert(AcctBalanceLogModel record);

    int insertSelective(AcctBalanceLogModel record);

    AcctBalanceLogModel selectByPrimaryKey(Long balanceLogId);

    int updateByPrimaryKeySelective(AcctBalanceLogModel record);

    int updateByPrimaryKey(AcctBalanceLogModel record);
}
