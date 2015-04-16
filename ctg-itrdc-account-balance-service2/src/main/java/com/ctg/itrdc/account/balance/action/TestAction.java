package com.ctg.itrdc.account.balance.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ctg.itrdc.account.balance.service.impl.TestCommitServiceImpl;
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
//		testCommitServiceImpl.testMyibatis();
		return SUCCESS;
	}
	
	public String showBalance()throws Exception{
		return SUCCESS;
	}
	
	public String queryBalance()throws Exception{
		return SUCCESS;
	}
}
