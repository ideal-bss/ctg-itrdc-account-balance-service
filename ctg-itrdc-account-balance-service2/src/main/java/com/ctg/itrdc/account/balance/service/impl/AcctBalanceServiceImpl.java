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
import com.ctg.itrdc.account.balance.model.BalanceConfig;
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
import com.ctg.itrdc.account.balance.service.IAcctBalanceService;
import com.ctg.itrdc.account.balance.util.BaseUtil;
@Service
@Transactional
public class AcctBalanceServiceImpl implements IAcctBalanceService{
	private Logger logger = Logger.getLogger(AcctBalanceServiceImpl.class);
	private IAcctBalanceMapper iAcctBalanceMapper;
	private IBalanceShareRuleMapper iBalanceShareRuleMapper;
	private IAcctBalanceLogMapper iAcctBalanceLogMapper;
	private IBalanceSourceMapper iBalanceSourceMapper;
	private IBalancePayoutMapper iBalancePayoutMapper;
	
	/*SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
	IAcctBalanceMapper iAcctBalanceMapper=sqlSession.getMapper(IAcctBalanceMapper.class);//余额账目
	IBalanceShareRuleMapper iBalanceShareRuleMapper=sqlSession.getMapper(IBalanceShareRuleMapper.class);//共享规则类型
	IAcctBalanceLogMapper iAcctBalanceLogMapper=sqlSession.getMapper(IAcctBalanceLogMapper.class);//余额账本日志
*/	
	@Override
	public void insertAcctBalance(AcctBalanceModel model,BalanceShareRuleModel shareModel) {
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
		iAcctBalanceMapper.selectAcctBalance(map);
	}
	@Override
	public List<AcctBalanceModel> selectBalance(AcctBalanceModel model) {
		List<AcctBalanceModel> list=new ArrayList<AcctBalanceModel>();
		try {
			list=iAcctBalanceMapper.selectBalanceByAcct(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public AcctBalanceModel selectBalanceById(AcctBalanceModel model) {
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
				BalanceConfig balanceConfig =BalanceConfig.getInstance();
				BalanceTypeModel balTypeModel = balanceConfig.getByTypeId(balanceTypeId);
				logger.info("balance query result.");
				balanceResultMap = new HashMap<String, Object>();
				//余额账本标识
				balanceResultMap.put("acctBalanceId", acctBalanceId);
				//余额类型标识
				balanceResultMap.put("balanceTypeId", balanceTypeId);
				//余额类型名称
				balanceResultMap.put("balanceTypeName", balTypeModel.getBalanceTypeName());
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
			long acctId = Long.parseLong(String.valueOf(model.get("acctId")));
			model.put("sliceKey", acctId);
			//根据余额对象、余额对象类型、账户标识  查询余额账本
			List<AcctBalanceModel> acctBalanceModelList = iAcctBalanceMapper.selectByAcctId(model);
			if (acctBalanceModelList!=null && acctBalanceModelList.size()>0) {
				for (AcctBalanceModel acctBalanceModel : acctBalanceModelList) {
					Date balEffDate = acctBalanceModel.getEffDate();
					Date balExpDate = acctBalanceModel.getExpDate();
					logger.info("余额账本标识:" + acctBalanceModel.getAcctBalanceId());
					
					//余额账本 有效状态
					if (currDate.after(balEffDate) && currDate.before(balExpDate)) {
						BalanceConfig balanceConfig =BalanceConfig.getInstance();
						BalanceTypeModel balTypeModel = balanceConfig.getByTypeId(acctBalanceModel.getBalanceTypeId());
						logger.info("余额类型名称:" + balTypeModel.getBalanceTypeName() + 
									"-->是否专款专用:" + (balTypeModel.getSpePaymentId()==null || balTypeModel.getSpePaymentId()==0?"N":"Y"));
						//专款专用余额 不能支取
						if (balTypeModel.getSpePaymentId() == null || balTypeModel.getSpePaymentId() == 0) {
							acctBalIdList.add(acctBalanceModel.getAcctBalanceId());
							acctBalList.add(acctBalanceModel.getBalance());
							sumBalance += acctBalanceModel.getBalance();
						}
					}
				}
			}else{
				flagHint="余额账本不存在！";
			}
			
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
					acctBalModel.setRemark("paid acct Balance :" + (double)payoutBal/100
									+ " dollar.operation date:" + BaseUtil.dateToString(currDate, "yyyy-MM-dd HH:mm:ss"));
					acctBalModel.setBalance(operAfterBalance);
					iAcctBalanceMapper.updateByPrimaryKeySelective(acctBalModel);
					
					logger.info("记录余额支出日志.");
					long operPayoutId = newBalancePayoutLog(acctBalanceId, requestId, 
							acctBalList.get(i), operAfterBalance, "drawBalance",acctId);
					
					logger.info("查询余额来源信息,并更新余额来源表金额，记录余额账本日志.");
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
	
	/**
	 * 余额转账
	 */
	@Override
	public String balanceTransfer(Map<String, Object> map){
		logger.info("balanceTransfer().");
		String flagHint = null;
		try {
			long requestId = Long.parseLong(String.valueOf(map.get("requestId")));
			long origAcctId = Long.parseLong(String.valueOf(map.get("origAcctId")));
			//根据账户标识、余额类型、余额对象、余额对象类型查询唯一源余额账本
			Map<String, Object> orgiMap = new HashMap<String, Object>();
			orgiMap.put("acctId", origAcctId);
			orgiMap.put("balanceTypeId", map.get("origBalanceTypeId"));
			orgiMap.put("objectType", map.get("origObjectType"));
			orgiMap.put("objectId", map.get("origObjectId"));
			
			List<AcctBalanceModel> orgiAcctBalModelList = iAcctBalanceMapper.selectByAcctId(orgiMap);
			if (orgiAcctBalModelList != null && orgiAcctBalModelList.size()>0) {
				for (AcctBalanceModel orgiAcctBalModel : orgiAcctBalModelList) {
					//根据账户标识、余额类型、余额对象、余额对象类型查询唯一目的余额账本
					Map<String, Object> goalMap = new HashMap<String, Object>();
					long acctId = Long.parseLong(String.valueOf(map.get("acctId")));
					goalMap.put("acctId", acctId);
					goalMap.put("balanceTypeId", map.get("balanceTypeId"));
					goalMap.put("objectType", map.get("objectType"));
					goalMap.put("objectId", map.get("objectId"));
					List<AcctBalanceModel> goalAcctBalModelList = iAcctBalanceMapper.selectByAcctId(goalMap);
					if (goalAcctBalModelList!= null && goalAcctBalModelList.size()>0) {
						for (AcctBalanceModel goalAcctBalModel : goalAcctBalModelList) {
							if (orgiAcctBalModel.getAcctBalanceId() != goalAcctBalModel.getAcctBalanceId()) {
								BalanceConfig balanceConfig =BalanceConfig.getInstance();
								BalanceTypeModel orgiBalTypeModel = balanceConfig.getByTypeId(orgiAcctBalModel.getBalanceTypeId());
								if (orgiBalTypeModel.getSpePaymentId() == null || orgiBalTypeModel.getSpePaymentId() == 0) {
									BalanceTypeModel goalBalTypeModel = balanceConfig.getByTypeId(orgiAcctBalModel.getBalanceTypeId());
									if (goalBalTypeModel.getSpePaymentId() == null || goalBalTypeModel.getSpePaymentId() == 0) {
										long amount = Long.parseLong(String.valueOf(map.get("amount")));
										if (orgiAcctBalModel.getBalance()>=amount) {
											logger.info("余额转账更新源余额账本，并记录支出日志。");
											String currDate = BaseUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
											
											AcctBalanceModel updateOrgiAcctBal = new AcctBalanceModel();
											updateOrgiAcctBal.setAcctBalanceId(orgiAcctBalModel.getAcctBalanceId());
											updateOrgiAcctBal.setBalance(orgiAcctBalModel.getBalance()-amount);
											updateOrgiAcctBal.setRemark("paid Balance :" + (double)amount/100
														+ " dollar.operation date:" + currDate);
											iAcctBalanceMapper.updateByPrimaryKeySelective(updateOrgiAcctBal);
											logger.info("更新余额来源，并记录余额账本日志！");
											long operPayoutId = newBalancePayoutLog(orgiAcctBalModel.getAcctBalanceId(), requestId, 
													orgiAcctBalModel.getBalance(), orgiAcctBalModel.getBalance()-amount, "balance transfer", origAcctId);
											updateBalanceSource(orgiAcctBalModel.getAcctBalanceId(), amount, operPayoutId, origAcctId);

											logger.info("余额转账目的余额账本，并记录余额来源记录。");
											AcctBalanceModel updateGoalAcctBal = new AcctBalanceModel();
											updateGoalAcctBal.setAcctBalanceId(goalAcctBalModel.getAcctBalanceId());
											updateGoalAcctBal.setBalance(goalAcctBalModel.getBalance()+amount);
											updateGoalAcctBal.setRemark("deposit Balance :" + (double)amount/100
														+ " dollar.operation date:" + currDate);
											iAcctBalanceMapper.updateByPrimaryKeySelective(updateGoalAcctBal);											
											logger.info("记录来源记录.");
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
				}
			}else{
				flagHint = "源账本不存在！";
			}
			
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		logger.info(flagHint);
		return flagHint;
	}
	
	/**
	 * 余额冲正
	 */
	@Override
	public String balanceReverse(long operIncomeId) {
		String hint = "";
		Date currDate = new Date();
		try {
			logger.info("balanceReverse().");
			logger.info("根据来源操作流水查询余额来源记录。");
			BalanceSourceModel balSourceRecord = new BalanceSourceModel();
			balSourceRecord.setOperIncomeId(operIncomeId);
			BalanceSourceModel balSourceModel = iBalanceSourceMapper.selectByPrimaryKey(balSourceRecord);
			if (balSourceModel != null) {
				if (balSourceModel.getCurAmount() > 0 && balSourceModel.getAmount() == balSourceModel.getCurAmount()) {
					//没有返销接口，暂时不做。
					logger.info("调用返销账接口，进行账单返销。");
					
					logger.info("修改余额账本余额.");
					AcctBalanceModel acctBalanceRecord = new AcctBalanceModel();
					acctBalanceRecord.setAcctBalanceId(balSourceModel.getAcctBalanceId());
					AcctBalanceModel acctBalanceModel = iAcctBalanceMapper.selectByPrimaryKey(acctBalanceRecord);
					acctBalanceRecord.setBalance(acctBalanceModel.getBalance()-balSourceModel.getCurAmount());
					acctBalanceRecord.setRemark("balance reverse." + (double)balSourceModel.getCurAmount()/100
								+ " dollar.operation date:" + currDate);
					iAcctBalanceMapper.updateByPrimaryKeySelective(acctBalanceRecord);
					
					logger.info("修改账本余额来源记录.");
					balSourceRecord.setCurAmount(0L);
					balSourceRecord.setOperDate(currDate);
					iBalanceSourceMapper.updateByPrimaryKey(balSourceRecord);
					
					logger.info("记录余额支出日志.");
					long operPayoutId = newBalancePayoutLog(balSourceModel.getAcctBalanceId(), 0L, 
							acctBalanceModel.getBalance(), acctBalanceModel.getBalance()-balSourceModel.getCurAmount(), 
							"balance reverse", balSourceModel.getSliceKey());
					logger.info("记录余额账本日志.");
					newAcctBalanceLog(balSourceModel, operPayoutId, balSourceModel.getCurAmount());
					
					hint = "余额冲正成功！";
				} else if (balSourceModel.getCurAmount() <= 0){
					hint = "余额来源金额不足，不能冲正操作！";
				} else if(balSourceModel.getAmount() != balSourceModel.getCurAmount()){
					hint = "余额来源金额已发生变化，不能冲正操作！";
				} else {
					hint = "余额来源金额异常，不能冲正操作！";
				}
			} else {
				hint = "此余额来源不存在！";
			}
			
		} catch (Exception e) {
			hint = "冲正出错！";
			logger.info(e.getMessage());
			e.printStackTrace();
		} finally {
			logger.info(hint);
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
	public long newBalancePayoutLog(long acctBalanceId,long billId,long amount,long balance,String payoutDesc,long sliceKey){
		Date payoutDate = new Date();
		BalancePayoutModel balPayout = new BalancePayoutModel();
		balPayout.setBalance(balance);
		balPayout.setAmount(amount);
		balPayout.setAcctBalanceId(acctBalanceId);
		balPayout.setBillId(billId);
		balPayout.setOperDate(payoutDate);
		balPayout.setStaffId("staff id");
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
		balSourceModel.setStaffId("staff id");
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
	
}
