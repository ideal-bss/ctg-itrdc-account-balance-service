package com.ctg.itrdc.account.balance.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctg.itrdc.account.balance.model.BalanceRelationModel;
import com.ctg.itrdc.account.balance.repository.IAcctBalanceMapper;
import com.ctg.itrdc.account.balance.repository.ITestMapper;
import com.ctg.itrdc.account.balance.util.SpringUtil;
@Service
@Transactional
public class TestCommitServiceImpl{
	SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
	ITestMapper iTestMapper=sqlSession.getMapper(ITestMapper.class);
	IAcctBalanceMapper iAcctBalanceMapper=sqlSession.getMapper(IAcctBalanceMapper.class);//余额账目
	public void testSelectMybatis(){
		int res=iTestMapper.selectTest();
		System.out.println("res:"+res);
	}
	public void selectAcctBalance(){

	}
	public void selectSequence(){
		String res=iTestMapper.selectSequence();
		System.out.println("res:"+res);
	}
	
	public void testMapperInterface(){
		int res=iTestMapper.selectAcctBalance();
		System.out.println("selectAcctBalance....:"+res);
	}
	public void insertGlobal(){
		int res=iTestMapper.insertGlobalSequence();
		System.out.println("res....:"+res);
	}
	public void selectPrimaryKey(){
		BalanceRelationModel relation=new BalanceRelationModel();
		relation.setAcctBalanceId((long)1);
		relation.setObjectId((long)1);
		relation.setObjectType("1");
		relation.setSliceKey((long)1);
		int row=iAcctBalanceMapper.insertRelation(relation);
		System.out.println("row:"+row+"      "+"relationID:"+relation.getBalanceRelationId());
	}
	public void testHit()
	{
		Map<String, String> map=new HashMap<String, String>();
		map.put("hitSql", "/*+hint({\"dn\":[\"itrdc-cu01\"]})*/");
		int res=iTestMapper.selectHit(map);
		System.out.println("res:"+res);
	}
	
}
