package com.ctg.itrdc.account.balance.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctg.itrdc.account.balance.dao.RouteDao;
import com.ctg.itrdc.account.balance.model.AcctBalanceModel;
import com.ctg.itrdc.account.balance.repository.IAcctBalanceMapper;
import com.ctg.itrdc.account.balance.repository.ITestMapper;
import com.ctg.itrdc.account.balance.util.SpringUtil;
@Service
@Transactional
public class TestCommitServiceImpl{
	SqlSession sqlSession = ((SqlSessionFactory)SpringUtil.getBean("sqlSessionFactory")).openSession();
	ITestMapper iTestMapper=sqlSession.getMapper(ITestMapper.class);
	
	/*private RouteDao routeDao;
	public RouteDao getRouteDao() {
		return routeDao;
	}
	@Autowired
	public void setRouteDao(RouteDao routeDao) {
		this.routeDao = routeDao;
	}*/

	public void testSelectMybatis(){
		int res=iTestMapper.selectTest();
		System.out.println("res:"+res);
	}
	public void selectAcctBalance(){
//		int res=iTestMapper.selectAcctBalance();
//		System.out.println("selectAcctBalance....:"+res);
		
//		routeDao.route("", "");
		
		
	}
	public void selectSequence(){
		String res=iTestMapper.selectSequence();
		System.out.println("res:"+res);
	}
	/*public void insertAcctBalance(){
		int res=iTestMapper.insertAcctBalance();
		System.out.println("rs:"+res);
	}*/

	public void testMapperInterface(){
		
		
		int res=iTestMapper.selectAcctBalance();
		System.out.println("selectAcctBalance....:"+res);
	}
	public void insertGlobal(){
		int res=iTestMapper.insertGlobalSequence();
		System.out.println("res....:"+res);
	}
	public void selectPrimaryKey(){
		IAcctBalanceMapper iAcctBalanceMapper=sqlSession.getMapper(IAcctBalanceMapper.class);
		AcctBalanceModel res=iAcctBalanceMapper.selectByPrimaryKey(1L);
		System.out.println("selectAcctBalance....:"+res.getAcctBalanceId());
	}
	public void testHit()
	{
		Map<String, String> map=new HashMap<String, String>();
		map.put("hitSql", "/*+hint({\"dn\":[\"itrdc-cu01\"]})*/");
		int res=iTestMapper.selectHit(map);
		System.out.println("res:"+res);
	}
	
}
