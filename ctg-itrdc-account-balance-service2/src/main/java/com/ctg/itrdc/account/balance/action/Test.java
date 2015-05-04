package com.ctg.itrdc.account.balance.action;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ctg.itrdc.account.balance.service.impl.TestCommitServiceImpl;
import com.ctg.itrdc.account.balance.service.impl.TestServiceImpl;
import com.ctg.itrdc.account.balance.util.SpringUtil;
import com.ctg.udal.daopub.jdbc.RowSet;
import com.ctg.udal.daopub.jdbc.impl.JdbcDaoImpl;
import com.ctg.udal.daopub.model.ListBean;
@SuppressWarnings({"rawtypes","unchecked"})
public class Test {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://10.128.96.4:3306/account";
	private static final String DBUSER = "db_app";
	private static final String DBPASS = "x9@iuhFd";

	private static final String sql = "SELECT ACCT_ITEM_ID,ITEM_SOURCE_ID,BILL_ID,ACCT_ITEM_TYPE_ID, BILLING_CYCLE_ID,ACCT_ID,SERV_ID,AMOUNT, FEE_CYCLE_ID,STATE,ACC_NBR,ORI_ACCT_ITEM_ID, REGION_ID FROM ACCT_ITEM  WHERE ACCT_ITEM_ID=26 AND STATE=1";

	private static final long count = 100;

	public static void testJdbc() throws Exception {Class.forName(DBDRIVER);
//		Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
//		String select="UPDATE ACCT_ITEM SET ORI_ACCT_ITEM_ID=1 WHERE ACCT_ITEM_ID=26 AND STATE=1 and SLICE_KEY=176455900";
//		PreparedStatement stmt = con.prepareStatement(select);
//	
//		long begin = System.currentTimeMillis();
//		boolean rs=stmt.execute(select);
//		long end = System.currentTimeMillis();
//		System.out.println("PreparedStatement.executeQuery() time=" + (end - begin) / count);
//	
//		con.close();
		
	}

	public static void testSpringJdbc() throws Exception {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DBDRIVER);
		dataSource.setUrl(DBURL);
		dataSource.setUsername(DBUSER);
		dataSource.setPassword(DBPASS);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//Object[] params = new Object[] { 2L, 1L };

		long begin = System.currentTimeMillis();
		List<Map<String, Object>> ls = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> m : ls) {
			System.out.println(m.get("ACCT_ITEM_ID"));
		}
		long end = System.currentTimeMillis();
		System.out.println("JdbcTemplate.queryForList() time=" + (end - begin) / count);
	}

	public static void testSpringJdbc2() throws Exception {
		
	}
	public static void updateTable() throws Exception{
		ApplicationContext act=new FileSystemXmlApplicationContext("F:/MyeclipseWork/ctg-itrdc-account/ctg-itrdc-account-balance-service3/src/main/webapp/WEB-INF/classes/applicationContext.xml");
		TestServiceImpl testimpl = (TestServiceImpl)SpringUtil.getBean("TestServiceImpl");
		testimpl.updatet();
	}

	public static void testDaas() throws Exception {
	//	IJdbcDAO jdbcDAO = SpringUtils.getBean("jdbcDAO");
		ApplicationContext act=new FileSystemXmlApplicationContext("F:/MyeclipseWork/ctg-itrdc-account/ctg-itrdc-account-balance-service3/src/main/webapp/WEB-INF/classes/applicationContext.xml");
		JdbcDaoImpl dl = (JdbcDaoImpl)act.getBean("jdbcDAO");
		ListBean param = new ListBean();
		param.add(2L);
		param.add(1L);

		long begin = System.currentTimeMillis();
		RowSet rs = dl.jdbcQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getLong(1));
		}
		long end = System.currentTimeMillis();
		System.out.println("IJdbcDAO.jdbcQuery() time=" + (end - begin) / count);
	}

	public static void test2(){
		TestServiceImpl testimpl = (TestServiceImpl)SpringUtil.getBean("TestCommitServiceImpl");
		testimpl.testDaas();
		System.out.println("ok");
	}
	
	
	public static void testCommite(){}
	
	public static void test1commit(){
		TestCommitServiceImpl testimpl = (TestCommitServiceImpl)SpringUtil.getBean("TestCommitServiceImpl");
//		testimpl.testDaas();
//		testimpl.testSelect();
//		testimpl.testInsert();
//		testimpl.testMyibatis();
//		testimpl.testSelectMybatis();
//		testimpl.selectSequence();
		System.out.println("ok");
	}
	public static void main(String[] args) throws Exception {
		// testCommite();
		// testSpringJdbc();
		// testSpringJdbc2();
//		 updateTable();

		// testDaas();

		// test2();
		
		test1commit();
//		long id = ClientSeqUtils.getInstance().getSeq("SEQ_TEST");
//		System.out.println(id);

	}}
