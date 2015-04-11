package com.ctg.itrdc.account.balance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctg.itrdc.account.balance.repository.ITestMapper;
@Service
@Transactional
public class TestCommitServiceImpl{
	@Autowired
	private ITestMapper iTestMapper;

	public ITestMapper getiTestMapper() {
		return iTestMapper;
	}

	public void setiTestMapper(ITestMapper iTestMapper) {
		this.iTestMapper = iTestMapper;
	}
	public void testMyibatis(){
		int res=iTestMapper.updateTest(null);
		System.out.println("res:"+res);
	}
	public void testSelectMybatis(){
		int res=iTestMapper.selectTest();
		System.out.println("res:"+res);
	}
	
	public void selectSequence(){
		String res=iTestMapper.selectSequence();
		System.out.println("res:"+res);
	}
	
}
