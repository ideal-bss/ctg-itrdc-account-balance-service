package com.ctg.itrdc.account.balance.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class BaseUtil {
	public static String getPropertyUrl() {

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
		+ File.separator+"conf/config.properties";
		return url;

	}
	public static String getProperty(String propertyName){
		String pro="";
		try {
			String url=getPropertyUrl();
			FileInputStream jdbcinput = new FileInputStream(url);
			Properties jdbcpro = new Properties();
			jdbcpro.load(jdbcinput);
			pro=jdbcpro.getProperty(propertyName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pro;
	}
	public static void main(String[] args) {
		System.out.println(getProperty("balance.normal.expdate"));
	}
}
