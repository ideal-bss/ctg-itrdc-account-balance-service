package com.ctg.itrdc.account.balance.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ctg.itrdc.account.balance.model.BalanceTypeModel;
import com.ctg.itrdc.account.balance.service.IBalanceTypeService;

/**
 * 余额类型
 * @author hxj
 *
 */
@Controller
public class BalanceTypeAction extends BaseAction{
	Logger logger = Logger.getLogger(BalanceTypeAction.class);
	
	private String balanceTypeId; //余额类型标识
	private String priority; //余额类型优先级
	private String spePaymentId; //专款专用标识
	private String balanceTypeName; //余额类型名称
	private String statusCd; //状态
	private String allowDraw; //允许提取标志
	private String invOffer; //提供发票标志
	private String ifEarning;  //是否低收入
	private String ifPayOld; //是否抵旧欠
	private String ifSaveBack; //是否滚存
	private String ifPrincipal; //是否本金
	private String statusDate; //状态时间
	private IBalanceTypeService iBalanceTypeService;
	private BalanceTypeModel balanceTypeModel;
	
	/**
	 * 余额类型查询
	 * @author ls
	 * @return 
	 * @throws Exception
	 */
	public String balanceTypeQuery()throws Exception{
		logger.info("balanceTypeQuery()......start......");
		List<Object> list= new ArrayList<Object>();
		String type = "query";
		try {
			setBalanceType(type);
			list = iBalanceTypeService.queryBalanceType(balanceTypeModel);
			writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("balanceTypeQuery()......end......");
		return "success";
	}
	/**
	 * 余额类型查询界面跳转
	 * @author ls
	 * @return
	 * @throws Exception
	 */
	public String balanceTypeQueryGo()throws Exception{
		logger.info("balanceTypeQueryGo().");
		return "success";
	}
	

	/**
	 * 余额类型存入
	 * @return
	 * @throws Exception
	 * @author ls
	 */
	public String balanceTypeAdd(){
		logger.info("balanceTypeAdd()......start......");
		String hint = "";
		List<String> list = new ArrayList<String>();
		String type = "insert";
		try {
			//封装页面传输的参数
			hint = setBalanceType(type);
			if (hint == null || hint.trim().equals("")) {
				hint = iBalanceTypeService.newInsertBalanceType(balanceTypeModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			list.add(hint);
			writeJson(hint);
		}
		logger.info("balanceTypeAdd()......end......");
		return "success";
	}
	
	/**
	 * 余额类型存入界面跳转
	 * @return
	 * @throws Exception
	 * @author ls
	 */
	public String balanceTypeAddGo()throws Exception{
		logger.info("balanceTypeAddGo().");
		return "success";
	}
	
	/**
	 * 封装页面传输的参数
	 * @author ls
	 */
	protected String setBalanceType(String type) {
		String hint = "";
		balanceTypeModel = new BalanceTypeModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (type.equals("query") || type.equals("insert")) {
				if (balanceTypeId != null && !balanceTypeId.trim().equals("")) {
					balanceTypeModel.setBalanceTypeId(Long.valueOf(balanceTypeId));
				}
				if (spePaymentId != null && !spePaymentId.trim().equals("")) {
					balanceTypeModel.setSpePaymentId(Long.valueOf(spePaymentId));
				}
				if (priority != null && !priority.trim().equals("")) {
					balanceTypeModel.setPriority(Long.valueOf(priority));
				}
				balanceTypeModel.setBalanceTypeName(balanceTypeName);
				balanceTypeModel.setStatusCd(statusCd);
			}
			
			if (type.equals("insert")) {
				balanceTypeModel.setAllowDraw(allowDraw);
				balanceTypeModel.setInvOffer(invOffer);
				balanceTypeModel.setIfEarning(ifEarning);
				balanceTypeModel.setIfPayold(ifPayOld);
				balanceTypeModel.setIfSaveback(ifSaveBack);
				balanceTypeModel.setIfPrincipal(ifPrincipal);
				balanceTypeModel.setStatusDate(sdf.parse(statusDate));
			}
			
			
		} catch (NumberFormatException e) {
			hint = "数字格式化错误" + e.getMessage();
			e.printStackTrace();
		} catch (ParseException e) {
			hint = "状态时间格式化错误" + e.getMessage();
			e.printStackTrace();
		} catch (Exception e) {
			hint = "其他错误" + e.getMessage();
			e.printStackTrace();
		}
		return hint;
	}
	
	@Autowired
	public void setiBalanceTypeService(IBalanceTypeService iBalanceTypeService) {
		this.iBalanceTypeService = iBalanceTypeService;
	}
	
	public String getBalanceTypeId() {
		return balanceTypeId;
	}
	public void setBalanceTypeId(String balanceTypeId) {
		this.balanceTypeId = balanceTypeId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSpePaymentId() {
		return spePaymentId;
	}
	public void setSpePaymentId(String spePaymentId) {
		this.spePaymentId = spePaymentId;
	}
	public String getBalanceTypeName() {
		return balanceTypeName;
	}
	public void setBalanceTypeName(String balanceTypeName) {
		this.balanceTypeName = balanceTypeName;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getAllowDraw() {
		return allowDraw;
	}
	public void setAllowDraw(String allowDraw) {
		this.allowDraw = allowDraw;
	}
	public String getInvOffer() {
		return invOffer;
	}
	public void setInvOffer(String invOffer) {
		this.invOffer = invOffer;
	}
	public String getIfEarning() {
		return ifEarning;
	}
	public void setIfEarning(String ifEarning) {
		this.ifEarning = ifEarning;
	}
	public String getIfPayOld() {
		return ifPayOld;
	}
	public void setIfPayOld(String ifPayOld) {
		this.ifPayOld = ifPayOld;
	}
	public String getIfSaveBack() {
		return ifSaveBack;
	}
	public void setIfSaveBack(String ifSaveBack) {
		this.ifSaveBack = ifSaveBack;
	}
	public String getIfPrincipal() {
		return ifPrincipal;
	}
	public void setIfPrincipal(String ifPrincipal) {
		this.ifPrincipal = ifPrincipal;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}
	public IBalanceTypeService getiBalanceTypeService() {
		return iBalanceTypeService;
	}
	
	
}
