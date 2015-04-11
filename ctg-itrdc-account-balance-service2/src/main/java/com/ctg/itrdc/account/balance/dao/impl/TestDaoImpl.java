package com.ctg.itrdc.account.balance.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ctg.itrdc.account.balance.dao.TestDao;
import com.ctg.itrdc.account.balance.repository.ITestMapper;
import com.ctg.itrdc.account.balance.util.SpringUtil;
import com.ctg.udal.daopub.jdbc.RowSet;
import com.ctg.udal.daopub.jdbc.impl.JdbcDaoImpl;
@Repository
public class TestDaoImpl implements TestDao{
	@Autowired
	private JdbcDaoImpl jdbcDao;
	@Autowired
	private ITestMapper iTestMapper;
	@Override
	public void update() {
		String updateSql="UPDATE ACCT_ITEM SET ORI_ACCT_ITEM_ID=10 WHERE ACCT_ITEM_ID=26 AND STATE=1 and SLICE_KEY=176455900";
		long begin = System.currentTimeMillis();
		jdbcDao.jdbcUpdate(updateSql);
		long end = System.currentTimeMillis();
		System.out.println("IJdbcDAO.jdbcQuery() time=" + (end - begin));
	
	}
	public void select(){
		String selectsql="SELECT ITEM_SOURCE_ID FROM ACCT_ITEM  WHERE ACCT_ITEM_ID=1 AND SLICE_KEY=176455891";
		JdbcDaoImpl dl = (JdbcDaoImpl)SpringUtil.getBean("jdbcDAO");
		long begin = System.currentTimeMillis();
		RowSet rs=dl.jdbcQuery(selectsql);
		rs.next();
		
		System.out.println("rs:"+rs.getString("ITEM_SOURCE_ID"));
		long end = System.currentTimeMillis();
		System.out.println("IJdbcDAO.jdbcQuery() time=" + (end - begin));
	}
	public void testMybatisMapper(){
		int res=iTestMapper.updateTest(null);
		System.out.println("res:"+res);
	}
	public static void main(String[] args) {
		((TestDaoImpl)SpringUtil.getBean("testDaoImpl")).update();
	}
	public JdbcDaoImpl getJdbcDao() {
		return jdbcDao;
	}
	public void setJdbcDao(JdbcDaoImpl jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	
	public ITestMapper getiTestMapper() {
		return iTestMapper;
	}
	public void setiTestMapper(ITestMapper iTestMapper) {
		this.iTestMapper = iTestMapper;
	}
	@Override
	public void insert() {
		String sql="insert into ACCT_ITEM(ACCT_ITEM_ID,ITEM_SOURCE_ID,BILL_ID," +
		"ACCT_ITEM_TYPE_ID, BILLING_CYCLE_ID,ACCT_ID,SERV_ID,AMOUNT, FEE_CYCLE_ID,PAYMENT_METHOD,"+
				"STATE,ACC_NBR,ORI_ACCT_ITEM_ID, REGION_ID) "
		+"VALUES(1,1,1,1,1,1,1,1,1,1,1,1,1,1)";//"+176455810+", SLICE_KEY
		jdbcDao.jdbcExecute(sql);
	}
	
	
}
