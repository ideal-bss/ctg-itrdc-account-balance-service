package com.ctg.itrdc.account.balance.repository;

import com.ctg.itrdc.account.balance.model.BalanceTypeModel;

public interface IBalanceTypeMapper {

    int deleteByPrimaryKey(Long balanceTypeId);

    int insert(BalanceTypeModel record);

    int insertSelective(BalanceTypeModel record);

    BalanceTypeModel selectByPrimaryKey(Long balanceTypeId);

    int updateByPrimaryKeySelective(BalanceTypeModel record);

    int updateByPrimaryKey(BalanceTypeModel record);
}
