package com.ctg.itrdc.account.balance.repository;

import java.util.List;
import java.util.Map;

import com.ctg.itrdc.account.balance.model.BalanceFrozenModel;

/**
 * @desc 余额冻结解冻
 * @author ls
 * @date:2015-6-19
 * @version:
 */
public interface IBalanceFrozenMapper {
	BalanceFrozenModel selectByPrimaryKey(Map<String, Object> record);
	
	List<BalanceFrozenModel> queryBalFrozenByAcctId(Map<String, Object> record);
	
	int insertBalanceFrozen(BalanceFrozenModel record);
	
	int balanceUnFrozen(Map<String, Object> record);
}
