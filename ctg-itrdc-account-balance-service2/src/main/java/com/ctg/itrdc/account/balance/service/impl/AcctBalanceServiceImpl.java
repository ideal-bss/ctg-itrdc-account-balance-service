package com.ctg.itrdc.account.balance.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctg.itrdc.account.balance.model.AcctBalanceModel;
import com.ctg.itrdc.account.balance.repository.IAcctBalanceMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceShareRuleMapper;
import com.ctg.itrdc.account.balance.repository.IIndexTableMapper;
import com.ctg.itrdc.account.balance.repository.ITestMapper;
import com.ctg.itrdc.account.balance.service.IAcctBalanceService;
@Service
@Transactional
public class AcctBalanceServiceImpl implements IAcctBalanceService{
	private IAcctBalanceMapper iAcctBalanceMapper;
	private IIndexTableMapper iIndexTableMapper;
	private ITestMapper iTestMapper;
	private IBalanceShareRuleMapper iBalanceShareRuleMapper;
	@Override
	public void insertAcctBalance(AcctBalanceModel model) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("OBJECT_ID", model.getAcctId());//余额对象标识
			map.put("OBJECT_TYPE", model.getBalanceTypeId());//余额对象类型
			int count=iBalanceShareRuleMapper.selectRuleByObjectId(Long.parseLong(model.getAcctId().toString()));
			if(count>0){
				//余额规则中，余额对象存在
				Map<String, Object> mapObject=iBalanceShareRuleMapper.selectRuleType(map);
				if(mapObject!=null){
					//余额对象存在，且余额对象类型相同;修改该共享对象账本金额
					Long acctBalanceId=(Long) mapObject.get("ACCT_BALANCE_ID");
//					iAcctBalanceMapper.updateBalance(mapObject);
				}else{
					//余额对象存在，但余额对象类型不相同
					
				}
			}else{
				//余额规则总，余额对象不存在；新增余额账本；新增余额共享规则
				
			}
			Map<String, String> param=new HashMap<String, String>();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@Override
	public void selectAcctBalance(Map<String, String> map) {
		// TODO Auto-generated method stub
		
		
		iAcctBalanceMapper.selectAcctBalance(map);
		
	}
	public IAcctBalanceMapper getiAcctBalanceMapper() {
		return iAcctBalanceMapper;
	}
	@Autowired
	public void setiAcctBalanceMapper(IAcctBalanceMapper iAcctBalanceMapper) {
		this.iAcctBalanceMapper = iAcctBalanceMapper;
	}
	public IIndexTableMapper getiIndexTableMapper() {
		return iIndexTableMapper;
	}
	@Autowired
	public void setiIndexTableMapper(IIndexTableMapper iIndexTableMapper) {
		this.iIndexTableMapper = iIndexTableMapper;
	}
	public ITestMapper getiTestMapper() {
		return iTestMapper;
	}
	@Autowired
	public void setiTestMapper(ITestMapper iTestMapper) {
		this.iTestMapper = iTestMapper;
	}
	public IBalanceShareRuleMapper getiBalanceShareRuleMapper() {
		return iBalanceShareRuleMapper;
	}
	@Autowired
	public void setiBalanceShareRuleMapper(
			IBalanceShareRuleMapper iBalanceShareRuleMapper) {
		this.iBalanceShareRuleMapper = iBalanceShareRuleMapper;
	}

	
	
}
