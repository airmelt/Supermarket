package com.javasm.supermarket.user;

/**
 * ClassName: Cashier 
 * @Description: 收银员类,静态变量账户密码
 * @author LiJi
 * @date 2018年6月13日
 */
public class Cashier {
	
	private static String userId = "cashier";
	private static String password = "cashier";
	
	public static String getUserId() {
		return userId;
	}
	public static void setUserId(String userId) {
		Cashier.userId = userId;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static void setPassword(String password) {
		Cashier.password = password;
	}
}
