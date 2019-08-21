package com.example.demo.mongodb.common;

import java.net.Inet4Address;


public class Constant {
	public static final String DATABASE_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String CHARSET = "UTF-8";
	public static final int PAGE_NO_DEFAULT = 1;
	public static final int PAGE_SIZE_DEFAULT = 20;
	public static final int PAGE_SIZE_MAX = 1000;
	/** 逗号 **/
	public static final String COMMA_DELIMITER = ",";
	/**本机ip**/
	public static String LOCAL_IP = "";

	static{
		try{
			LOCAL_IP = Inet4Address.getLocalHost().getHostAddress();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
