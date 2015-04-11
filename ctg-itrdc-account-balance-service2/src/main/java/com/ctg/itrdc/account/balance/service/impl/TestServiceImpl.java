package com.ctg.itrdc.account.balance.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctg.itrdc.account.balance.dao.TestDao;
import com.ctg.itrdc.account.balance.service.ITest;
import com.ctg.udal.daopub.jdbc.impl.JdbcDaoImpl;
import com.ctg.udal.daopub.model.ListBean;
@Service
@Transactional
public class TestServiceImpl implements ITest{
	@Autowired
	private TestDao testDao;
	
	public TestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public void testDaas() {
		// TODO Auto-generated method stub
		testDao.update();
	}
	public void testSelect(){
		testDao.select();
	}
	public void testInsert(){
		testDao.insert();
	}
	public void testMybatis(){
		testDao.testMybatisMapper();
	}
	public void updatet(){
		String updateSql="UPDATE ACCT_ITEM SET ORI_ACCT_ITEM_ID=1 WHERE ACCT_ITEM_ID=100000000002 AND STATE=1 and SLICE_KEY=24370010";
		ApplicationContext act=new FileSystemXmlApplicationContext("F:/MyeclipseWork/ctg-itrdc-account/ctg-itrdc-account-balance-service3/src/main/webapp/WEB-INF/classes/applicationContext.xml");
		JdbcDaoImpl dl = (JdbcDaoImpl)act.getBean("jdbcDAO");
		ListBean param = new ListBean();
		param.add(2L);
		param.add(1L);

		long begin = System.currentTimeMillis();
		int rs = dl.jdbcExecute(updateSql);
		System.out.println("rs:"+rs);
		long end = System.currentTimeMillis();
	}
}
