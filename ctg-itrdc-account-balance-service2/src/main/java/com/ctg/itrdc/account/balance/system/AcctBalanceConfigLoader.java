package com.ctg.itrdc.account.balance.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

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
import com.ctg.itrdc.account.balance.util.BaseUtil;

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
		/*Connection conn=null;
		Statement state=null;
		try {
			conn=BaseUtil.getConnection();
			String sql="insert into balance_type_mod(BALANCE_TYPE_ID, PRIORITY, SPE_PAYMENT_ID, MEASURE_METHOD_ID, BALANCE_TYPE_NAME) values(99,1,1,1,'999999')";
			state=conn.createStatement();
			state.execute(sql);
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if(state!=null)
					state.close();
				if(conn!=null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}*/
		
//		balanceConfig.addBalanceType((long)99);
		
//		BalanceTypeModel model99=balanceConfig.getByTypeId((long)99);
		System.out.println("=======================>"+model.getBalanceTypeName());
//		System.out.println("++++++++++++++++++++:"+model.getSpecialPaymentModel().getSpecialPaymentDescModel().getSpePaymentDesc());
//		System.out.println("=======================>"+model99.getBalanceTypeName());
		logger.info(new Date()+"--->>>余额配置信息加载完毕<<<---");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	public static void main(String[] args) {
		new AcctBalanceConfigLoader().contextInitialized(null);
	}
}
