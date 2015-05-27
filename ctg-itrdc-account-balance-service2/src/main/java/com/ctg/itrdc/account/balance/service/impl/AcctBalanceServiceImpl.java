package com.ctg.itrdc.account.balance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctg.itrdc.account.balance.model.AcctBalanceLogModel;
import com.ctg.itrdc.account.balance.model.AcctBalanceModel;
import com.ctg.itrdc.account.balance.model.BalanceRelationModel;
import com.ctg.itrdc.account.balance.model.BalanceShareRuleModel;
import com.ctg.itrdc.account.balance.model.BalanceSourceModel;
import com.ctg.itrdc.account.balance.repository.IAcctBalanceLogMapper;
import com.ctg.itrdc.account.balance.repository.IAcctBalanceMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceShareRuleMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceTypeMapper;
import com.ctg.itrdc.account.balance.repository.IIndexTableMapper;
import com.ctg.itrdc.account.balance.repository.ITestMapper;
import com.ctg.itrdc.account.balance.service.IAcctBalanceService;
import com.ctg.itrdc.account.balance.util.SpringUtil;
@Service
@Transactional
public class AcctBalanceServiceImpl implements IAcctBalanceService{
	private IAcctBalanceMapper iAcctBalanceMapper;
	private IBalanceShareRuleMapper iBalanceShareRuleMapper;
	private IAcctBalanceLogMapper iAcctBalanceLogMapper;
	
	/*SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
	IAcctBalanceMapper iAcctBalanceMapper=sqlSession.getMapper(IAcctBalanceMapper.class);//余额账目
	IBalanceShareRuleMapper iBalanceShareRuleMapper=sqlSession.getMapper(IBalanceShareRuleMapper.class);//共享规则类型
	IAcctBalanceLogMapper iAcctBalanceLogMapper=sqlSession.getMapper(IAcctBalanceLogMapper.class);//余额账本日志
*/	
	@Override
	public void insertAcctBalance(AcctBalanceModel model,BalanceShareRuleModel shareModel) {
		// TODO Auto-generated method stub
		try {
			int count=iBalanceShareRuleMapper.selectRuleByObjectId(shareModel);
			if(count>0){
				//余额规则中，余额对象存在
				Map<String, Object> mapObject=iBalanceShareRuleMapper.selectRuleType(shareModel);
				if(mapObject!=null){
					//余额对象存在，且余额对象类型相同;修改该共享对象账本金额
					mapObject.put("BALANCE", model.getBalance());
					mapObject.put("SLICE_KEY", model.getSubAcctId());
					iAcctBalanceMapper.updateBalance(mapObject);
					//查询余额对象账本关系标识
					BalanceRelationModel relation=new BalanceRelationModel();
					relation.setAcctBalanceId(Long.parseLong(mapObject.get("ACCT_BALANCE_ID").toString()));
					relation.setObjectId(shareModel.getObjectId());
					relation.setObjectType(shareModel.getObjectType());
					relation.setSliceKey(shareModel.getSliceKey());
					long balanceRelationId=iAcctBalanceMapper.selectRelationId(relation);
					//记录余额账本日志
					insertSource(model, balanceRelationId, model.getBalance());
				}else{
					//余额对象存在，但余额对象类型不相同,新增共享规则对象
					shareModel.setAcctBalanceId((Long) mapObject.get("ACCT_BALANCE_ID"));
					iBalanceShareRuleMapper.insertSelective(shareModel);
					//更新余额账本记录
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("ACCT_BALANCE_ID", shareModel.getAcctBalanceId());
					map.put("BALANCE", model.getBalance());
					map.put("SLICE_KEY", shareModel.getSliceKey());
					iAcctBalanceMapper.updateBalance(map);
					//余额对象账本关系
					BalanceRelationModel relation=insertRelation(shareModel);
					//余额来源记录表
					insertSource(model,relation.getBalanceRelationId(),model.getBalance());
				}
			}else{
				//余额规则总，余额对象不存在；新增余额账本；新增余额共享规则
				iAcctBalanceMapper.insertSelective(model);
				//获取新增的账本ID
				Long acctBalanceId=iAcctBalanceMapper.selectAcctBalanceId(model);
				model.setAcctBalanceId(acctBalanceId);
				shareModel.setAcctBalanceId(acctBalanceId);
				
				iBalanceShareRuleMapper.insertSelective(shareModel);
				//余额对象账本关系
				BalanceRelationModel relation=insertRelation(shareModel);
				//余额来源记录表
				insertSource(model,relation.getBalanceRelationId(),model.getBalance());
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	/**
	 * 余额对象账本关系表新增
	 * @param model
	 * @throws Exception
	 */
	public BalanceRelationModel insertRelation(BalanceShareRuleModel model)throws Exception{
		BalanceRelationModel relation=new BalanceRelationModel();
		relation.setAcctBalanceId(model.getAcctBalanceId());
		relation.setObjectId(model.getObjectId());
		relation.setObjectType(model.getObjectType());
		relation.setSliceKey(model.getSliceKey());
		iAcctBalanceMapper.insertRelation(relation);
		
		System.out.println("balanceRelationId:"+relation.getBalanceRelationId());
		return relation;
	}
	/**
	 * 余额来源记录表新增
	 * @param model
	 * @param balanceRelationId
	 * @throws Exception
	 */
	public void insertSource(AcctBalanceModel model,long balanceRelationId,long amount)throws Exception{
		BalanceSourceModel source=new BalanceSourceModel();
		source.setAcctBalanceId(model.getAcctBalanceId());
		source.setAmount(amount);
		source.setCurAmount(model.getBalance());
		source.setBalanceRelationId(balanceRelationId);
		source.setBalanceSourceTypeId(model.getBalanceTypeId());
		source.setSliceKey(model.getSliceKey());
		iAcctBalanceMapper.insertSourceSelective(source);
		
		long operIncomeId=source.getOperIncomeId();
		
		insertBalanceSourceLog(source);
	}
	/**
	 * 余额账本日志
	 * @param model
	 * @throws Exception
	 */
	public void insertBalanceSourceLog(BalanceSourceModel model)throws Exception{
		AcctBalanceLogModel log=new AcctBalanceLogModel();
		log.setAcctBalanceId(model.getAcctBalanceId());
		log.setOperIncomeId(model.getOperIncomeId());
		log.setSliceKey(model.getSliceKey());
		log.setSrcAmount(model.getCurAmount());
		log.setSliceKey(model.getSliceKey());
		iAcctBalanceLogMapper.insertSelective(log);
	}
	@Override
	public void selectAcctBalance(Map<String, String> map) {
		// TODO Auto-generated method stub
		iAcctBalanceMapper.selectAcctBalance(map);
	}
	@Override
	public List<AcctBalanceModel> selectBalance(AcctBalanceModel model) {
		// TODO Auto-generated method stub
		List<AcctBalanceModel> list=new ArrayList<AcctBalanceModel>();
		try {
			list=iAcctBalanceMapper.selectBalanceByAcct(model);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public AcctBalanceModel selectBalanceById(AcctBalanceModel model) {
		// TODO Auto-generated method stub
		AcctBalanceModel acctModel=iAcctBalanceMapper.selectByPrimaryKey(model);
		return acctModel;
	}
	public IAcctBalanceMapper getiAcctBalanceMapper() {
		return iAcctBalanceMapper;
	}
	@Autowired
	public void setiAcctBalanceMapper(IAcctBalanceMapper iAcctBalanceMapper) {
		this.iAcctBalanceMapper = iAcctBalanceMapper;
	}
	public IBalanceShareRuleMapper getiBalanceShareRuleMapper() {
		return iBalanceShareRuleMapper;
	}
	@Autowired
	public void setiBalanceShareRuleMapper(
			IBalanceShareRuleMapper iBalanceShareRuleMapper) {
		this.iBalanceShareRuleMapper = iBalanceShareRuleMapper;
	}
	public IAcctBalanceLogMapper getiAcctBalanceLogMapper() {
		return iAcctBalanceLogMapper;
	}
	@Autowired
	public void setiAcctBalanceLogMapper(IAcctBalanceLogMapper iAcctBalanceLogMapper) {
		this.iAcctBalanceLogMapper = iAcctBalanceLogMapper;
	}
	
	
}
