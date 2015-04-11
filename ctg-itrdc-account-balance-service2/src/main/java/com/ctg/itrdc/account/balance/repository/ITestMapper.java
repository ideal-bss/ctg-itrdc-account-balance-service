package com.ctg.itrdc.account.balance.repository;

import java.util.Map;


public interface ITestMapper {
	int updateTest(Map map);
	
	int selectTest();
	
	String selectSequence();
}
