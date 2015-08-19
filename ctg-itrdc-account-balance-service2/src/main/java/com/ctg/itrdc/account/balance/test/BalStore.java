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
			//bs.balAdd();  //余额存入
			//bs.balQuery();  //余额查询
			//bs.balDraw();   //余额支取
			//bs.balTransfer();  //余额转账
			//bs.balFrozen();  //余额冻结
			//bs.balFrozenQuery();   //余额冻结查询
			//bs.balUnFrozen();  //余额解冻
			bs.balReverse();  //余额冲正
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
			acctBalanceModel.setSubAcctId((long) 1242314);
			acctBalanceModel.setAcctId((long) 1212);
			acctBalanceModel.setEffDate(new Date());
			acctBalanceModel.setExpDate(new Date());
			acctBalanceModel.setBalance((long) 5000);
			acctBalanceModel.setReserveBalance((long) 0);
			acctBalanceModel.setCycleUpper((long) 0);
			acctBalanceModel.setCycleLower((long) 0);
			acctBalanceModel.setStatusCd("Y");
			acctBalanceModel.setStatusDate(new Date());
			acctBalanceModel.setCycleType("Y");
			acctBalanceModel.setRemark("余额存入！");
			acctBalanceModel.setNeedInvoiceAmount((long) 0);
			acctBalanceModel.setSliceKey((long) 1242314);
			acctBalanceModel.setCreateDate(new Date());
			
			//共享规则对象
			BalanceShareRuleModel shareModel=new BalanceShareRuleModel();
			shareModel.setObjectId((long) 12421323);
			shareModel.setObjectType("2");
			shareModel.setShareRuleTypeId((long) 2);
			shareModel.setShareRuleTypePri((long) 2);
			shareModel.setUpperAmount((long) 0);
			shareModel.setLowerAmount((long) 0);
			shareModel.setSliceKey((long) 1242314);
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
		model.put("objectId", 12421323);
		model.put("balanceTypeId", 0);
		model.put("objectType", 0);
		IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
		Map<String, Object> map = iAcctBalanceService.queryBalance(model);
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("rows");
		for (Map<String, Object> mapResult : list) {
			System.out.print(mapResult.get("acctBalanceId")+",");
			System.out.print(mapResult.get("balanceTypeId")+",");
			System.out.print(mapResult.get("balanceTypeName")+",");
			System.out.print(mapResult.get("effDate")+",");
			System.out.print(mapResult.get("expDate")+",");
			System.out.print(mapResult.get("objectId")+",");
			System.out.print(mapResult.get("balance")+",");
			System.out.print(mapResult.get("frozenAmount")+",");
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
		model.put("acctId", 2982214);
		model.put("drawAmount", 45891);
		model.put("objectId", 9930078);
		model.put("acctBalanceIdArray", 1000000653);
		IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
		String drawHint = iAcctBalanceService.balanceDraw(model);
		System.out.println("支取结果："+drawHint);
	}
	
	/**
	 * 余额转账
	 */
	public void balTransfer(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("origBalanceTypeId", 998);
		map.put("acctBalanceId", 1000005504);
		map.put("origAcctId", 2985094);
		map.put("amount", 10);
		map.put("acctId", 2980114);
		map.put("objectType", 1);
		map.put("objectId", 9925229);
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
		map.put("acctBalanceId", 10614261091L);
		IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
		Map<String, Object> resultMap = iAcctBalanceService.queryBalFrozen(map);
		List<Object> resultList = (List<Object>) resultMap.get("rows");
		for (int i = 0; i < resultList.size(); i++) {
			Map<String, Object> mapR = (Map<String, Object>) resultList.get(i);
			System.out.print(mapR.get("balanceFrozenId")+"   ");
			System.out.println(mapR.get("frozenAmount"));
		}
	}
	
	/**
	 * 
	 * @desc 余额解冻
	 * @author ls
	 */
	public void balUnFrozen(){
		String balFrozenId = "1";
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
		requestMap.put("operIncomeId", 1111241591);
		requestMap.put("sliceKey", 34231231);
		IAcctBalanceService iAcctBalanceService = new AcctBalanceServiceImpl(true);
		String hint = iAcctBalanceService.balanceReverse(requestMap);
		System.out.println("冲正结果："+hint);
	}
}
