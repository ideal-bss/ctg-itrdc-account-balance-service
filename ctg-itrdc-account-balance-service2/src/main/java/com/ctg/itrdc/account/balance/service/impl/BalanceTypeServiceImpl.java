package com.ctg.itrdc.account.balance.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctg.itrdc.account.balance.model.BalanceConfig;
import com.ctg.itrdc.account.balance.model.BalanceTypeModel;
import com.ctg.itrdc.account.balance.model.SpecialPaymentModel;
import com.ctg.itrdc.account.balance.repository.IBalanceTypeMapper;
import com.ctg.itrdc.account.balance.repository.ISpecialPaymentMapper;
import com.ctg.itrdc.account.balance.service.IBalanceTypeService;
import com.ctg.itrdc.account.balance.util.BaseUtil;

/**
 * @author ls
 * @date:2015-5-8
 * @version:
 */
@Service
@Transactional
public class BalanceTypeServiceImpl implements IBalanceTypeService {
	Logger logger = Logger.getLogger(BalanceTypeServiceImpl.class);
	
	
	private ISpecialPaymentMapper iSpecialPaymentMapper;
	private IBalanceTypeMapper iBalanceTypeMapper;
	
	/**
	 * 余额类型查询
	 */
	@Override
	public Map<String, Object> queryBalanceType(BalanceTypeModel balanceTypeMap,int rows,int page) {
		logger.debug("queryBalanceType().");
		Map<String, Object> mapResult = new HashMap<String, Object>();
		List<Object> resultList = new ArrayList<Object>();
		try {
			Map<String, Object> requestMap = new HashMap<String, Object>();
			requestMap.put("balanceTypeId", balanceTypeMap.getBalanceTypeId());
			requestMap.put("priority", balanceTypeMap.getPriority());
			requestMap.put("spePaymentId", balanceTypeMap.getSpePaymentId());
			requestMap.put("balanceTypeName", balanceTypeMap.getBalanceTypeName());
			requestMap.put("statusCd", balanceTypeMap.getStatusCd());
			requestMap.put("rows", rows);
			requestMap.put("page", ((page-1)*rows));
			List<BalanceTypeModel> list = iBalanceTypeMapper.queryByBalanceType(requestMap);
			Map<String, Object> map = new HashMap<String, Object>();
			for (BalanceTypeModel balanceTypeModel : list) {
				map = new HashMap<String, Object>();
				map.put("balanceTypeId", balanceTypeModel.getBalanceTypeId());
				map.put("priority", balanceTypeModel.getPriority());
				map.put("spePaymentId", balanceTypeModel.getSpePaymentId());
				map.put("balanceTypeName", balanceTypeModel.getBalanceTypeName());
				map.put("allowDraw", balanceTypeModel.getAllowDraw());
				map.put("invOffer", balanceTypeModel.getInvOffer());
				map.put("ifEarning", balanceTypeModel.getIfEarning());
				map.put("ifPayold", balanceTypeModel.getIfPayold());
				map.put("ifSaveback", balanceTypeModel.getIfSaveback());
				map.put("ifPrincipal", balanceTypeModel.getIfPrincipal());
				map.put("statusCd", balanceTypeModel.getStatusCd());
				map.put("statusDate", balanceTypeModel.getStatusDate());
				if (balanceTypeModel.getBalanceTypeId() != null && balanceTypeModel.getSpePaymentId() != null) {
					map.put("spePaymentName",BalanceConfig.getInstance().getByTypeId(balanceTypeModel.getBalanceTypeId()).getSpecialPaymentModel().getSpecialPaymentDescModel().getSpePaymentDesc());
				}
				
				resultList.add(map);
			}
			int total = iBalanceTypeMapper.queryByBalanceTypeSum(balanceTypeMap);
			mapResult.put("total", total);
			mapResult.put("rows", resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapResult;
	}
	
	/**
	 * 余额类型新增
	 */
	@Override
	public String newInsertBalanceType(BalanceTypeModel balanceTypeModel) {
		logger.debug("newInsertBalanceType().");
		String hint = "";
		SpecialPaymentModel specialPaymentModel = null;
		Long spePaymentId = balanceTypeModel.getSpePaymentId();
		balanceTypeModel.setCreateStaff("system");
		balanceTypeModel.setCreateDate(new Date());
		
		try {
			if (spePaymentId != null && spePaymentId > 0) {
				logger.debug("根据专款专用标识取专款专用信息。");
				specialPaymentModel = iSpecialPaymentMapper.selectByPrimaryKey(spePaymentId);
				if (specialPaymentModel != null) {
					hint = insertBalanceType(balanceTypeModel);
				}else{
					hint = "专款专用标识不存在，存入失败!";
				}
			}else{
				hint = insertBalanceType(balanceTypeModel);
			}
		} catch (Exception e) {
			hint = e.getMessage();
			e.printStackTrace();
		}
		logger.debug(hint);
		return hint;
	}
	
	//校验余额类型是否存在并配置余额类型
	protected String insertBalanceType(BalanceTypeModel balanceTypeModel) {
		String message = "";
		try {
			logger.debug("判断余额类型是否存在。");
			//根据余额类型名称校验余额类型是否存在
			int balanceTypeCnt = iBalanceTypeMapper.selectByBalanceTypeName(balanceTypeModel.getBalanceTypeName());
			//判断余额类型是否已存在，根据余额类型名称校验
			if (balanceTypeCnt == 0){
				//插入新余额类型配置
				if((iBalanceTypeMapper.insertSelective(balanceTypeModel))>0){
					message = "存入成功！";
					BalanceConfig.getInstance().addBalanceType(balanceTypeModel);
				}else{
					message = "存入失败！";
				}
							
			}else{
				message = "指定的余额类型已存在！";
			}
		} catch (Exception e) {
			message = e.getMessage();
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * 批量导入余额类型
	 */
	@Override
	public List<Object> importBalanceType(File file) {
		String hint = null;
		List<Object> list = new ArrayList<Object>();
		try {
			String line = null;
			String item = null;
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			line = reader.readLine(); //表头
			if (line != null && !line.trim().equals("")) {
				String head[] = line.split(",");
				if (head.length == 11) {
					for (int i = 0; i < head.length && hint==null; i++) {
						item = head[i] == null ? "" : head[i].trim();
						hint = null;
						switch (i) {
							case 0:
								if (!item.equals("余额类型优先级")) {
									hint = "表头第1行第" + (i+1) + "列应为：余额类型优先级！";
								}
								break;
							case 1:
								if (!item.equals("专款专用标识")) {
									hint = "表头第1行第" + (i+1) + "列应为：专款专用标识！";
								}
								break;
							case 2:
								if (!item.equals("余额类型名称")) {
									hint = "表头第1行第" + (i+1) + "列应为：余额类型名称！";
								}
								break;
							case 3:
								if (!item.equals("允许提取标志")) {
									hint = "表头第1行第" + (i+1) + "列应为：允许提取标志！";
								}
								break;
							case 4:
								if (!item.equals("是否抵收入")) {
									hint = "表头第1行第" + (i+1) + "列应为：是否抵收入！";
								}
								break;
							case 5:
								if (!item.equals("是否抵旧欠")) {
									hint = "表头第1行第" + (i+1) + "列应为：是否抵旧欠！";
								}
								break;
							case 6:
								if (!item.equals("状态")) {
									hint = "表头第1行第" + (i+1) + "列应为：状态！";
								}
								break;
							case 7:
								if (!item.equals("状态时间")) {
									hint = "表头第1行第" + (i+1) + "列应为：状态时间！";
								}
								break;
							case 8:
								if (!item.equals("提供发票标志")) {
									hint = "表头第1行第" + (i+1) + "列应为：提供发票标志！";
								}
								break;
							case 9:
								if (!item.equals("是否滚存")) {
									hint = "表头第1行第" + (i+1) + "列应为：是否滚存！";
								}
								break;
							case 10:
								if (!item.equals("是否本金")) {
									hint = "表头第1行第" + (i+1) + "列应为：是否本金！";
								}
								break;
						}
					}
					
				}else{
					hint = "表头应有11列，请检查文件！";
				}
				
			}else{
				hint = "文件第一行应是表头！";
			}
			
			int succCnt = 0;
			int sumCnt = 0;
			
			long priority = 0; //余额类型优先级 1,2,3
			Long spePaymentId = null; //专款专用标识
			String balanceTypeName = ""; //余额类型名称
			String allowDraw = "Y";  //允许提取标识   默认Y
			String ifEarning = "Y"; //是否抵收入  默认Y
			String ifPayOld = "Y"; //是否抵旧欠 默认Y
			String statusCd = "Y"; //状态  默认有效（Y）、无效（N）
			Date statusDate = null; //状态时间
			String invOffer = "Y"; //提供发票标识  默认允许（Y）、不允许（N）
			String ifSaveBack = "Y"; //是否滚存  默认是（Y）、否（N）
			String ifPrincipal = "Y";  //是否现金 默认是（Y）、否（N）
			SpecialPaymentModel specialPaymentModel = null;
			BalanceTypeModel balanceTypeModel = null;
			StringBuffer sb = new StringBuffer();
			//读取文件内容
			if (hint == null) {
				sb.append(line).append(",").append("失败原因").append("\r\n");
				while ((line = reader.readLine()) != null) {
					hint = null;
					spePaymentId = null;
					sumCnt++;
					String content[] = line.split(",");
					if (content.length == 11) {
						for (int i = 0; i < content.length && hint == null; i++) {
							item = content[i] == null ? "" : content[i].trim();
							try {
								switch (i) {
									case 0:
										if (!item.equals("")) {
											try {
												priority = Long.parseLong(item);
												if (priority != 1 && priority != 2 && priority != 3) {
													hint = "余额类型优先级不正确!";
												}
											} catch (NumberFormatException e) {
												hint = "余额类型优先级应该是数字类型!";
											}
										}else{
											hint = "余额类型优先级为空!";
										}
										break;
									case 1:
										if (!item.equals("")) {
											try {
												spePaymentId = Long.parseLong(item);
												specialPaymentModel = iSpecialPaymentMapper.selectByPrimaryKey(spePaymentId);
												if (specialPaymentModel == null) {
													hint = "专款专用标识不存在!";
												}
											} catch (NumberFormatException e) {
												hint = "专款专用标识应该是数字类型!";
											}
										}
										break;
									case 2:
										if (!item.equals("")) {
											balanceTypeName = item;
											int balanceTypeCnt = iBalanceTypeMapper.selectByBalanceTypeName(balanceTypeName);
											//判断余额类型是否已存在，根据余额类型名称校验
											if (balanceTypeCnt>0){
												hint = "余额类型已存在！";
											}
										}else{
											hint = "余额类型不能为空！";
										}
										break;
									case 3:
										if (!item.equals("")) {
											if (item.length() == 1) {
												allowDraw = item;
												if (!allowDraw.equals("Y") && !allowDraw.equals("N")) {
													hint = "允许提取标识不正确！";
												}
											}else{
												hint = "允许提取标识不正确。。。！";
											}
											
										}else{
											hint = "允许提取标识不能为空！";
										}
										break;
									case 4:
										if (!item.equals("")) {
											if (item.length() == 1) {
												ifEarning = item;
												if (!ifEarning.equals("Y") && !ifEarning.equals("N")) {
													hint = "是否低收入标识不正确！";
												}
											}else{
												hint = "是否低收入标识不正确。。。！";
											}
										}else{
											hint = "是否低收入标识不能为空！";
										}
										break;
									case 5:
										if (!item.equals("")) {
											if (item.length() == 1) {
												ifPayOld = item;
												if (!ifPayOld.equals("Y") && !ifPayOld.equals("N")) {
													hint = "是否低旧欠标识不正确！";
												}
											}else{
												hint = "是否低旧欠标识不正确。。。！";
											}
											
										}else{
											hint = "是否低旧欠标识不能为空！";
										}
										break;
									case 6:
										if (!item.equals("")) {
											statusCd = item;
											if (!statusCd.equals("Y") && !statusCd.equals("N")) {
												hint = "状态标识不正确！";
											}
										}else{
											hint = "状态标识不能为空！";
										}
										break;
									case 7:
										if (!item.equals("")) {
											try {
												statusDate =  BaseUtil.stringToDate(item, "yyyy-MM-dd");
											}catch (Exception e) {
												hint = "状态时间格式不正确（yyyy-MM-dd）！";
											}
											
										}else{
											hint = "状态时间不能为空！";
										}
										break;
									case 8:
										if (!item.equals("")) {
											if (item.length() == 1) {
												invOffer = item;
												if (!invOffer.equals("Y") && !invOffer.equals("N")) {
													hint = "提供发票标识不正确！";
												}
											}else{
												hint = "提供发票标识不正确。。。！";
											}
										}else{
											hint = "提供发票标识不能为空！";
										}
										break;
									case 9:
										if (!item.equals("")) {
											if (item.length() == 1) {
												ifSaveBack = item;
												if (!ifSaveBack.equals("Y") && !ifSaveBack.equals("N")) {
													hint = "是否滚存标识不正确！";
												}
											}else{
												hint = "是否滚存标识不正确。。。！";
											}
										}else{
											hint = "是否滚存标识不能为空！";
										}
										break;
									case 10:
										if (!item.equals("")) {
											if (item.length() == 1) {
												ifPrincipal = item;
												if (!ifPrincipal.equals("Y") && !ifPrincipal.equals("N")) {
													hint = "是否本金标识不正确！";
												}
											}else{
												hint = "是否本金标识不正确。。。！";
											}
										}else{
											hint = "是否本金标识不能为空！";
										}
										break;
								}
							} catch (Exception e) {
								hint = "其他错误！";
								break;
							}
						}
					}else{
						hint = "文件内容应该是11列！";
					}
					
					//余额存入
					if (hint == null) {
						balanceTypeModel = new BalanceTypeModel();
						balanceTypeModel.setBalanceTypeName(balanceTypeName);
						balanceTypeModel.setCreateDate(new Date());
						balanceTypeModel.setCreateStaff("system");
						balanceTypeModel.setIfEarning(ifEarning);
						balanceTypeModel.setIfPayold(ifPayOld);
						balanceTypeModel.setIfPrincipal(ifPrincipal);
						balanceTypeModel.setIfSaveback(ifSaveBack);
						balanceTypeModel.setInvOffer(invOffer);
						balanceTypeModel.setPriority(priority);
						balanceTypeModel.setSpePaymentId(spePaymentId);
						balanceTypeModel.setStatusCd(statusCd);
						balanceTypeModel.setStatusDate(statusDate);
						balanceTypeModel.setAllowDraw(allowDraw);
						iBalanceTypeMapper.insertSelective(balanceTypeModel);
						succCnt++;
						BalanceConfig.getInstance().addBalanceType(balanceTypeModel);
					} else {
						sb.append(line).append(",").append(hint).append("\r\n");
					}
				}
				hint = "文件共" + sumCnt + "条数据，成功" + succCnt + "条，失败" + (sumCnt-succCnt) + "条！";
				String fileName = "balanceTypeFaild_" + BaseUtil.dateToString(new Date(), "yyyyMMddHHmmss") + ".csv";
				String filePath = BaseUtil.getProperty("balanceType.failedLogs.path");
				BaseUtil.newExcelFile(sb.append(hint).toString(), filePath, fileName);
				
				list.add(0,hint);
				list.add(1,filePath);
				list.add(2,fileName);
				if (sumCnt == succCnt) {
					list.add(3,"allSucced");
				}
			}else{
				list.add(0,hint);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 余额类型修改
	 */
	@Override
	public String modifyBalType(BalanceTypeModel record) {
		logger.debug("modifyBalType().");
		String hint = "该余额类型已修改！";
		try {
			BalanceTypeModel balTypeModel = iBalanceTypeMapper.selectByPrimaryKey(record);
			if (balTypeModel != null && balTypeModel.getBalanceTypeId() != null && balTypeModel.getBalanceTypeId() > 0) {
				int balanceTypeCnt = iBalanceTypeMapper.selectByBalanceTypeName(record.getBalanceTypeName());
				//判断余额类型是否已存在，根据余额类型名称校验
				if (balanceTypeCnt == 0 || balTypeModel.getBalanceTypeName().trim().equals(record.getBalanceTypeName().trim())){
					SpecialPaymentModel SpecialPaymentModel = null;
					Long spePaymentId = record.getSpePaymentId();
					if (spePaymentId != null && spePaymentId > 0) {
						logger.debug("根据专款专用标识取专款专用信息。");
						SpecialPaymentModel = iSpecialPaymentMapper.selectByPrimaryKey(spePaymentId);
						if (SpecialPaymentModel != null) {
							updateBalType(record,balTypeModel);
						}else{
							hint = "专款专用标识不存在，更新失败!";
						}
					}else{
						updateBalType(record,balTypeModel);
					}
						
				}else{
					hint = "指定的余额类型已存在！";
				}
				
			}else{
				hint = "该余额类型已被删除！";
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
	 * 
	 * @desc 余额类型刷新。
	 * @author ls
	 * @param record 传入对象参数
	 * @param balTypeModel  要更新的对象
	 */
	public void updateBalType(BalanceTypeModel record,BalanceTypeModel balTypeModel){
		balTypeModel.setPriority(record.getPriority());
		balTypeModel.setSpePaymentId(record.getSpePaymentId());
		balTypeModel.setBalanceTypeName(record.getBalanceTypeName());
		balTypeModel.setAllowDraw(record.getAllowDraw());
		balTypeModel.setIfEarning(record.getIfEarning());
		balTypeModel.setIfPayold(record.getIfPayold());
		balTypeModel.setStatusCd(record.getStatusCd());
		balTypeModel.setStatusDate(record.getStatusDate());
		balTypeModel.setInvOffer(record.getInvOffer());
		balTypeModel.setIfSaveback(record.getIfSaveback());
		balTypeModel.setIfPrincipal(record.getIfPrincipal());
		balTypeModel.setUpdateDate(new Date());
		balTypeModel.setUpdateStaff("system");
		iBalanceTypeMapper.updateByPrimaryKeySelective(balTypeModel);
	}
	
	@Autowired
	public void setiSpecialPaymentMapper(ISpecialPaymentMapper iSpecialPaymentMapper) {
		this.iSpecialPaymentMapper = iSpecialPaymentMapper;
	}
	
	@Autowired
	public void setiBalanceTypeMapper(IBalanceTypeMapper iBalanceTypeMapper) {
		this.iBalanceTypeMapper = iBalanceTypeMapper;
	}

	public ISpecialPaymentMapper getiSpecialPaymentMapper() {
		return iSpecialPaymentMapper;
	}

	public IBalanceTypeMapper getiBalanceTypeMapper() {
		return iBalanceTypeMapper;
	}

	
}
