package com.ctg.itrdc.account.balance.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
	

	
	/**
	 * 
	 * @desc 时间字符串转换成日期类型
	 * @author ls
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date stringToDate(Object dateStr, String pattern) throws Exception{
		Date date = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		date = sdf.parse(String.valueOf(dateStr));
		return date;
	}
	
	/**
	 * 
	 * @desc 日期类型转换成时间字符串
	 * @author ls
	 * @param date
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static String dateToString(Date date, String pattern) throws Exception{
		String dateStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		dateStr = sdf.format(date);
		return dateStr;
	}
	
	/**
	 * 
	 * @desc 生成csv，txt文件，以\r\n换行
	 * @author ls
	 * @param content
	 * @param filePath
	 * @return
	 */
	public static boolean newExcelFile(String content,String filePath,String fileName){
		File fileMkdir = null;
		File file = null;
		FileOutputStream outputStream = null;
		try {
			fileMkdir = new File(filePath);
			if (!fileMkdir.exists()) {
				fileMkdir.mkdirs();
			}
			file = new File(fileMkdir, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			outputStream = new FileOutputStream(file);
			outputStream.write(content.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
//		System.out.println(getPropertyUrl("conf/menu.xml"));
		//清空目录中的文件
		File[] fileList = (new File("D:/logs/balance/balanceTypeFail/")).listFiles();
		for (File rmFile : fileList) {
			rmFile.delete();
		}
		
	}
}
