package com.ctg.itrdc.account.balance.model;

import java.util.List;

public class AcctBalanceConfig {
	private static List<BalanceTypeModel> balanceTypeList;
	private static List<SpecialPaymentModel> specialPaymentList;

	public static List<BalanceTypeModel> getBalanceTypeList() {
		return balanceTypeList;
	}

	public static void setBalanceTypeList(List<BalanceTypeModel> balanceTypeList) {
		AcctBalanceConfig.balanceTypeList = balanceTypeList;
	}

	public static List<SpecialPaymentModel> getSpecialPaymentList() {
		return specialPaymentList;
	}

	public static void setSpecialPaymentList(List<SpecialPaymentModel> specialPaymentList) {
		AcctBalanceConfig.specialPaymentList = specialPaymentList;
	}

	/**
	 * 2015/05/21 hja
	 * 根据账本ID获取余额配置信息
	 * 后续改善初始化时放入map
	 *
	 **/
	public static BalanceTypeModel getByTypeId(Long typeId) {
		BalanceTypeModel balanceTypeModel = null;
		for (BalanceTypeModel btm : balanceTypeList) {
			if (btm.getBalanceTypeId() == typeId) {
				balanceTypeModel = btm;
				break;
			}
		}
		return balanceTypeModel;
	}

}
