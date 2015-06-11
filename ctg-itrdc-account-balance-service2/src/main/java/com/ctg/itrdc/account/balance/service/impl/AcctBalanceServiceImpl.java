package com.ctg.itrdc.account.balance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctg.itrdc.account.balance.model.AcctBalanceLogModel;
import com.ctg.itrdc.account.balance.model.AcctBalanceModel;
import com.ctg.itrdc.account.balance.model.BalancePayoutModel;
import com.ctg.itrdc.account.balance.model.BalanceRelationModel;
import com.ctg.itrdc.account.balance.model.BalanceShareRuleModel;
import com.ctg.itrdc.account.balance.model.BalanceSourceModel;
import com.ctg.itrdc.account.balance.model.BalanceTypeModel;
import com.ctg.itrdc.account.balance.repository.IAcctBalanceLogMapper;
import com.ctg.itrdc.account.balance.repository.IAcctBalanceMapper;
import com.ctg.itrdc.account.balance.repository.IBalancePayoutMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceShareRuleMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceSourceMapper;
import com.ctg.itrdc.account.balance.repository.IBalanceTypeMapper;
import com.ctg.itrdc.account.balance.service.IAcctBalanceService;
import com.ctg.itrdc.account.balance.util.BaseUtil;
@Service
@Transactional
public class AcctBalanceServiceImpl implements IAcctBalanceService{
	private Logger logger = Logger.getLogger(AcctBalanceServiceImpl.class);
	private IAcctBalanceMapper iAcctBalanceMapper;
	private IBalanceShareRuleMapper iBalanceShareRuleMapper;
	private IAcctBalanceLogMapper iAcctBalanceLogMapper;
	private IBalanceTypeMapper iBalanceTypeMapper;
	private IBalanceSourceMapper iBalanceSourceMapper;
	private IBalancePayoutMapper iBalancePayoutMapper;
	
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
	
	/**
	 * 
	 * @desc 余额查询
	 * @author ls
	 * @param model
	 * @return
	 */
	@Override
	public List<Object> queryBalance(Map<String, Object> model) {
		
		logger.info("queryBalance()");
		
		List<Map<String, Object>> shareRuleList = null;
		List<Object> balanceResultList = new ArrayList<Object>();
		Map<String, Object> balanceResultMap = null;
		
		AcctBalanceModel acctBalanceModelQuery = null;
		BalanceTypeModel balanceTypeModelQuery = null;
		
		try {
			
			logger.info("query balance share rule."+model);
			shareRuleList = iBalanceShareRuleMapper.selectRuleList(model);
			
			logger.info("query share rule result." + shareRuleList);
			
			for(Map<String, Object> shareRule:shareRuleList){
				Long acctBalanceId = Long.parseLong(shareRule.get("ACCT_BALANCE_ID").toString());
				Long sliceKey = Long.parseLong(shareRule.get("SLICE_KEY").toString());
				
				logger.info("query acct balance.");
				acctBalanceModelQuery = new AcctBalanceModel();
				acctBalanceModelQuery.setAcctBalanceId(acctBalanceId);
				acctBalanceModelQuery.setSliceKey(sliceKey);
				AcctBalanceModel acctBalanceModel= iAcctBalanceMapper.selectByPrimaryKey(acctBalanceModelQuery);
				Long balanceTypeId = acctBalanceModel.getBalanceTypeId();
				if (!model.get("balanceTypeId").equals("0")
						&& !model.get("balanceTypeId").equals(String.valueOf(balanceTypeId))) {
					continue;
				}
				
				logger.info("query balance type.");
				balanceTypeModelQuery = new BalanceTypeModel();
				balanceTypeModelQuery.setBalanceTypeId(balanceTypeId);
				List<Object> balanceTypeList = iBalanceTypeMapper.queryByBalanceType(balanceTypeModelQuery);
				
				logger.info("balance query result.");
				balanceResultMap = new HashMap<String, Object>();
				//余额账本标识
				balanceResultMap.put("acctBalanceId", acctBalanceId);
				//余额类型标识
				balanceResultMap.put("balanceTypeId", balanceTypeId);
				//余额类型名称
				balanceResultMap.put("balanceTypeName", ((BalanceTypeModel)balanceTypeList.get(0)).getBalanceTypeName());
				//生效时间
				balanceResultMap.put("effDate", acctBalanceModel.getEffDate());
				//失效时间
				balanceResultMap.put("expDate", acctBalanceModel.getExpDate());
				//单个账本余额
				balanceResultMap.put("balance", acctBalanceModel.getBalance());
				//余额对象标识
				balanceResultMap.put("objectId", shareRule.get("OBJECT_ID"));
				//冻结余额
				balanceResultMap.put("freezeBalance", 0);
				
				balanceResultList.add(balanceResultMap);
			}
			if (balanceResultList.size() == 0) {
				balanceResultMap = new HashMap<String, Object>();
				balanceResultMap.put("errorInfo", "账本不存在！");
				balanceResultList.add(balanceResultMap);
			}
			
		} catch (NumberFormatException e) {
			logger.error("格式化错误" + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			logger.error("空指针错误" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("其他异常" + e.getMessage());
			e.printStackTrace();
		}
		return balanceResultList;
	}
	
	/**
	 * @desc 余额支取
	 * @author ls
	 * @param model
	 * @return
	 */
	@Override
	public String balanceDraw(Map<String, Object> model) {
		logger.info("-----余额支取----- balanceDraw().");
		Date currDate = new Date(); //当前时间
		String flagHint = null;//返回值
		long sumBalance = 0;
		List<Long> acctBalIdList = new ArrayList<Long>();
		List<Long> acctBalList = new ArrayList<Long>();
		try {
			
			//根据余额对象和余额对象类型  查询余额共享规则
			List<Map<String, Object>> balanceShareRuleList = getiBalanceShareRuleMapper().selectRuleList(model);
			if (balanceShareRuleList == null || balanceShareRuleList.size() == 0) {
				flagHint = "余额账本不存在！";
			}
			logger.info("余额共享规则清单-->" + balanceShareRuleList);
			for (Map<String, Object> balanceShareRule:balanceShareRuleList) {
				long acctBalanceId = Long.valueOf(String.valueOf(balanceShareRule.get("ACCT_BALANCE_ID")));
				Date shareEffDate = BaseUtil.stringToDate(balanceShareRule.get("EFF_DATE"), "yyyy-MM-dd");
				Date shareExpDate = BaseUtil.stringToDate(balanceShareRule.get("EXP_DATE"), "yyyy-MM-dd");
				
				//余额共享规则 有效状态
				if (currDate.after(shareEffDate) && currDate.before(shareExpDate)) {
					
					//根据余额账本标识取余额账本
					AcctBalanceModel acctBalRecord = new AcctBalanceModel();
					acctBalRecord.setAcctBalanceId(acctBalanceId);
					AcctBalanceModel acctBalanceMpdel = iAcctBalanceMapper.selectByPrimaryKey(acctBalRecord);
					Date balEffDate = acctBalanceMpdel.getEffDate();
					Date balExpDate = acctBalanceMpdel.getExpDate();
					logger.info("余额账本标识:" + acctBalanceMpdel.getAcctBalanceId());
					
					//余额账本 有效状态
					if (currDate.after(balEffDate) && currDate.before(balExpDate)) {
						BalanceTypeModel balTypeRecord = new BalanceTypeModel();
						balTypeRecord.setBalanceTypeId(acctBalanceMpdel.getBalanceTypeId());
						BalanceTypeModel balTypeModel = iBalanceTypeMapper.selectByPrimaryKey(balTypeRecord);
						logger.info("余额类型名称:" + balTypeModel.getBalanceTypeName() + 
									"-->是否专款专用:" + (balTypeModel.getSpePaymentId()==null || balTypeModel.getSpePaymentId()==0?"N":"Y"));
						//专款专用余额 不能支取
						if (balTypeModel.getSpePaymentId() == null || balTypeModel.getSpePaymentId() == 0) {
							acctBalIdList.add(acctBalanceId);
							acctBalList.add(acctBalanceMpdel.getBalance());
							sumBalance += acctBalanceMpdel.getBalance();
						}
					}
				}
			}
			Date currentDate = new Date();
			//支取金额
			long drawAmount = Long.parseLong(String.valueOf(model.get("drawAmount")));
			long requestId = Long.parseLong(String.valueOf(model.get("requestId")));
			//支取金额大于等于账本总余额，才可以支取。否则不能支取
			if (sumBalance>=drawAmount) {
				boolean flag = true;
				long balance = drawAmount;
				long acctBalance = drawAmount;
				logger.info("开始支取余额.");
				List<BalanceSourceModel> balanceSourceList = null;
				BalanceSourceModel balSourceModel = null;
				for (int i = 0; i < acctBalIdList.size() && flag; i++) {
					long acctBalanceId = acctBalIdList.get(i);
					
					logger.info("更新余额账本余额.");
					BalancePayoutModel balPayoutModel = new BalancePayoutModel();
					AcctBalanceModel acctBalModel = new AcctBalanceModel();
					acctBalModel.setAcctBalanceId(acctBalanceId);
					if (acctBalance>acctBalList.get(i)) {
						acctBalance -= acctBalList.get(i);
						acctBalModel.setBalance(0L);
						balPayoutModel.setBalance(0L);
					}else{
						acctBalModel.setBalance(acctBalList.get(i) - acctBalance);
						balPayoutModel.setBalance(acctBalList.get(i) - acctBalance);
					}
					iAcctBalanceMapper.updateByPrimaryKeySelective(acctBalModel);
					
					logger.info("记录余额账本日志.");
					balPayoutModel.setAmount(acctBalList.get(i));
					balPayoutModel.setAcctBalanceId(acctBalanceId);
					balPayoutModel.setBillId(requestId);
					balPayoutModel.setOperDate(currentDate);
					balPayoutModel.setStaffId("staff id");
					iBalancePayoutMapper.insert(balPayoutModel);
					Long operPayoutId = balPayoutModel.getOperPayoutId();
					
					logger.info("查询余额来源信息,并更新余额来源表金额，记录余额支出日志.");
					balSourceModel = new BalanceSourceModel();
					balSourceModel.setAcctBalanceId(acctBalanceId);
					balanceSourceList = iBalanceSourceMapper.selectByAcctBalanceId(balSourceModel);
					for (BalanceSourceModel balanceSource : balanceSourceList) {
						BalanceSourceModel balSource = new BalanceSourceModel();
						balSource.setOperDate(currentDate);
						balSource.setOperIncomeId(balanceSource.getOperIncomeId());
						
						//余额账本日志
						AcctBalanceLogModel acctBalLogModel = new AcctBalanceLogModel();
						acctBalLogModel.setAcctBalanceId(acctBalanceId);
						acctBalLogModel.setOperIncomeId(balanceSource.getOperIncomeId());
						acctBalLogModel.setOperPayoutId(operPayoutId);
						acctBalLogModel.setSrcAmount(balanceSource.getCurAmount());
						if (balance>balanceSource.getCurAmount()) {
							balance -= balanceSource.getCurAmount();
							balSource.setCurAmount(0L);
							iBalanceSourceMapper.updateByPrimaryKey(balSource);
							
							acctBalLogModel.setPayoutAmount(balanceSource.getCurAmount());
							iAcctBalanceLogMapper.insertSelective(acctBalLogModel);
						}else{
							balSource.setCurAmount(balanceSource.getCurAmount()-balance);
							iBalanceSourceMapper.updateByPrimaryKey(balSource);
							
							acctBalLogModel.setPayoutAmount(balance);
							iAcctBalanceLogMapper.insertSelective(acctBalLogModel);
							flag = false;
							break;
						}
					}
					
					
				}
				
				flagHint = "成功支取金额：" + Double.valueOf(drawAmount)/100 + "元。";
			}else if(flagHint == null){
				flagHint = "可支取余额不足，操作失败！";
			}
			logger.info(flagHint);
		} catch (NumberFormatException e) {
			logger.error("格式化参数异常." + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			logger.error("对象为空." + e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return flagHint;
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
	@Autowired
	public void setiBalanceTypeMapper(IBalanceTypeMapper iBalanceTypeMapper) {
		this.iBalanceTypeMapper = iBalanceTypeMapper;
	}
	public IBalanceSourceMapper getiBalanceSourceMapper() {
		return iBalanceSourceMapper;
	}
	@Autowired
	public void setiBalanceSourceMapper(IBalanceSourceMapper iBalanceSourceMapper) {
		this.iBalanceSourceMapper = iBalanceSourceMapper;
	}
	public IBalanceTypeMapper getiBalanceTypeMapper() {
		return iBalanceTypeMapper;
	}
	public IBalancePayoutMapper getiBalancePayoutMapper() {
		return iBalancePayoutMapper;
	}
	@Autowired
	public void setiBalancePayoutMapper(IBalancePayoutMapper iBalancePayoutMapper) {
		this.iBalancePayoutMapper = iBalancePayoutMapper;
	}
	
	
	
}
