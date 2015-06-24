package com.ctg.itrdc.account.balance.service;

import java.util.List;
import java.util.Map;

import com.ctg.itrdc.account.balance.model.BalanceTypeModel;


/**
 * @author ls
 * @date:2015-5-8
 * @version:
 */
public interface IBalanceTypeService {
	
	/**
	 * 余额类型查询
	 * @author ls
	 * @param balanceTypeModel
	 * @return
	 */
	public List<Object> queryBalanceType(BalanceTypeModel balanceTypeModel);
	
	/**
	 * 新增余额类型
	 * @author ls
	 * @param balanceTypeMode
	 */
	 public String newInsertBalanceType(BalanceTypeModel balanceTypeModel);
	 
	 /**
	  * 
	  * @desc 删除余额类型
	  * @author ls
	  * @param balTypeId
	  * @return
	  */
	 public String delBalType(String[] balTypeId);
	 
	 public String modifyBalType(BalanceTypeModel record);
}
