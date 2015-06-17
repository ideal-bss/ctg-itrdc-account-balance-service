package com.ctg.itrdc.account.balance.repository;

import com.ctg.itrdc.account.balance.model.BalanceSourceTypeModel;

/**
 * @desc 
 * @author ls
 * @date:2015-6-16
 * @version:
 */
public interface IBalanceSourceTypeMapper {
	BalanceSourceTypeModel selectByPrimaryKey(Long balanceSourceTypeId); 
}
