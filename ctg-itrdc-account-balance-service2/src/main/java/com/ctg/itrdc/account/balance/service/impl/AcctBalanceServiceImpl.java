package com.ctg.itrdc.account.balance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctg.itrdc.account.balance.entity.AcctModel;
import com.ctg.itrdc.account.balance.entity.DevModel;
import com.ctg.itrdc.account.balance.model.AcctBalanceLogModel;
import com.ctg.itrdc.account.balance.model.AcctBalanceModel;
//import com.ctg.itrdc.account.balance.model.BalanceConfig;
import com.ctg.itrdc.account.balance.model.BalanceFrozenModel;
import com.ctg.itrdc.account.balance.model.BalancePayoutModel;
import com.ctg.itrdc.account.balance.model.BalanceRelationModel;
import com.ctg.itrdc.account.balance.model.BalanceShareRuleModel;
import com.ctg.itrdc.account.balance.model.BalanceSourceModel;
import com.ctg.itrdc.account.balance.model.BalanceSourceTypeModel;
import com.ctg.itrdc.account.balance.model.BalanceTypeModel;
import com.ctg.itrdc.account.balance.repository.IAcctBalanceLogMapper;
import com.ctg.itrdc.account.balance.repository.IAcctBalanceMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceAcctItemPayedMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceFrozenMapper;
import com.ctg.itrdc.account.balance.repository.IBalancePayoutMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceShareRuleMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceSourceMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceSourceTypeMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceTypeMapper;

import com.ctg.itrdc.account.balance.service.IAcctBalanceService;
import com.ctg.itrdc.account.balance.util.BaseUtil;
import com.ctg.itrdc.account.balance.util.SpringUtil;
@Service
@Transactional
public class AcctBalanceServiceImpl implements IAcctBalanceService{
	private Logger logger = Logger.getLogger(AcctBalanceServiceImpl.class);
	private IAcctBalanceMapper iAcctBalanceMapper;
	private IBalanceShareRuleMapper iBalanceShareRuleMapper;
	private IAcctBalanceLogMapper iAcctBalanceLogMapper;
	private IBalanceSourceMapper iBalanceSourceMapper;
	private IBalancePayoutMapper iBalancePayoutMapper;
	private IBalanceAcctItemPayedMapper iBalanceAcctItemPayedMapper;
	private IBalanceSourceTypeMapper iBalanceSourceTypeMapper;
	private IBalanceFrozenMapper iBalanceFrozenMapper;
	private IBalanceTypeMapper iBalanceTypeMapper;
	@Override
	public Map<String, Object> insertAcctBalance(AcctBalanceModel model,BalanceShareRuleModel shareModel) {
		int status = 0;
		String failReason = "存入成功！";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			/*int count=iBalanceShareRuleMapper.selectRuleByObjectId(shareModel);
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
				
			}*/
			//余额规则中，余额对象存在
			Map<String, Object> mapObject=iBalanceShareRuleMapper.selectRuleType(shareModel);
			shareModel.setCreateDate(new Date());
			shareModel.setCreateStaff("system");
			shareModel.setEffDate(model.getEffDate());
			shareModel.setExpDate(model.getExpDate());
			if (mapObject != null && mapObject.size()>0) {//存在余额共享规则
				model.setAcctBalanceId((Long) mapObject.get("ACCT_BALANCE_ID"));
				
				//查询余额账本
				AcctBalanceModel abm = iAcctBalanceMapper.selectByPrimaryKey(model);
				
				//余额使用规则是否一致
				if (abm != null) {
					//余额对象存在，且余额对象类型相同;修改该共享对象账本金额
					mapObject.put("BALANCE", model.getBalance());
					mapObject.put("SLICE_KEY", model.getSubAcctId());
					iAcctBalanceMapper.updateBalance(mapObject);
					/*//查询余额对象账本关系标识
					BalanceRelationModel relation=new BalanceRelationModel();
					relation.setAcctBalanceId(Long.parseLong(mapObject.get("ACCT_BALANCE_ID").toString()));
					relation.setObjectId(shareModel.getObjectId());
					relation.setObjectType(shareModel.getObjectType());
					relation.setSliceKey(shareModel.getSliceKey());
					long balanceRelationId=iAcctBalanceMapper.selectRelationId(relation);*/
					//记录余额账本日志
					insertSource(model, null, model.getBalance());
				}else{
					status = 1;
					failReason = "此余额对象标识已经存在余额信息，但子账户标识和当前输入不一致，余额存入失败！";
				}
			}else{//不存在余额共享规则
				//查询余额账本
				long acctBalanceId = insertBalance(model);
				shareModel.setAcctBalanceId(acctBalanceId);
				iBalanceShareRuleMapper.insertSelective(shareModel);
				//余额对象账本关系
				//BalanceRelationModel relation=insertRelation(shareModel);
				//余额来源记录表
				insertSource(model,null,model.getBalance());
			}
			
		} catch (Exception e) {
			failReason = "其他异常！";
			e.printStackTrace();
		}finally {
			resultMap.put("status", status);
			resultMap.put("failReason", failReason);
		}
		return resultMap;
	}
	@Override
	public void insertBalance(AcctBalanceModel model,BalanceShareRuleModel shareModel){
		long objectId=shareModel.getObjectId();
		String objectType=shareModel.getObjectType();
		long subAcctId=model.getSubAcctId();
		AcctModel acct=null;
		List<AcctBalanceModel> balanceList=null;
		boolean devBool=false; 
		boolean balanceBool=false;
		try {
			acct=setAcct(subAcctId);
			if(acct!=null){
				acct=setDev(acct, objectId, objectType, subAcctId);
				List<DevModel> devList=acct.getDevModelList();
				if(devList!=null&&devList.size()>0){
					
					for(DevModel dev:devList){
						if(dev.getObjectId()==objectId&&dev.getObjectType().equals(objectType)){
							devBool=true;
							balanceList=dev.getBalanceList();
							for(AcctBalanceModel balance:balanceList){
								if(balance.getBalanceTypeId()==model.getBalanceTypeId()
										&&balance.getCycleLower()==model.getCycleLower()
										&&balance.getCycleUpper()==model.getCycleUpper()
										&&balance.getCycleType()==model.getCycleType()){
									//修改账本金额
									balanceBool=true;
									updateBalance(balance.getAcctBalanceId(), model.getBalance(), balance.getSliceKey());
									//日志
									insertSource(balance, selectRelationId(balance.getAcctBalanceId(), objectId, objectType, subAcctId), model.getBalance());
								}
							}
						}
					}
					//对象标识和对象类型都不相同
					if(devBool==false||balanceBool==false){
						insertBalanceRuleLog(model, shareModel);
					}
				}else{
					insertBalanceRuleLog(model, shareModel);
				}
			}else{
				insertBalanceRuleLog(model, shareModel);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 新增 账本  共享规则  日志
	 * @param model
	 * @param shareModel
	 * @throws Exception
	 */
	public void insertBalanceRuleLog(AcctBalanceModel model,BalanceShareRuleModel shareModel)throws Exception{
		//新增账本
		long balanceIdAdd=insertBalance(model);
		//新增共享规则
		shareModel.setAcctBalanceId(balanceIdAdd);
		long ruleIdAdd=insertShareRule(shareModel);
		//日志
		//余额对象账本关系
		BalanceRelationModel relation=insertRelation(shareModel);
		//余额来源记录表
		insertSource(model,relation.getBalanceRelationId(),model.getBalance());
	}
	/**
	 * 新增账本
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long insertBalance(AcctBalanceModel model)throws Exception{
		iAcctBalanceMapper.insertSelective(model);
		return model.getAcctBalanceId();
	}
	/**
	 * 共享规则新增
	 * @param shareModel
	 * @throws Exception
	 */
	public long insertShareRule(BalanceShareRuleModel shareModel) throws Exception{
		iBalanceShareRuleMapper.insertSelective(shareModel);
		return shareModel.getShareRuleId();
	}
	/**
	 * 余额账本金额修改
	 * @param acctBalanceId 账本ID
	 * @param balance 金额
	 * @param subAcctId 账户ID
	 * @throws Exception
	 */
	public void updateBalance(long acctBalanceId,long balance,long subAcctId)throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("ACCT_BALANCE_ID", acctBalanceId);
		map.put("BALANCE", balance);
		map.put("SLICE_KEY", subAcctId);
		iAcctBalanceMapper.updateBalance(map);
	}
	/**
	 * 查询余额对象关系标识
	 * @param acctBalanceId 账本ID
	 * @param objectId 对象标识
	 * @param objectType 对象类型
	 * @param subAcctId 账户ID
	 * @return 关系标识
	 * @throws Exception
	 */
	public long selectRelationId(long acctBalanceId,long  objectId,String objectType,long subAcctId)throws Exception{
		BalanceRelationModel relation=new BalanceRelationModel();
		relation.setAcctBalanceId(acctBalanceId);
		relation.setObjectId(objectId);
		relation.setObjectType(objectType);
		relation.setSliceKey(subAcctId);
		long balanceRelationId=iAcctBalanceMapper.selectRelationId(relation);
		return balanceRelationId;
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
	public void insertSource(AcctBalanceModel model,Long balanceRelationId,long amount)throws Exception{
		Date curDate = new Date();
		BalanceSourceModel source=new BalanceSourceModel();
		source.setAcctBalanceId(model.getAcctBalanceId());
		source.setAmount(amount);
		source.setCurAmount(model.getBalance());
		//source.setBalanceRelationId(balanceRelationId);
		source.setBalanceSourceTypeId(model.getBalanceTypeId());
		source.setSliceKey(model.getSliceKey());
		source.setOperType("");
		source.setStaffId("system");
		source.setOperDate(curDate);
		source.setCurStatusDate(curDate);
		source.setStatusCd("1");
		source.setStatusDate(curDate);
		source.setSourceDesc("balanceAdd");
		iAcctBalanceMapper.insertSourceSelective(source);
		
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
		log.setStatusCd("1");
		log.setStatusDate(new Date());
		iAcctBalanceLogMapper.insertSelective(log);
	}
	/**
	 * 账户查询设备
	 * @param subAcctId 账户ID
	 * @throws Exception
	 */
	public AcctModel setAcct(long subAcctId)throws Exception{
		AcctModel acct=new AcctModel();
		acct.setAcctId(subAcctId);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("subAcctId", subAcctId);
		Integer balanceId=iAcctBalanceMapper.selectBalanceByAcctId(map);
		map.clear();
		map.put("subAcctId", subAcctId);
		map.put("objectType", 0);
		map.put("acctBalanceId", balanceId);
		List<BalanceShareRuleModel> rule=iAcctBalanceMapper.selectDevByBalanceId(map);
		List<DevModel> devList=new ArrayList<DevModel>();
		for(BalanceShareRuleModel ruleModel:rule){
			DevModel dev=new DevModel();
			dev.setObjectId(ruleModel.getObjectId());
			dev.setObjectType(ruleModel.getObjectType());
			devList.add(dev);
		}
		acct.setDevModelList(devList);
		return acct;
	}
	/**
	 * 设备查询账本
	 * @param objectId 设备ID
	 * @param objectType 设备类型
	 * @param subAcctId 账户ID
	 * @throws Exception
	 */
	public AcctModel setDev(AcctModel acct,long objectId,String objectType,long subAcctId)throws Exception{
		List<DevModel> devList=acct.getDevModelList();
		for(DevModel dev:devList){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("objectId", dev.getObjectId());
			map.put("objectType", dev.getObjectType());
			map.put("subAcctId", subAcctId);
			List<Integer> balanceIdList=iAcctBalanceMapper.selectBalanceByDev(map);
			List<AcctBalanceModel> balanceList=new ArrayList<AcctBalanceModel>();
			for(Integer balanceId: balanceIdList){
				map.clear();
				map.put("acctBalanceId", balanceId);
				map.put("subAcctId", subAcctId);
				AcctBalanceModel balance=iAcctBalanceMapper.selectBalanceById(map);
				balanceList.add(balance);
			}
			dev.setBalanceList(balanceList);
		}
		return acct;
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
	
	/**
	 * 
	 * @desc 余额查询
	 * @author ls
	 * @param model
	 * @return
	 */
	@Override
	public Map<String, Object> queryBalance(Map<String, Object> model) {
		
		logger.debug("queryBalance()");
		
		List<Object> balanceResultList = new ArrayList<Object>();
		Map<String, Object> balanceResultMap = null;
		Map<String, Object> balanceFrozenMap = new HashMap<String, Object>();
		Map<String, Object> returnResult = new HashMap<String, Object>();
		BalanceTypeModel balTypeModel = null;
		int balFrozenAmount = 0;
		long balanceSum = 0;
		long frozenAmountSum = 0;
		try {
			logger.debug("查询余额类型信息。");
//			BalanceConfig balanceConfig =BalanceConfig.getInstance();
			
			logger.debug("查询余额账本信息。输入参数：" + model);
			List<AcctBalanceModel> acctBalList = iAcctBalanceMapper.selectByAcctId(model);
			for (AcctBalanceModel acctBalanceModel : acctBalList) {
				balTypeModel = iBalanceTypeMapper.selectTypeById(acctBalanceModel.getBalanceTypeId());
//				balTypeModel = balanceConfig.getByTypeId(acctBalanceModel.getBalanceTypeId());
				//查询余额冻结记录 2 代表冻结，1未冻结
				balanceFrozenMap.clear();
				balanceFrozenMap.put("acctBalanceId", acctBalanceModel.getAcctBalanceId());
				balanceFrozenMap.put("frozenState", 2);
				balanceFrozenMap.put("acctId", acctBalanceModel.getSubAcctId());
				balanceFrozenMap.put("sliceKey", acctBalanceModel.getSliceKey());
				balFrozenAmount = iBalanceFrozenMapper.queryAcctBalFrozenSum(balanceFrozenMap);
				
				balanceResultMap = new HashMap<String, Object>();
				//余额账本标识
				balanceResultMap.put("acctBalanceId", acctBalanceModel.getAcctBalanceId());
				//余额类型标识
				balanceResultMap.put("balanceTypeId", acctBalanceModel.getBalanceTypeId());
				//余额类型名称
				balanceResultMap.put("balanceTypeName", balTypeModel.getBalanceTypeName());
				//生效时间
				balanceResultMap.put("effDate", BaseUtil.dateToString(acctBalanceModel.getEffDate(), "yyyy-MM-dd"));
				//失效时间
				balanceResultMap.put("expDate", BaseUtil.dateToString(acctBalanceModel.getExpDate(), "yyyy-MM-dd"));
				//余额对象标识
				balanceResultMap.put("objectId", model.get("objectId"));
				//单个账本余额
				balanceResultMap.put("balance", Double.parseDouble(String.valueOf(acctBalanceModel.getBalance()))/100);
				//冻结余额
				balanceResultMap.put("frozenAmount", Double.parseDouble(String.valueOf(balFrozenAmount))/100);
				//账户ID
				balanceResultMap.put("acctId", acctBalanceModel.getSubAcctId());
				balanceResultList.add(balanceResultMap);
				
				balanceSum +=acctBalanceModel.getBalance();
				frozenAmountSum +=balFrozenAmount;
			}
			int pageSize = balanceResultList.size();
			Map<String, Object> amountSumMap = new HashMap<String, Object>();
			amountSumMap.put("acctBalanceId", "账本余额总和：");
			amountSumMap.put("balanceTypeId", Double.parseDouble(String.valueOf(balanceSum))/100+" 元");
			amountSumMap.put("balanceTypeName", "冻结余额总和：");
			amountSumMap.put("effDate", Double.parseDouble(String.valueOf(frozenAmountSum))/100+" 元");
			amountSumMap.put("expDate", "平均账本余额：");
			amountSumMap.put("balance", "平均冻结余额：");
			if (pageSize>0) {
				amountSumMap.put("objectId", BaseUtil.formatDouble((Double.parseDouble(String.valueOf(balanceSum))/balanceResultList.size())/100,2)+" 元");
				amountSumMap.put("frozenAmount", BaseUtil.formatDouble((Double.parseDouble(String.valueOf(frozenAmountSum))/balanceResultList.size())/100,2)+" 元");
			}else{
				amountSumMap.put("objectId", Double.parseDouble(String.valueOf(balanceSum))/100+" 元");
				amountSumMap.put("frozenAmount", Double.parseDouble(String.valueOf(frozenAmountSum))/100+" 元");
			}
			
			List<Map<String, Object>> sumList = new ArrayList<Map<String,Object>>();
			sumList.add(amountSumMap);
			int total = iAcctBalanceMapper.selectByAcctIdSum(model);
			returnResult.put("total", total);
			returnResult.put("rows", balanceResultList);
			returnResult.put("footer", sumList);
			logger.debug(returnResult);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnResult;
	}
	
	/**
	 * @desc 余额支取
	 * @author ls
	 * @param model
	 * @return
	 */
	@Override
	public String balanceDraw(Map<String, Object> model) {
		logger.debug("-----余额支取----- balanceDraw().");
		Date currDate = new Date(); //当前时间
		String flagHint = null;//返回值
		long sumBalance = 0;
		List<Long> acctBalIdList = new ArrayList<Long>();
		List<Long> acctBalList = new ArrayList<Long>();
		try {
			long acctId = Long.parseLong(String.valueOf(model.get("acctId")));
			String []acctBalanceIdArray = String.valueOf(model.get("acctBalanceIdArray")).split(",");
			model.put("sliceKey", acctId);
			//根据余额对象、余额对象类型、账户标识  查询余额账本
			AcctBalanceModel acctBalModelParam = new AcctBalanceModel();
			acctBalModelParam.setSubAcctId(acctId);
			acctBalModelParam.setSliceKey(acctId);
			for (int i = 0; i < acctBalanceIdArray.length; i++) {
				long acctBalanceId = Long.parseLong(acctBalanceIdArray[i]);
				acctBalModelParam.setAcctBalanceId(acctBalanceId);
				AcctBalanceModel acctBalanceModel = iAcctBalanceMapper.selectByPrimaryKey(acctBalModelParam);
				Date balEffDate = acctBalanceModel.getEffDate();
				Date balExpDate = acctBalanceModel.getExpDate();
				logger.debug("余额账本标识:" + acctBalanceModel.getAcctBalanceId());
				
				//余额账本 有效状态
				if (currDate.after(balEffDate) && currDate.before(balExpDate)) {
					//BalanceConfig balanceConfig =BalanceConfig.getInstance();
					BalanceTypeModel balTypeModel = iBalanceTypeMapper.selectTypeById(acctBalanceModel.getBalanceTypeId());
					logger.debug("余额类型名称:" + balTypeModel.getBalanceTypeName() + 
								"-->是否专款专用:" + (balTypeModel.getSpePaymentId()==null || balTypeModel.getSpePaymentId()==0?"N":"Y"));
					//专款专用余额 不能支取
					if (balTypeModel.getSpePaymentId() == null || balTypeModel.getSpePaymentId() == 0) {
						acctBalIdList.add(acctBalanceModel.getAcctBalanceId());
						acctBalList.add(acctBalanceModel.getBalance());
						sumBalance += acctBalanceModel.getBalance();
					}
				}
			}
			
			
			//支取金额
			long drawAmount = Long.parseLong(String.valueOf(model.get("drawAmount")));
			
			//支取金额大于等于账本总余额，才可以支取。否则不能支取
			if (sumBalance>=drawAmount) {
				boolean flag = true;
				long balance = drawAmount;
				long acctBalance = drawAmount;
				logger.debug("开始支取余额.");
				List<BalanceSourceModel> balanceSourceList = null;
				BalanceSourceModel balSourceModel = null;
				for (int i = 0; i < acctBalIdList.size() && flag; i++) {
					long acctBalanceId = acctBalIdList.get(i);
					
					logger.debug("更新余额账本余额.");
					long operAfterBalance = 0L;
					long payoutBal = 0L;
					AcctBalanceModel acctBalModel = new AcctBalanceModel();
					acctBalModel.setAcctBalanceId(acctBalanceId);
					if (acctBalance>acctBalList.get(i)) {
						acctBalance -= acctBalList.get(i);
						operAfterBalance = 0L;
						payoutBal = acctBalList.get(i);
					}else{
						operAfterBalance = acctBalList.get(i) - acctBalance;
						payoutBal = acctBalance;
					}
					acctBalModel.setRemark("支取 :" + (double)payoutBal/100
									+ " 元。操作时间:" + BaseUtil.dateToString(currDate, "yyyy-MM-dd HH:mm:ss"));
					acctBalModel.setBalance(operAfterBalance);
					iAcctBalanceMapper.updateByPrimaryKeySelective(acctBalModel);
					
					logger.debug("记录余额支出日志.");
					long operPayoutId = newBalancePayoutLog(acctBalanceId, null, 
							acctBalList.get(i), operAfterBalance, "drawBalance",acctId);
					
					logger.debug("查询余额来源信息,并更新余额来源表金额，记录余额账本日志.");
					balSourceModel = new BalanceSourceModel();
					balSourceModel.setAcctBalanceId(acctBalanceId);
					balSourceModel.setSliceKey(acctId);
					balanceSourceList = iBalanceSourceMapper.selectByAcctBalanceId(balSourceModel);
					for (BalanceSourceModel balanceSource : balanceSourceList) {
						BalanceSourceModel balSource = new BalanceSourceModel();
						balSource.setOperDate(currDate);
						balSource.setOperIncomeId(balanceSource.getOperIncomeId());
						
						//余额账本日志
						if (balance>balanceSource.getCurAmount()) {
							balance -= balanceSource.getCurAmount();
							balSource.setCurAmount(0L);
							iBalanceSourceMapper.updateByPrimaryKey(balSource);
							
							newAcctBalanceLog(balanceSource, operPayoutId, balanceSource.getCurAmount());
						}else{
							balSource.setCurAmount(balanceSource.getCurAmount()-balance);
							iBalanceSourceMapper.updateByPrimaryKey(balSource);
							
							newAcctBalanceLog(balanceSource, operPayoutId, balance);
							flag = false;
							break;
						}
					}
					
					
				}
				
				flagHint = "成功支取金额：" + Double.valueOf(drawAmount) + "元。";
			}else if(flagHint == null){
				flagHint = "可支取余额不足，操作失败！";
			}
			logger.debug(flagHint);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flagHint;
	}
	
	/**
	 * 余额转账
	 */
	@Override
	public String balanceTransfer(Map<String, Object> map){
		logger.debug("balanceTransfer().");
		String flagHint = null;
		try {
			long orgiAcctBalId = Long.parseLong(String.valueOf(map.get("acctBalanceId")));
			long origAcctId = Long.parseLong(String.valueOf(map.get("origAcctId")));
			AcctBalanceModel orgiRecord = new AcctBalanceModel();
			orgiRecord.setAcctBalanceId(orgiAcctBalId);
			AcctBalanceModel orgiAcctBalModel = iAcctBalanceMapper.selectByPrimaryKey(orgiRecord);
			if (orgiAcctBalModel != null) {
				Map<String, Object> goalMap = new HashMap<String, Object>();
				long acctId = Long.parseLong(String.valueOf(map.get("acctId")));
				goalMap.put("acctId", acctId);
				goalMap.put("balanceTypeId", map.get("origBalanceTypeId"));
				goalMap.put("objectType", map.get("objectType"));
				goalMap.put("objectId", map.get("objectId"));
				List<AcctBalanceModel> goalAcctBalModelList = iAcctBalanceMapper.selectByAcctId(goalMap);
				if (goalAcctBalModelList!= null && goalAcctBalModelList.size()>0) {
					for (AcctBalanceModel goalAcctBalModel : goalAcctBalModelList) {
						if (orgiAcctBalModel.getAcctBalanceId() != goalAcctBalModel.getAcctBalanceId()) {
							//BalanceConfig balanceConfig =BalanceConfig.getInstance();
							BalanceTypeModel orgiBalTypeModel = iBalanceTypeMapper.selectTypeById(orgiAcctBalModel.getBalanceTypeId());
							if (orgiBalTypeModel.getSpePaymentId() == null || orgiBalTypeModel.getSpePaymentId() == 0) {
								BalanceTypeModel goalBalTypeModel = iBalanceTypeMapper.selectTypeById(goalAcctBalModel.getBalanceTypeId());
								if (goalBalTypeModel.getSpePaymentId() == null || goalBalTypeModel.getSpePaymentId() == 0) {
									long amount = Long.parseLong(String.valueOf(map.get("amount")));
									if (orgiAcctBalModel.getBalance()>=amount) {
										logger.debug("余额转账更新源余额账本，并记录支出日志。");
										String currDate = BaseUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
										
										AcctBalanceModel updateOrgiAcctBal = new AcctBalanceModel();
										updateOrgiAcctBal.setAcctBalanceId(orgiAcctBalModel.getAcctBalanceId());
										updateOrgiAcctBal.setBalance(orgiAcctBalModel.getBalance()-amount);
										updateOrgiAcctBal.setRemark("paid Balance :" + (double)amount/100
													+ " dollar.operation date:" + currDate);
										iAcctBalanceMapper.updateByPrimaryKeySelective(updateOrgiAcctBal);
										logger.debug("更新余额来源，并记录余额账本日志！");
										long operPayoutId = newBalancePayoutLog(orgiAcctBalModel.getAcctBalanceId(), null, 
												orgiAcctBalModel.getBalance(), orgiAcctBalModel.getBalance()-amount, "balance transfer", origAcctId);
										updateBalanceSource(orgiAcctBalModel.getAcctBalanceId(), amount, operPayoutId, origAcctId);

										logger.debug("余额转账目的余额账本，并记录余额来源记录。");
										AcctBalanceModel updateGoalAcctBal = new AcctBalanceModel();
										updateGoalAcctBal.setAcctBalanceId(goalAcctBalModel.getAcctBalanceId());
										AcctBalanceModel updateGoalAcctBalById = iAcctBalanceMapper.selectByPrimaryKey(updateGoalAcctBal);
										updateGoalAcctBalById.setBalance(goalAcctBalModel.getBalance()+amount);
										updateGoalAcctBalById.setRemark("deposit Balance :" + (double)amount/100
													+ " dollar.operation date:" + currDate);
										iAcctBalanceMapper.updateByPrimaryKeySelective(updateGoalAcctBalById);											
										logger.debug("记录来源记录.");
										newBalanceSource(goalAcctBalModel.getAcctBalanceId(), amount, amount, "balance transfer save.", acctId);
										
										flagHint = "余额转账成功！";
									}else {
										flagHint = "源账本余额不足！";
									}
								}else{
									flagHint = "目的账本为专款专用账本！";
								}
							}else{
								flagHint = "源账本为专款专用账本！";
							}
							
						}else{
							flagHint = "同一账本之间不能进行转账！";
						}
					}
				}else{
					flagHint = "目的账本不存在！";
				}
			}else{
				flagHint = "源账本不存在！";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug(flagHint);
		return flagHint;
	}
	
	/**
	 * 余额冲正
	 */
	@Override
	public String balanceReverse(Map<String, Object> record) {
		logger.debug("balanceReverse().");
		String hint = "";
		Date currDate = new Date();
		int successCnt = 0;
		int totalCnt = 0;
		try {
			logger.debug("余额冲正参数：" + record);
			BalanceSourceModel balSourceRecord = new BalanceSourceModel();
			long sliceKey = Long.parseLong(String.valueOf(record.get("sliceKey")));
			String operIncomeId[] = String.valueOf(record.get("operIncomeId")).split("-");
			for (int i = 0; i < operIncomeId.length; i++) {
				totalCnt ++;
				logger.debug("根据来源操作流水查询余额来源记录。operIncomeId:" + operIncomeId[i]);
				balSourceRecord.setOperIncomeId(Long.parseLong(operIncomeId[i]));
				balSourceRecord.setSliceKey(sliceKey);
				BalanceSourceModel balSourceModel = iBalanceSourceMapper.selectByPrimaryKey(balSourceRecord);
				if (balSourceModel != null) {
					if (balSourceModel.getCurAmount() > 0 && balSourceModel.getAmount() == balSourceModel.getCurAmount()) {
						//没有返销接口，暂时不做。
						logger.debug("调用返销账接口，进行账单返销。");
						
						logger.debug("修改余额账本余额.");
						AcctBalanceModel acctBalanceRecord = new AcctBalanceModel();
						acctBalanceRecord.setAcctBalanceId(balSourceModel.getAcctBalanceId());
						acctBalanceRecord.setSliceKey(sliceKey);
						AcctBalanceModel acctBalanceModel = iAcctBalanceMapper.selectByPrimaryKey(acctBalanceRecord);
						acctBalanceModel.setBalance(acctBalanceModel.getBalance()-balSourceModel.getCurAmount());
						acctBalanceModel.setRemark("balance reverse." + (double)balSourceModel.getCurAmount()/100
									+ " dollar.operation date:" + currDate);
						iAcctBalanceMapper.updateByPrimaryKeySelective(acctBalanceModel);
						
						logger.debug("修改账本余额来源记录.");
						balSourceRecord.setCurAmount(0L);
						balSourceRecord.setOperDate(currDate);
						iBalanceSourceMapper.updateByPrimaryKey(balSourceRecord);
						
						logger.debug("记录余额支出日志.");
						long operPayoutId = newBalancePayoutLog(balSourceModel.getAcctBalanceId(), 0L, 
								acctBalanceModel.getBalance(), acctBalanceModel.getBalance()-balSourceModel.getCurAmount(), 
								"balance reverse", sliceKey);
						logger.debug("记录余额账本日志.");
						newAcctBalanceLog(balSourceModel, operPayoutId, balSourceModel.getCurAmount());
						successCnt ++;
					}
				}
			}
			hint = "冲正已完成，其中成功：" + successCnt + "条,失败：" + (totalCnt-successCnt) + "条！";
		} catch (Exception e) {
			hint = "冲正出错！";
			e.printStackTrace();
		} finally {
			logger.debug(hint);
		}
		
		return hint;
	}
	
	/**
	 * 查询余额账本日志
	 */
	@Override
	public Map<String, Object> queryAcctBalanceLog(Map<String, Object> map) {
		logger.debug("acctBalanceLog().");
		List<Object> resultList = new ArrayList<Object>();
		Map<String, Object> resultMap = null;
		try {
			logger.debug("根据账户标识和余额类型查询余额账本。");
			//根据账户标识和余额类型查询余额账本。
			long acctId = Long.parseLong(String.valueOf(map.get("acctId")));
			long balanceTypeId = Long.parseLong(String.valueOf(map.get("balanceTypeId")));
			Map<String, Object> acctBalMap = new HashMap<String, Object>();
			acctBalMap.put("acctId", acctId);
			acctBalMap.put("balanceTypeId", balanceTypeId);
			acctBalMap.put("sliceKey", acctId);
			acctBalMap.put("sliceKey", acctId);
			acctBalMap.put("sliceKey", acctId);
			List<AcctBalanceModel> acctBalModelList = iAcctBalanceMapper.selectByAcctId(acctBalMap);
			double sumBalance = 0;
			List<Object> acctBalList = new ArrayList<Object>();
			if (acctBalModelList != null && acctBalModelList.size() > 0) {
				Map<String, Object> acctBalMapResult = null;
				for (AcctBalanceModel acctBalanceModel : acctBalModelList) {
					//余额类型查询
					//BalanceConfig balanceConfig =BalanceConfig.getInstance();
					BalanceTypeModel balanceTypeModel=iBalanceTypeMapper.selectTypeById(acctBalanceModel.getBalanceTypeId());
					double balance = Double.parseDouble(String.valueOf(acctBalanceModel.getBalance()))/100;
					acctBalMapResult = new HashMap<String, Object>();
					acctBalMapResult.put("acctId", acctId);
					acctBalMapResult.put("acctBalanceId", acctBalanceModel.getAcctBalanceId());
					acctBalMapResult.put("acctBalance", balance);
					acctBalMapResult.put("balanceTypeId", acctBalanceModel.getBalanceTypeId());
					acctBalMapResult.put("balanceTypeName", balanceTypeModel.getBalanceTypeName());
					acctBalList.add(acctBalMapResult);
					sumBalance = BaseUtil.add(sumBalance, balance);
				}
			}
			int total = iAcctBalanceMapper.selectByAcctIdSum(acctBalMap);
			Map<String, Object> footerMap = new HashMap<String, Object>();
			footerMap.put("acctBalance","总余额：" + sumBalance + "元");
			if (acctBalList != null && acctBalList.size()>0) {
				footerMap.put("balanceTypeName","平均余额：" + BaseUtil.formatDouble(sumBalance/acctBalList.size(),2) + "元");
			}else{
				footerMap.put("balanceTypeName","平均余额：" + 0 + "元");
			}
			List<Map<String, Object>> footerList = new ArrayList<Map<String,Object>>();
			footerList.add(footerMap);

			resultMap = new HashMap<String, Object>();
			resultMap.put("rows", acctBalList);
			resultMap.put("total", total);
			resultMap.put("footer", footerList);
			/*if (acctBalFlag) {
				Map<String, Object> hintMap = new HashMap<String, Object>();
				hintMap.put("errorInfo", "余额账本日志不存在！");
				resultList.add(hintMap);
			}*/
			logger.debug(resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 余额来源记录查询
	 */
	@Override
	public Map<String, Object> acctBalLogbalSourceQuery(Map<String, Object> record) {
		logger.debug("acctBalLogbalSourceQuery().");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			logger.debug("根据账本标识查询余额来源记录.参数：" + record);
			List<Map<String, Object>> rowsList = new ArrayList<Map<String,Object>>();
			Map<String, Object> rowsMap = null;
			double amountSum = 0;
			double curAmountSum = 0;
			List<BalanceSourceModel> balSourceList = iBalanceSourceMapper.selectSourceByAcctBalLog(record);
			for (BalanceSourceModel balSourceRecord : balSourceList) {
				double amount = Double.parseDouble(String.valueOf(balSourceRecord.getAmount()))/100;
				double curAmount = Double.parseDouble(String.valueOf(balSourceRecord.getCurAmount()))/100;
				//余额来源类型
				BalanceSourceTypeModel balSourceTypeModel = 
							iBalanceSourceTypeMapper.selectByPrimaryKey(balSourceRecord.getBalanceSourceTypeId());
				rowsMap = new HashMap<String, Object>();
				rowsMap.put("operIncomeId", balSourceRecord.getOperIncomeId());
				rowsMap.put("operType", balSourceRecord.getOperType());
				rowsMap.put("staffId", balSourceRecord.getStaffId());
				if (balSourceRecord.getOperDate() == null) {
					rowsMap.put("operDate", null);
				}else{
					rowsMap.put("operDate", BaseUtil.dateToString(balSourceRecord.getOperDate(), "yyyy-MM-dd HH:mm:ss"));
				}
				
				rowsMap.put("amount", amount);
				rowsMap.put("curAmount", curAmount);
				rowsMap.put("balanceSourceTypeId", balSourceRecord.getBalanceSourceTypeId());
				if (balSourceTypeModel != null) {
					rowsMap.put("balanceSourceTypeDesc", balSourceTypeModel.getBalanceSourceTypeDesc());
				}
				
				rowsList.add(rowsMap);
				amountSum = BaseUtil.add(amountSum, amount);
				curAmountSum = BaseUtil.add(curAmountSum, curAmount);
			}
			List<Map<String, Object>> footerList = new ArrayList<Map<String,Object>>();
			Map<String, Object> footerMap = new HashMap<String, Object>();
			footerMap.put("operIncomeId", "原总金额：");
			footerMap.put("operType", amountSum + "元");
			footerMap.put("amount", "余剩余总金额：");
			footerMap.put("curAmount", curAmountSum + "元");
			footerMap.put("staffId", "源平均金额：");
			footerMap.put("balanceSourceTypeId", "剩余平均金额：");
			if (rowsList != null && rowsList.size()>0) {
				footerMap.put("operDate", BaseUtil.formatDouble(amountSum/rowsList.size(),2) + "元");
				footerMap.put("balanceSourceTypeDesc", BaseUtil.formatDouble(curAmountSum/rowsList.size(),2) + "元");
			}else{
				footerMap.put("curAmount", amountSum + "元");
				footerMap.put("balanceSourceTypeDesc", curAmountSum + "元");
			}
			
			footerList.add(footerMap);
			int total = iBalanceSourceMapper.selectSourceByAcctBalLogTotal(record);
			resultMap.put("rows", rowsList);
			resultMap.put("total", total);
			resultMap.put("footer", footerList);
			logger.debug(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 余额支出记录
	 */
	@Override
	public Map<String, Object> acctBalLogbalPayOutQuery(Map<String, Object> record) {
		logger.debug("acctBalLogbalPayOutQuery().");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			logger.debug("根据余额账本日志和余额来源记录查询余额支出记录。");
			List<Map<String, Object>> rowsList = new ArrayList<Map<String,Object>>();
			Map<String, Object> rowsMap = null;
			double altAmountSum = 0;
			double balanceSum = 0;
			
			List<BalancePayoutModel> balPayOutList = iBalancePayoutMapper.selectPayOutByAcctBalLog(record);
			for (BalancePayoutModel balancePayoutModel : balPayOutList) {
				double amount = Double.parseDouble(String.valueOf(balancePayoutModel.getAmount()))/100;
				double balance = Double.parseDouble(String.valueOf(balancePayoutModel.getBalance()))/100;
				double altAmount = BaseUtil.sub(amount, balance);
				rowsMap = new HashMap<String, Object>();
				rowsMap.put("operPayoutId", balancePayoutModel.getOperPayoutId());
				rowsMap.put("altAmount", altAmount);
				rowsMap.put("billId", balancePayoutModel.getBillId());
				rowsMap.put("extSerialId", balancePayoutModel.getExtSerialId());
				rowsMap.put("operType", balancePayoutModel.getOperType());
				rowsMap.put("staffId", balancePayoutModel.getStaffId());
				rowsMap.put("operDate", BaseUtil.dateToString(balancePayoutModel.getOperDate(), "yyyy-MM-dd HH:mm:ss"));
				rowsMap.put("balance", balance);
				rowsList.add(rowsMap);
				altAmountSum = BaseUtil.add(altAmountSum, altAmount);
				balanceSum = BaseUtil.add(balanceSum, balance);
			}
			
			List<Map<String, Object>> footerList = new ArrayList<Map<String,Object>>();
			Map<String, Object> footerMap = new HashMap<String, Object>();
			footerMap.put("operPayoutId", "补退总金额：");
			footerMap.put("altAmount", altAmountSum + "元");
			footerMap.put("operType", "支出后总余额：");
			footerMap.put("staffId", balanceSum + "元");
			footerMap.put("billId", "补退平均金额：");
			footerMap.put("operDate", "支出后平均余额：");
			if (rowsList != null && rowsList.size()>0) {
				footerMap.put("extSerialId", BaseUtil.formatDouble(altAmountSum/rowsList.size(),2) + "元");
				footerMap.put("balance", BaseUtil.formatDouble(balanceSum/rowsList.size(),2) + "元");
			}else{
				footerMap.put("extSerialId", altAmountSum + "元");
				footerMap.put("balance", balanceSum + "元");
			}
			
			footerList.add(footerMap);
			int total = iBalancePayoutMapper.selectPayOutByAcctBalLogTotal(record);
			resultMap.put("rows", rowsList);
			resultMap.put("total", total);
			resultMap.put("footer", footerList);
			logger.debug(resultMap);
		} catch (Exception e) {
			
		}
		return resultMap;
	}
	
	/**
	 * 查询余额冻结记录
	 */
	@Override
	public Map<String, Object> queryBalFrozen(Map<String,Object> mapQuery){
		logger.debug("queryBalFrozen().");
		List<Object> resultList = new ArrayList<Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		int total = 0;
		long frozenAmountSum = 0L;
		try {
			long acctId = Long.parseLong(String.valueOf(mapQuery.get("acctId")));
			long acctBalanceId = Long.parseLong(String.valueOf(mapQuery.get("acctBalanceId")));
			
			String rows = String.valueOf(mapQuery.get("rows"));
			String page = String.valueOf(mapQuery.get("page"));
			
			Map<String, Object> acctBalMap = new HashMap<String, Object>();
			acctBalMap.put("acctId", acctId);
			acctBalMap.put("sliceKey", acctId);
			acctBalMap.put("acctBalanceId", acctBalanceId);
			logger.debug("query Acct Bal Frozen Map." + acctBalMap);
			List<AcctBalanceModel> acctBalList = iAcctBalanceMapper.selectByAcctId(acctBalMap);
			logger.debug("query Acct Bal Frozen result." + acctBalList);
			for (AcctBalanceModel acctBalanceModel : acctBalList) {
				String statusCd = acctBalanceModel.getStatusCd();
				if (statusCd != null && statusCd.equals("2")) { //2账本冻结，1为账本未冻结
					acctBalMap.put("acctBalanceId", acctBalanceModel.getAcctBalanceId());
					acctBalMap.put("rows", rows);
					acctBalMap.put("page", page);
					acctBalMap.put("frozenState", 2);
					logger.debug("query balance frozen by acctBalanceId:" + acctBalMap);
					List<BalanceFrozenModel> list = iBalanceFrozenMapper.queryBalFrozenByAcctId(acctBalMap);
					
					for (BalanceFrozenModel balanceFrozenModel : list) {
						if (balanceFrozenModel.getFrozenState() != null && balanceFrozenModel.getFrozenState().equals("2")) {
							long frozenAmount = balanceFrozenModel.getFrozenAmount();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("balanceFrozenId", balanceFrozenModel.getBalanceFrozenId());
							map.put("frozenAmount", Double.parseDouble(String.valueOf(frozenAmount))/100);
							resultList.add(map);
							frozenAmountSum += frozenAmount;
						}
					}
					total = iBalanceFrozenMapper.queryBalFrozenByAcctIdCnt(acctBalMap);
					//没有账本冻结记录，则修改账本状态为未冻结，否则输出冻结记录
					if (list == null || list.size() == 0) {
						acctBalMap.put("statusCd", 1);
						acctBalMap.put("statusDate", new Date());
						iAcctBalanceMapper.updateBalanceByAcctBalId(acctBalMap);
					}
				}
			}
			
			int pageSize = resultList.size();
			
			List<Map<String, Object>> sumList = new ArrayList<Map<String,Object>>();
			Map<String, Object> mapRooter = new HashMap<String, Object>();
			mapRooter.put("balanceFrozenId", "总冻结金额：" + Double.parseDouble(String.valueOf(frozenAmountSum))/100+" 元");
			if (pageSize>0) {
				mapRooter.put("frozenAmount", "平均冻结金额：" + BaseUtil.formatDouble((Double.parseDouble(String.valueOf(frozenAmountSum))/pageSize)/100,2)+" 元");
			}else{
				mapRooter.put("frozenAmount", "平均冻结金额：" + Double.parseDouble(String.valueOf(frozenAmountSum))/100+" 元");
			}
			sumList.add(mapRooter);
			mapResult.put("total", total);
			mapResult.put("rows", resultList);
			mapResult.put("footer", sumList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.debug(mapResult);
		}
		
		return mapResult;
	}
	
	/**
	 * 余额冻结
	 */
	@Override
	public String balanceFrozen(Map<String, Object> record) {
		logger.debug("balanceFrozen().");
		String hint = "";
		try {
			long subAcctId = Long.parseLong(String.valueOf(record.get("subAcctId")));
			long acctBalanceId = Long.parseLong(String.valueOf(record.get("acctBalanceId")));
			long frozenAmount = Long.parseLong(String.valueOf(record.get("frozenAmount")));
			AcctBalanceModel acctBalRecord = new AcctBalanceModel();
			acctBalRecord.setAcctBalanceId(acctBalanceId);
			acctBalRecord.setSubAcctId(subAcctId);
			acctBalRecord.setSliceKey(subAcctId);
			logger.debug("acct balance query by id.");
			AcctBalanceModel acctBalanceModel = iAcctBalanceMapper.selectByPrimaryKey(acctBalRecord);
			if (acctBalanceModel != null) {
				long frozenAmountSum = 0;
				//statusCd=2账本冻结，其他为账本未冻结
				if (acctBalanceModel.getStatusCd() != null && acctBalanceModel.getStatusCd().equals("2")) {
					logger.debug("acct balance is frozened.");
					Map<String, Object> frozenMap = new HashMap<String, Object>();
					frozenMap.put("acctBalanceId", acctBalanceId);
					frozenMap.put("acctId", subAcctId);
					frozenMap.put("sliceKey", subAcctId);
					List<BalanceFrozenModel> balFrozenRecord = iBalanceFrozenMapper.queryBalFrozenByAcctId(record);
					for (BalanceFrozenModel balanceFrozenModel : balFrozenRecord) {
						if (balanceFrozenModel.getFrozenState() != null && balanceFrozenModel.getFrozenState().equals("2")) {
							frozenAmountSum += balanceFrozenModel.getFrozenAmount();
						}
					}
				}
				//未被冻结余额
				long balanceAmount = acctBalanceModel.getBalance()-frozenAmount-frozenAmountSum;
				if (balanceAmount>=0) {
					Date currDate = new Date();
					
					logger.debug("frozen acct balance.");
					BalanceFrozenModel balFrozenModel = new BalanceFrozenModel();
					balFrozenModel.setAcctBalanceId(acctBalanceId);
					balFrozenModel.setAcctId(subAcctId);
					balFrozenModel.setFrozenAmount(frozenAmount);
					balFrozenModel.setBalanceAmount(balanceAmount);
					balFrozenModel.setFrozenState("2");
					balFrozenModel.setReason("balance frozen");
					balFrozenModel.setStaffId("system");
					balFrozenModel.setCreateDate(currDate);
					balFrozenModel.setEffDate(currDate);
					balFrozenModel.setExpDate(BaseUtil.stringToDate("2099-1-1", "yyyy-MM-dd"));
					balFrozenModel.setSliceKey(subAcctId);
					iBalanceFrozenMapper.insertBalanceFrozen(balFrozenModel);
					
					acctBalanceModel.setStatusCd("2");
					acctBalanceModel.setStatusDate(currDate);
					iAcctBalanceMapper.updateByPrimaryKeySelective(acctBalanceModel);
				 	
					hint = "余额冻结成功！";
				}else{
					hint = "余额不足！";
				}
				
			}else{
				hint = "余额账本不存在！";
			}
		} catch (Exception e) {
			hint = e.getMessage();
			e.printStackTrace();
		} finally {
			logger.debug(hint);
		}
		
		return hint;
	}
	
	/**
	 * 余额解冻
	 */
	@Override
	public String BalanceUnFrozen(String[] balanceFrozenIdArray) {
		logger.debug("BalanceUnFrozen().");
		String hint = "余额已解冻！";
		int succCnt = 0;
		long acctBalanceId = 0;
		long acctId = 0;
		long sliceKey = 0;
		try {
			logger.debug("query balance frozen by key.");
			for (int i = 0; i < balanceFrozenIdArray.length; i++) {
				long balanceFrozenId = Long.parseLong(balanceFrozenIdArray[i]);
				Map<String, Object> balFrozenMap = new HashMap<String, Object>();
				balFrozenMap.put("balanceFrozenId", balanceFrozenId);
				BalanceFrozenModel balFrozenModel = iBalanceFrozenMapper.selectByPrimaryKey(balFrozenMap);
				if (balFrozenModel != null) {
					acctBalanceId = balFrozenModel.getAcctBalanceId();
					acctId = balFrozenModel.getAcctId();
					sliceKey = balFrozenModel.getSliceKey();
					if (balFrozenModel != null) {
						Map<String, Object> balFrozenRecord = new HashMap<String, Object>();
						balFrozenRecord.put("frozenState", "1");
						balFrozenRecord.put("reason", "balance unfrozen.");
						balFrozenRecord.put("updateDate", new Date());
						balFrozenRecord.put("balanceFrozenId", balanceFrozenId);
						balFrozenRecord.put("sliceKey", sliceKey);
						iBalanceFrozenMapper.balanceUnFrozen(balFrozenRecord);
						succCnt++;
					}
				}
				
			}
			
			logger.debug("query balance frozen by acctBalanceId.");
			Map<String, Object> acctBalMap = new HashMap<String, Object>();
			acctBalMap.put("acctBalanceId", acctBalanceId);
			acctBalMap.put("acctId", acctId);
			acctBalMap.put("sliceKey", sliceKey);
			acctBalMap.put("frozenState", 2);
			int unfrozenCnt = iBalanceFrozenMapper.queryBalFrozenByAcctIdCnt(acctBalMap);
			
			//如果冻结记录中全部为未冻结，则更新余额账本为未冻结
			if (unfrozenCnt == 0 && succCnt>0) {
				logger.debug("query acct balance by acctBalanceId.");
				AcctBalanceModel acctBalRecord = new AcctBalanceModel();
				acctBalRecord.setAcctBalanceId(acctBalanceId);
				acctBalRecord.setSubAcctId(acctId);
				acctBalRecord.setSliceKey(sliceKey);
				AcctBalanceModel acctBalModel = iAcctBalanceMapper.selectByPrimaryKey(acctBalRecord);
				
				logger.debug("update acct balance to unfrozen.");
				acctBalModel.setStatusCd("1");
				acctBalModel.setStatusDate(new Date());
				iAcctBalanceMapper.updateByPrimaryKeySelective(acctBalModel);
			}
			if (succCnt == 0) {
				hint = "冻结记录不存在！";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hint;
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
	public IBalanceSourceMapper getiBalanceSourceMapper() {
		return iBalanceSourceMapper;
	}
	@Autowired
	public void setiBalanceSourceMapper(IBalanceSourceMapper iBalanceSourceMapper) {
		this.iBalanceSourceMapper = iBalanceSourceMapper;
	}
	public IBalancePayoutMapper getiBalancePayoutMapper() {
		return iBalancePayoutMapper;
	}
	@Autowired
	public void setiBalancePayoutMapper(IBalancePayoutMapper iBalancePayoutMapper) {
		this.iBalancePayoutMapper = iBalancePayoutMapper;
	}
	
	
	public IBalanceAcctItemPayedMapper getiBalanceAcctItemPayedMapper() {
		return iBalanceAcctItemPayedMapper;
	}
	@Autowired
	public void setiBalanceAcctItemPayedMapper(
			IBalanceAcctItemPayedMapper iBalanceAcctItemPayedMapper) {
		this.iBalanceAcctItemPayedMapper = iBalanceAcctItemPayedMapper;
	}
	
	public IBalanceSourceTypeMapper getiBalanceSourceTypeMapper() {
		return iBalanceSourceTypeMapper;
	}
	@Autowired
	public void setiBalanceSourceTypeMapper(
			IBalanceSourceTypeMapper iBalanceSourceTypeMapper) {
		this.iBalanceSourceTypeMapper = iBalanceSourceTypeMapper;
	}
	
	public IBalanceFrozenMapper getiBalanceFrozenMapper() {
		return iBalanceFrozenMapper;
	}
	@Autowired
	public void setiBalanceFrozenMapper(IBalanceFrozenMapper iBalanceFrozenMapper) {
		this.iBalanceFrozenMapper = iBalanceFrozenMapper;
	}
	

	public IBalanceTypeMapper getiBalanceTypeMapper() {
		return iBalanceTypeMapper;
	}
	@Autowired
	public void setiBalanceTypeMapper(IBalanceTypeMapper iBalanceTypeMapper) {
		this.iBalanceTypeMapper = iBalanceTypeMapper;
	}
	/**
	 * 
	 * @desc 记录余额账本日志
	 * @author ls
	 * @param balSource 余额来源
	 * @param operPayoutId 支出操作流水
	 * @param payoutAmount 支出金额
	 */
	public void newAcctBalanceLog(BalanceSourceModel balSource,long operPayoutId,long payoutAmount){
		AcctBalanceLogModel acctBalLogModel = new AcctBalanceLogModel();
		acctBalLogModel.setAcctBalanceId(balSource.getAcctBalanceId());
		acctBalLogModel.setOperIncomeId(balSource.getOperIncomeId());
		acctBalLogModel.setOperPayoutId(operPayoutId);
		acctBalLogModel.setSrcAmount(balSource.getCurAmount());
		acctBalLogModel.setStatusCd("1");
		acctBalLogModel.setStatusDate(new Date());
		acctBalLogModel.setPayoutAmount(payoutAmount);
		acctBalLogModel.setSliceKey(balSource.getSliceKey());
		iAcctBalanceLogMapper.insertSelective(acctBalLogModel);
	}
	
	/**
	 * 
	 * @desc 余额支出日志
	 * @author ls
	 * @param acctBalanceId 余额账本
	 * @param billId 销帐流水
	 * @param amount 金额
	 * @param balance 操作后余额
	 * @param payoutDesc 支出描述
	 * @param balSourceId 来源类型标识
	 * @param sliceKey 账户ID，分片ID
	 */
	public long newBalancePayoutLog(long acctBalanceId,Long billId,long amount,long balance,String payoutDesc,long sliceKey){
		Date payoutDate = new Date();
		BalancePayoutModel balPayout = new BalancePayoutModel();
		balPayout.setBalance(balance<0?0:balance);
		balPayout.setAmount(amount);
		balPayout.setAcctBalanceId(acctBalanceId);
		if (billId != null) {
			balPayout.setBillId(billId);
		}
		balPayout.setOperDate(payoutDate);
		balPayout.setStaffId("system");
		balPayout.setStatusCd("1");
		balPayout.setStatusDate(payoutDate);
		balPayout.setPayoutDesc(payoutDesc);
		balPayout.setSliceKey(sliceKey);
		iBalancePayoutMapper.insert(balPayout);
		return balPayout.getOperPayoutId();
	}
	
	/**
	 * 
	 * @desc 记录余额来源
	 * @author ls
	 * @param acctBalanceId 余额账本标识
	 * @param amount 存入金额
	 * @param curAmount 剩余金额
	 * @param sourceDesc 来源描述
	 * @param acctId 账户ID，分片ID
	 * @return
	 */
	public long newBalanceSource(long acctBalanceId,long amount,long curAmount,String sourceDesc,long sliceKey){
		Date balSourceDate = new Date();
		BalanceSourceModel balSourceModel = new BalanceSourceModel();
		balSourceModel.setAcctBalanceId(acctBalanceId);
		balSourceModel.setOperType("");
		balSourceModel.setStaffId("system");
		balSourceModel.setOperDate(balSourceDate);
		balSourceModel.setAmount(amount);
		balSourceModel.setCurAmount(curAmount);
		balSourceModel.setBalanceSourceTypeId(1L);
		balSourceModel.setCurStatusDate(balSourceDate);
		balSourceModel.setStatusCd("1");
		balSourceModel.setStatusDate(balSourceDate);
		balSourceModel.setSourceDesc(sourceDesc);
		balSourceModel.setSliceKey(sliceKey);
		iBalanceSourceMapper.insert(balSourceModel);
		return balSourceModel.getOperIncomeId();
	}
	
	/**
	 * 
	 * @desc 更新余额来源，记录余额账本日志
	 * @author ls
	 * @param acctBalanceId 余额账本标识
	 * @param sliceKey 分片ID
	 * @param payoutAmount 支出金额
	 * @param operPayoutId 支出流水
	 */
	public void updateBalanceSource(long acctBalanceId,long payoutAmount,long operPayoutId,long sliceKey){
		Date balSourceDate = new Date();
		BalanceSourceModel bsm = new BalanceSourceModel();
		bsm.setAcctBalanceId(acctBalanceId);
		bsm.setSliceKey(sliceKey);
		List<BalanceSourceModel> BalSourceModelList = iBalanceSourceMapper.selectByAcctBalanceId(bsm);
		for (BalanceSourceModel balanceSourceModel : BalSourceModelList) {
			BalanceSourceModel balSource = new BalanceSourceModel();
			balSource.setOperIncomeId(balanceSourceModel.getOperIncomeId());
			balSource.setOperDate(balSourceDate);
			balSource.setSliceKey(balanceSourceModel.getSliceKey());

			if (payoutAmount>balanceSourceModel.getCurAmount()) {
				payoutAmount -= balanceSourceModel.getCurAmount();
				balSource.setCurAmount(0L);
				iBalanceSourceMapper.updateByPrimaryKey(balSource);
				
				newAcctBalanceLog(balanceSourceModel, operPayoutId, balanceSourceModel.getCurAmount());
			}else{
				balSource.setCurAmount(balanceSourceModel.getCurAmount()-payoutAmount);
				iBalanceSourceMapper.updateByPrimaryKey(balSource);
				
				newAcctBalanceLog(balanceSourceModel, operPayoutId, payoutAmount);
				break;
			}
		}
		
	}
	
	
	public AcctBalanceServiceImpl(){}
	
	public AcctBalanceServiceImpl(boolean flag){
		if (flag) {
			synchronized (AcctBalanceServiceImpl.class) {
				sessionInit();
			}
		}
		
	}
	
	/**
	 * @desc 接口调用
	 * @author ls
	 * @return
	 */
	public void sessionInit(){
		SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
		iAcctBalanceMapper=sqlSession.getMapper(IAcctBalanceMapper.class);
		iBalanceShareRuleMapper=sqlSession.getMapper(IBalanceShareRuleMapper.class);
		iAcctBalanceLogMapper=sqlSession.getMapper(IAcctBalanceLogMapper.class);
		iBalanceSourceMapper=sqlSession.getMapper(IBalanceSourceMapper.class);
		iBalancePayoutMapper=sqlSession.getMapper(IBalancePayoutMapper.class);
		iBalanceSourceTypeMapper=sqlSession.getMapper(IBalanceSourceTypeMapper.class);
		iBalanceFrozenMapper=sqlSession.getMapper(IBalanceFrozenMapper.class);
		iBalanceTypeMapper = sqlSession.getMapper(IBalanceTypeMapper.class);
	}
	
}
