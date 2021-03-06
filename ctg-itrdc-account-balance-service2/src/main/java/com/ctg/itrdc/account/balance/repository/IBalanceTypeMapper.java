package com.ctg.itrdc.account.balance.repository;

import java.util.List;
import java.util.Map;

import com.ctg.itrdc.account.balance.dao.Mapper;
import com.ctg.itrdc.account.balance.model.BalanceTypeModel;

public interface IBalanceTypeMapper extends Mapper<BalanceTypeModel>{

    List<BalanceTypeModel> selectAllBalanceType();
    
    List<BalanceTypeModel> queryByBalanceType(Map<String, Object> balanceTypeModelMap);
    
    int queryByBalanceTypeSum(BalanceTypeModel balanceTypeModel);
    
    int selectByBalanceTypeName(String balanceTypeName);
    
    int insertSelective(BalanceTypeModel record);
    
    BalanceTypeModel selectTypeById(long balanceTypeId);

}
