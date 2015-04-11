package com.ctg.itrdc.account.balance.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/conf/applicationContext*.xml");

	  public static <T> T getBean(String beanId)
	  {
	    return (T) context.getBean(beanId);
	  }

	  public static boolean contains(String beanId) {
	    return context.containsBean(beanId);
	  }
}
