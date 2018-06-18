package com.javasm.supermarket.user;

/**
 * ClassName: Admin 
 * @Description: 管理员类,静态变量账户密码
 * @author LiJi
 * @date 2018年6月13日
 */
public class Admin {
	
	private static String userId = "admin";
	private static String password = "admin";
	
	public static String getUserId() {
		return userId;
	}
	
	public static void setUserId(String userId) {
		Admin.userId = userId;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static void setPassword(String password) {
		Admin.password = password;
	}
}
