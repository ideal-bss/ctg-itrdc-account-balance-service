package com.ctg.itrdc.account.balance.repository;

import java.util.List;
import java.util.Map;

import com.ctg.itrdc.account.balance.dao.Mapper;
import com.ctg.itrdc.account.balance.model.AcctBalanceLogModel;

public interface IAcctBalanceLogMapper extends Mapper<AcctBalanceLogModel>{
	int insertSelective(AcctBalanceLogModel log);
    List<AcctBalanceLogModel> selectLogByAcctBalId(Map<String, Object> map);
}
