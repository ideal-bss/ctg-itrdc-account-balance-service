package com.ctg.itrdc.account.balance.action;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	private static final String DBURL = "jdbc:mysql://10.142.90.19:8866/TESTDB";
	private static final String DBUSER = "udal";
	private static final String DBPASS = "Ab123456!";

	private static final String sql = "select a.ACCT_BALANCE_ID,a.ACCT_ID,a.BALANCE,a.BALANCE_TYPE_ID,a.CREATE_DATE,a.CYCLE_LOWER,a.CYCLE_UPPER,a.EFF_DATE,a.EXP_DATE from acct_balance a,balance_share_rule b where a.ACCT_BALANCE_ID=b.ACCT_BALANCE_ID and"+
	" a.ACCT_ID>0 limit 4,100";

	private static final long count = 100;

	public static void testJdbc() throws Exception {
		Class.forName(DBDRIVER);
		Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs= stmt.executeQuery();
		
		String insertsql=" INSERT INTO acct_balance_mod (ACCT_BALANCE_ID,ACCT_ID,SUB_ACCT_ID,BALANCE,"
		+"BALANCE_TYPE_ID,CREATE_DATE,CYCLE_LOWER,CYCLE_UPPER,EFF_DATE,EXP_DATE,SLICE_KEY) values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=null;
		while(rs.next()){
			pst=con.prepareStatement(insertsql);
			pst.setInt(1, rs.getInt("ACCT_BALANCE_ID"));
			pst.setInt(2, rs.getInt("ACCT_ID"));
			pst.setInt(3, rs.getInt("ACCT_ID"));//SUB_ACCT_ID
			pst.setInt(4, rs.getInt("BALANCE"));
			
			pst.setInt(5, rs.getInt("BALANCE_TYPE_ID"));
			pst.setDate(6, null);
			pst.setInt(7, rs.getInt("CYCLE_LOWER"));
			pst.setInt(8, rs.getInt("CYCLE_UPPER"));
			pst.setDate(9, rs.getDate("EFF_DATE"));
			pst.setDate(10, null);
			pst.setInt(11, rs.getInt("ACCT_ID"));//SLICE_KEY
			int res=pst.executeUpdate();
			System.out.println("res:"+res);
			if(pst!=null)
				pst.close();
		}
		if(stmt!=null)
			stmt.close();
		con.close();
		
	}
	
	public static void insertShare() throws Exception {
		Class.forName(DBDRIVER);
		Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		String sql="select b.ACCT_BALANCE_ID,b.SHARE_RULE_ID,b.SHARE_RULE_TYPE_ID,b.OBJECT_ID,b.OBJECT_TYPE,"+
		"b.SHARE_RULE_TYPE_PRI,b.UPPER_AMOUNT,b.LOWER_AMOUNT,b.STATE,b.ACCT_ID from balance_share_rule b "+
		"where b.ACCT_BALANCE_ID=1000000043";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs= stmt.executeQuery();
		
		String insertsql=" INSERT INTO balance_share_rule_mod (ACCT_BALANCE_ID,SHARE_RULE_ID,SHARE_RULE_TYPE_ID,"+
		"OBJECT_ID,OBJECT_TYPE,SHARE_RULE_TYPE_PRI,UPPER_AMOUNT,LOWER_AMOUNT,STATUS_CD,SLICE_KEY) "
				+"values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=null;
		while(rs.next()){
			pst=con.prepareStatement(insertsql);
			pst.setInt(1, rs.getInt("ACCT_BALANCE_ID"));
			pst.setInt(2, rs.getInt("SHARE_RULE_ID"));
			pst.setInt(3, rs.getInt("SHARE_RULE_TYPE_ID"));//SUB_ACCT_ID
			pst.setInt(4, rs.getInt("OBJECT_ID"));
			
			pst.setInt(5, rs.getInt("OBJECT_TYPE"));
			pst.setInt(6, rs.getInt("SHARE_RULE_TYPE_PRI"));
			pst.setInt(7, rs.getInt("UPPER_AMOUNT"));
			pst.setInt(8, rs.getInt("LOWER_AMOUNT"));
			pst.setInt(9, rs.getInt("STATE"));
			pst.setInt(10,rs.getInt("ACCT_ID"));
			int res=pst.executeUpdate();
			System.out.println("res:"+res);
			if(pst!=null)
				pst.close();
		}
		if(stmt!=null)
			stmt.close();
		con.close();
		
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
//		java.util.concurrent.ConcurrentHashMap<K, V>
//		test1commit();
//		long id = ClientSeqUtils.getInstance().getSeq("SEQ_TEST");
//		System.out.println(id);
		insertShare();
	}}
