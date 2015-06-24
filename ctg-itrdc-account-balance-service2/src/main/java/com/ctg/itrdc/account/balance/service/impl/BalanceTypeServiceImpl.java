package com.ctg.itrdc.account.balance.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctg.itrdc.account.balance.model.BalanceTypeModel;
import com.ctg.itrdc.account.balance.model.SpecialPaymentModel;
import com.ctg.itrdc.account.balance.repository.IBalanceTypeMapper;
import com.ctg.itrdc.account.balance.repository.ISpecialPaymentMapper;
import com.ctg.itrdc.account.balance.service.IBalanceTypeService;

/**
 * @author ls
 * @date:2015-5-8
 * @version:
 */
@Service
@Transactional
public class BalanceTypeServiceImpl implements IBalanceTypeService {
	Logger logger = Logger.getLogger(BalanceTypeServiceImpl.class);
	
	private Long spePaymentId = null;
	private ISpecialPaymentMapper iSpecialPaymentMapper;
	private IBalanceTypeMapper iBalanceTypeMapper;
	
	/**
	 * 余额类型查询
	 */
	@Override
	public List<Object> queryBalanceType(BalanceTypeModel balanceTypeMap) {
		logger.info("queryBalanceType().");
		List<Object> list = iBalanceTypeMapper.queryByBalanceType(balanceTypeMap);
		logger.info(list);
		return list;
	}
	
	/**
	 * 余额类型新增
	 */
	@Override
	public String newInsertBalanceType(BalanceTypeModel balanceTypeModel) {
		logger.info("newInsertBalanceType().");
		String hint = "";
		SpecialPaymentModel SpecialPaymentModel = null;
		spePaymentId = balanceTypeModel.getSpePaymentId();
		balanceTypeModel.setCreateStaff("system");
		balanceTypeModel.setCreateDate(new Date());
		
		try {
			if (spePaymentId != null && spePaymentId > 0) {
				logger.info("根据专款专用标识取专款专用信息。");
				SpecialPaymentModel = iSpecialPaymentMapper.selectByPrimaryKey(spePaymentId);
				if (SpecialPaymentModel != null) {
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
		logger.info(hint);
		return hint;
	}
	
	//校验余额类型是否存在并配置余额类型
	protected String insertBalanceType(BalanceTypeModel balanceTypeModel) {
		String message = "";
		try {
			logger.info("判断余额类型是否存在。");
			//根据余额类型名称校验余额类型是否存在
			int balanceTypeCnt = iBalanceTypeMapper.selectByBalanceTypeName(balanceTypeModel.getBalanceTypeName());
			//判断余额类型是否已存在，根据余额类型名称校验
			if (balanceTypeCnt == 0){
				//插入新余额类型配置
				if(iBalanceTypeMapper.insertSelective(balanceTypeModel)>0){
					message = "存入成功！";
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
	 * 删除余额类型
	 */
	@Override
	public String delBalType(String[] balTypeId) {
		String hint = "";
		try {
			for (String bti : balTypeId) {
				Long balanceTypeId = Long.parseLong(bti);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hint;
	}
	
	/**
	 * 余额类型修改
	 */
	@Override
	public String modifyBalType(BalanceTypeModel record) {
		logger.info("modifyBalType().");
		String hint = "修改成功！";
		try {
			BalanceTypeModel balTypeModel = iBalanceTypeMapper.selectByPrimaryKey(record);
			if (balTypeModel != null && balTypeModel.getBalanceTypeId() != null && balTypeModel.getBalanceTypeId() > 0) {
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
				iBalanceTypeMapper.updateByPrimaryKeySelective(balTypeModel);
			}else{
				hint = "该余额类型已被删除！";
			}
		} catch (Exception e) {
			hint = e.getMessage();
			e.printStackTrace();
		} finally {
			logger.info(hint);
		}
		return hint;
	}
	
	@Autowired
	public void setiSpecialPaymentMapper(ISpecialPaymentMapper iSpecialPaymentMapper) {
		this.iSpecialPaymentMapper = iSpecialPaymentMapper;
	}
	
	@Autowired
	public void setiBalanceTypeMapper(IBalanceTypeMapper iBalanceTypeMapper) {
		this.iBalanceTypeMapper = iBalanceTypeMapper;
	}

	
}
