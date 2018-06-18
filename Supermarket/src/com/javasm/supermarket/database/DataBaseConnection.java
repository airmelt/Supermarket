package com.javasm.supermarket.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName: DataBaseConnection 
 * @Description: 专门负责数据库的打开与关闭操作的类
 * @author LiJi
 * @date 2018年6月12日
 */

public class DataBaseConnection {
	
	// 数据库连接地址,用户名及密码
	private final String URL = "jdbc:mysql://192.168.2.229:3306/market";
	private final String USERNAME = "root";
	private final String PASSWORD = "asd123";
	private Connection conn = null;
	
	// 加载驱动
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 数据库连接方法
	public DataBaseConnection() {
		try {
			this.conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("数据库驱动加载失败!");
			e.printStackTrace();
		}
	}

	/**
	 * 返回一个连接对象
	 * @param @return
	 * @param @throws SQLException   
	 * @return Connection  
	 * @throws
	 */
	public Connection getConnection() throws SQLException {
		return conn;
	}
	
	/**
	 * 关闭数据库方法
	 * @param @param conn
	 * @param @param ps   
	 * @return void  
	 * @throws
	 */
	public void close(Connection conn, PreparedStatement ps){
		close(conn, ps, null);
	}
	
	/**
	 * 关闭数据库方法
	 * @param @param conn
	 * @param @param ps
	 * @param @param rs   
	 * @return void  
	 * @throws
	 */
	public void close(Connection conn, PreparedStatement ps, ResultSet rs){
		try {
			if(conn != null){
				conn.close();
			}	
			if(ps != null){
				ps.close();
			}
			if(rs != null){
				rs.close();
			}
		} catch (Exception e) {
			System.out.println("数据库连接关闭失败!");
			e.printStackTrace();
		}
	}
}
