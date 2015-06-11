package com.ctg.itrdc.account.balance.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseUtil {
	public static String getPropertyUrl(String confUrl) {

		Class c = null;
		try {
			c = Class
					.forName("com.ctg.itrdc.account.balance.util.BaseUtil");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File(c.getResource("").getPath());
		String url= f.getParentFile().getParentFile().getParentFile()
		.getParentFile().getParentFile().getParent()
		+ File.separator+confUrl;
		return url;

	}
	/*public static String getMenuUrl() {

		Class c = null;
		try {
			c = Class
					.forName("com.ctg.itrdc.account.balance.util.BaseUtil");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File(c.getResource("").getPath());
		String url= f.getParentFile().getParentFile().getParentFile()
		.getParentFile().getParentFile().getParent()
		+ File.separator+"conf/menu.xml";
		return url;

	}
	
	public static String getJdbcUrl() {

		Class c = null;
		try {
			c = Class
					.forName("com.ctg.itrdc.account.balance.util.BaseUtil");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File(c.getResource("").getPath());
		String url= f.getParentFile().getParentFile().getParentFile()
		.getParentFile().getParentFile().getParent()
		+ File.separator+"conf/jdbc.properties";
		return url;

	}*/
	
	public static String getProperty(String propertyName){
		String pro="";
		try {
			String url=getPropertyUrl("conf/config.properties");
			FileInputStream jdbcinput = new FileInputStream(url);
			Properties jdbcpro = new Properties();
			jdbcpro.load(jdbcinput);
			pro=jdbcpro.getProperty(propertyName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pro;
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			FileInputStream jdbcinput = new FileInputStream(getPropertyUrl("conf/jdbc.properties"));
			Properties jdbcpro = new Properties();
			jdbcpro.load(jdbcinput);
			Class.forName(jdbcpro.getProperty("jdbc.driverClassName")).newInstance();
			conn = DriverManager.getConnection(jdbcpro.getProperty("jdbc.url"),
					jdbcpro.getProperty("jdbc.username"), jdbcpro
							.getProperty("jdbc.password"));
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		System.out.println(getPropertyUrl("conf/menu.xml"));
	}
	
	/**
	 * 
	 * @desc 时间字符串转换成日期类型
	 * @author ls
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date stringToDate(Object dateStr, String pattern){
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			date = sdf.parse(String.valueOf(dateStr));
		} catch (NullPointerException e) {
			System.err.println("时间格式为空." + e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println("时间格式不合法." + e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("时间解析错误." + e.getMessage());
			e.printStackTrace();
		}
		return date;
	}
}
