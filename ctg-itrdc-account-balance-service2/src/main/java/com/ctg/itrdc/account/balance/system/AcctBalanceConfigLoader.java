package com.ctg.itrdc.account.balance.system;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ctg.itrdc.account.balance.dao.impl.MapCacheManager;
import com.ctg.itrdc.account.balance.model.AcctBalanceConfig;
import com.ctg.itrdc.account.balance.model.BalanceConfig;
import com.ctg.itrdc.account.balance.model.BalanceTypeModel;
import com.ctg.itrdc.account.balance.repository.IBalanceTypeMapper;

@Service
public class AcctBalanceConfigLoader implements ServletContextListener {
	
	private Logger logger = LoggerFactory.getLogger(AcctBalanceConfigLoader.class);
 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info(new Date()+"--->>>开始加载余额配置信息<<<---");
		/*WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		iBalanceTypeMapper =(IBalanceTypeMapper) ctx.getBean("IBalanceTypeMapper");
		AcctBalanceConfig.setBalanceTypeList(iBalanceTypeMapper.selectAllBalanceType());*/
		BalanceConfig balanceConfig =BalanceConfig.getInstance();
		BalanceTypeModel model=balanceConfig.getByTypeId((long)1);
		System.out.println("=======================>"+model.getCreateDate());
		logger.info(new Date()+"--->>>余额配置信息加载完毕<<<---");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
