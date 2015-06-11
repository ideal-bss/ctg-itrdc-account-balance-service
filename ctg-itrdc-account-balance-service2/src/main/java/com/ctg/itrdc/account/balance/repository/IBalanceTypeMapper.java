package com.ctg.itrdc.account.balance.repository;

import java.util.List;

import com.ctg.itrdc.account.balance.dao.Mapper;
import com.ctg.itrdc.account.balance.model.BalanceTypeModel;

public interface IBalanceTypeMapper extends Mapper<BalanceTypeModel>{

    List<BalanceTypeModel> selectAllBalanceType();
    
    List<Object> queryByBalanceType(BalanceTypeModel balanceTypeModel);
}
