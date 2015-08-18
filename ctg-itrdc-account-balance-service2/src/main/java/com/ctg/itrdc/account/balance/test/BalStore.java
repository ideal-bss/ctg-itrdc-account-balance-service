package com.ctg.itrdc.account.balance.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ctg.itrdc.account.balance.model.AcctBalanceModel;
import com.ctg.itrdc.account.balance.model.BalanceShareRuleModel;
import com.ctg.itrdc.account.balance.service.IAcctBalanceService;
import com.ctg.itrdc.account.balance.service.impl.AcctBalanceServiceImpl;

public class BalStore {

	public static void main(String[] args) {
		
		try {
			BalStore bs = new BalStore();
			bs.balAdd();
			//bs.balQuery();
			//bs.balFrozenQuery();
			//bs.balUnFrozen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 余额存入
	 * @desc 
	 * @author ls
	 * @return
	 */
	public void balAdd(){
		Map<String, Object> resultMap = null;
		try {
			//账本对象
			AcctBalanceModel acctBalanceModel=new AcctBalanceModel();
			acctBalanceModel.setBalanceTypeId((long) 1);
			acctBalanceModel.setPaymentRuleId(null);
			acctBalanceModel.setSubAcctId((long) 12);
			acctBalanceModel.setAcctId((long) 12);
			acctBalanceModel.setEffDate(new Date());
			acctBalanceModel.setExpDate(new Date());
			acctBalanceModel.setBalance((long) 10000);
			acctBalanceModel.setReserveBalance((long) 0);
			acctBalanceModel.setCycleUpper((long) 0);
			acctBalanceModel.setCycleLower((long) 0);
			acctBalanceModel.setStatusCd("Y");
			acctBalanceModel.setStatusDate(new Date());
			acctBalanceModel.setCycleType("Y");
			acctBalanceModel.setRemark("余额存入！");
			acctBalanceModel.setNeedInvoiceAmount((long) 0);
			acctBalanceModel.setSliceKey((long) 12);
			acctBalanceModel.setCreateDate(new Date());
			
			//共享规则对象
			BalanceShareRuleModel shareModel=new BalanceShareRuleModel();
			shareModel.setObjectId((long) 12);
			shareModel.setObjectType("2");
			shareModel.setShareRuleTypeId((long) 2);
			shareModel.setShareRuleTypePri((long) 2);
			shareModel.setUpperAmount((long) 0);
			shareModel.setLowerAmount((long) 0);
			shareModel.setSliceKey((long) 12);
			IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
			resultMap = iAcctBalanceService.insertAcctBalance(acctBalanceModel, shareModel);
			System.out.println("status:" + resultMap.get("status"));
			System.out.println("failReason:" + resultMap.get("failReason"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 余额查询
	 * @desc 
	 * @author ls
	 */
	public void balQuery(){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("objectId", 12);
		model.put("balanceTypeId", 0);
		model.put("objectType", 2);
		IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
		Map<String, Object> map = iAcctBalanceService.queryBalance(model);
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("rows");
		for (Map<String, Object> mapResult : list) {
			System.out.println(mapResult.get("acctBalanceId"));
			System.out.println(mapResult.get("balanceTypeId"));
			System.out.println(mapResult.get("balanceTypeName"));
			System.out.println(mapResult.get("effDate"));
			System.out.println(mapResult.get("expDate"));
			System.out.println(mapResult.get("objectId"));
			System.out.println(mapResult.get("balance"));
			System.out.println(mapResult.get("frozenAmount"));
			System.out.println(mapResult.get("acctId"));
		}
	}
	
	/**
	 * 
	 * @desc 余额支取
	 * @author ls
	 */
	public void balDraw(){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("acctId", 12);
		model.put("drawAmount", 10);
		model.put("objectId", 12);
		model.put("acctBalanceIdArray", 1061426109);
		IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
		String drawHint = iAcctBalanceService.balanceDraw(model);
		System.out.println("支取结果："+drawHint);
	}
	
	/**
	 * 余额转账
	 */
	public void balTransfer(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("origBalanceTypeId", 1);
		map.put("acctBalanceId", 1061426109);
		map.put("origAcctId", 12);
		map.put("amount", 10);
		map.put("acctId", 11);
		map.put("objectType", 1);
		map.put("objectId", 11);
		IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
		String hint = iAcctBalanceService.balanceTransfer(map);
		System.out.println("转账结果："+hint);
	}
	
	
	/**
	 * 
	 * @desc 余额冻结
	 * @author ls
	 */
	public void balFrozen(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subAcctId", 12);
		map.put("acctBalanceId", 1061426109);
		map.put("frozenAmount", 10);
		IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
		String hint = iAcctBalanceService.balanceFrozen(map);
		System.out.println("结果："+hint);
	}
	
	/**
	 * 
	 * @desc 余额冻结查询
	 * @author ls
	 */
	public void balFrozenQuery(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("acctId", 12);
		map.put("acctBalanceId", 1061426109);
		IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
		Map<String, Object> resultMap = iAcctBalanceService.queryBalFrozen(map);
		List<Object> resultList = (List<Object>) resultMap.get("rows");
		for (int i = 0; i < resultList.size(); i++) {
			Map<String, Object> mapR = (Map<String, Object>) resultList.get(i);
			System.out.println(mapR.get("balanceFrozenId"));
			System.out.println(mapR.get("frozenAmount"));
		}
	}
	
	/**
	 * 
	 * @desc 余额解冻
	 * @author ls
	 */
	public void balUnFrozen(){
		String balFrozenId = "124342";
		String []balFrozenIdArray = balFrozenId.split(",");
		IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
		String hint = iAcctBalanceService.BalanceUnFrozen(balFrozenIdArray);
		System.out.println("结果：" + hint);
	}
	
	/**
	 * 
	 * @desc 余额冲正
	 * @author ls
	 */
	public void balReverse(){
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("operIncomeId", 12);
		requestMap.put("sliceKey", 12);
		IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
		String hint = iAcctBalanceService.balanceReverse(requestMap);
		System.out.println("冲正结果："+hint);
	}
}
