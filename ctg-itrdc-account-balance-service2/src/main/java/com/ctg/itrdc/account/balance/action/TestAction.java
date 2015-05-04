package com.ctg.itrdc.account.balance.action;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ctg.itrdc.account.balance.service.impl.TestCommitServiceImpl;
/*import com.ctg.udal.index.GlobalIndexService;
import com.ctg.udal.index.IndexQuery;
import com.ctg.udal.index.internal.IndexProvider;
import com.ctg.udal.index.server.jdbc.JDBCClient;*/
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class TestAction extends ActionSupport{
	
	private TestCommitServiceImpl testCommitServiceImpl;
	
	public TestCommitServiceImpl getTestCommitServiceImpl() {
		return testCommitServiceImpl;
	}
	@Autowired
	public void setTestCommitServiceImpl(TestCommitServiceImpl testCommitServiceImpl) {
		this.testCommitServiceImpl = testCommitServiceImpl;
	}

	public String toTest() throws Exception{
//		testCommitServiceImpl.testMapperInterface();
//		testCommitServiceImpl.testSelectMybatis();
//		testCommitServiceImpl.selectSequence();
//		testCommitServiceImpl.insertAcctBalance();
//		testCommitServiceImpl.selectPrimaryKey();
//		testCommitServiceImpl.insertGlobal();
		
		
//		testCommitServiceImpl.selectAcctBalance();
		
		/*File file=new File("F:/ctg/ctg-itrdc-account-balance-service2/src/main/resources/conf/index.properties");
		Properties pro=new Properties();
		pro.load(new FileInputStream(file));
		IndexProvider provider = IndexProvider.getProvider(pro);
		IndexQuery index = provider.createIndexQuery();
		String CUST_ID=index.get("ITRDC-CRM.KH_KH_CUST.CUST_NUMBER.CUST_ID", "3200000400020000");
		System.out.println("CUST_ID:"+CUST_ID);*/
		
		/*Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://10.142.90.19:8867/ITRDC-CRM", "itrdc-mkt", "itrdc-mkt");
		// 取得操作接口, 单链接只推荐单线程操作
		GlobalIndexService service = JDBCClient.use(con);
		String CUST_ID=service.queryForString("ITRDC-CRM.KH_KH_CUST.CUST_NUMBER.CUST_ID", "3200000400020000");
		
		System.out.println("CUST_ID:"+CUST_ID);*/
		
		/*Long seq=ClientSeqUtils.getInstance().getSeq("SEQ_TEST");
		System.out.println("seq:"+seq);*/
		
//		testCommitServiceImpl.testHit();
		
		return SUCCESS;
	}
	
	public String showBalance()throws Exception{
		return SUCCESS;
	}
	
	public String queryBalance()throws Exception{
		return SUCCESS;
	}
}
