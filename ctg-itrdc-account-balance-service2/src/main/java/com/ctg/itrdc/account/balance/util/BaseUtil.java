package com.ctg.itrdc.account.balance.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	/**
	 * 
	 * @desc double类型精确相加
	 * @author ls
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static double add(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 
	 * @desc double类型精确相减
	 * @author ls
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
	
	
	public static String formatDouble(double v1,int bit){
		StringBuffer format = new StringBuffer("#");
		for (int i = 0; i < bit; i++) {
			if (i == 0) {
				format.append(".0");
			}else{
				format.append("0");
			}
			
		}
		DecimalFormat df = new DecimalFormat(format.toString());
        return v1<1?"0"+df.format(v1):df.format(v1);
	}
	
	/**
	 * 
	 * @desc 提示内容处理
	 * @author ls
	 * @param str
	 * @return
	 */
	public static String hintResult(String str){
		Pattern p = Pattern.compile("[0-9]");
		Matcher m = p.matcher(str);
		String status = "0";
		if (!m.find()) {
			status = str;
		}
		return status;
	}
	
	public static void main(String[] args) {
//		System.out.println(getPropertyUrl("conf/menu.xml"));
		//清空目录中的文件
		/*File[] fileList = (new File("D:/logs/balance/balanceTypeFail/")).listFiles();
		for (File rmFile : fileList) {
			rmFile.delete();
		}*/
	}
}
