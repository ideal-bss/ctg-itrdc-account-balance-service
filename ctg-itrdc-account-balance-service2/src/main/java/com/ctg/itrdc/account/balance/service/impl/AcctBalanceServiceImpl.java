package com.ctg.itrdc.account.balance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctg.itrdc.account.balance.dao.SliceKeyDao;
import com.ctg.itrdc.account.balance.model.AcctBalanceModel;
import com.ctg.itrdc.account.balance.model.BalanceShareRuleModel;
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
	private SliceKeyDao sliceKeyDao;
	@Override
	public void insertAcctBalance(AcctBalanceModel model,BalanceShareRuleModel shareModel) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> sliceMap=new HashMap<String, Object>();
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("OBJECT_ID", shareModel.getObjectId());//余额对象标识
			map.put("OBJECT_TYPE", shareModel.getObjectType());//余额对象类型
			int count=iBalanceShareRuleMapper.selectRuleByObjectId(shareModel.getObjectId());
			if(count>0){
				//余额规则中，余额对象存在
				Map<String, Object> mapObject=iBalanceShareRuleMapper.selectRuleType(map);
				if(mapObject!=null){
					//余额对象存在，且余额对象类型相同;修改该共享对象账本金额
//					Long acctBalanceId=(Long) mapObject.get("ACCT_BALANCE_ID");
					sliceMap.put("acctBalanceId", model.getAcctBalanceId());
					mapObject.put("BALANCE", model.getBalance());
					mapObject.put("SLICE_KEY", sliceKeyDao.getSliceKey(sliceMap));
					iAcctBalanceMapper.updateBalance(mapObject);
				}else{
					//余额对象存在，但余额对象类型不相同
					shareModel.setAcctBalanceId((Long) mapObject.get("ACCT_BALANCE_ID"));
					iBalanceShareRuleMapper.insert(shareModel);
				}
			}else{
				//余额规则总，余额对象不存在；新增余额账本；新增余额共享规则
				iBalanceShareRuleMapper.insert(shareModel);
				iAcctBalanceMapper.insert(model);
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
	public SliceKeyDao getSliceKeyDao() {
		return sliceKeyDao;
	}
	@Autowired
	public void setSliceKeyDao(SliceKeyDao sliceKeyDao) {
		this.sliceKeyDao = sliceKeyDao;
	}
	@Override
	public List<AcctBalanceModel> selectBalance(AcctBalanceModel model) {
		// TODO Auto-generated method stub
		List<AcctBalanceModel> list=new ArrayList<AcctBalanceModel>();
		list.add(iAcctBalanceMapper.selectByPrimaryKey(model));
		return list;
	}

	
	
}
