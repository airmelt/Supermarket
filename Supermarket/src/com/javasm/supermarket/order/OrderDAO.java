package com.javasm.supermarket.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javasm.supermarket.database.DataBaseConnection;

/**
 * ClassName: orderDAO 
 * @Description: 表order增删改查,遍历
 * @author LiJi
 * @date 2018年6月13日
 */
public class OrderDAO{

	// 单例模式,保证数据库的准确性
	private OrderDAO(){}
	private static OrderDAO orderDAO = new OrderDAO();
	public static OrderDAO getInstance(){
		return orderDAO;
	}
	
	/**
	 * 返回自增id
	 * @param @param order
	 * @param @return   
	 * @return int  
	 * @throws
	 */
	public int create(Order order){
		String sql = "insert into order_info (user_id, sum, time, type) values("
				+ order.getUserId() + ", " + order.getSum() + ", '" + order.getTime() +"', '" +
				order.getType() + "')";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		ResultSet rs = null;
		int autoIncKey = 0;
		try {
			dbc = new DataBaseConnection();
			// 拿到自增id
			ps = dbc.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			System.out.println("在表order_info中增加了一行"); 
			rs = ps.getGeneratedKeys();  
			if (rs.next()) {  
				autoIncKey = rs.getInt(1);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps, rs);
		}
		return autoIncKey;
	}

	public void delete(int id) {
		String sql = "delete from order_info where id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("在表order_info中删除了id = " + id + " 的一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public void update(Order order) {
		String sql = "update order_info set user_id = " + order.getUserId() + ", sum = " + order.getSum() + 
				", time = '" + order.getTime() + "', type = " + order.getType() + " where id = " + order.getId();
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(4, order.getId());
			ps.setInt(1, order.getUserId());
			ps.setDouble(2, order.getSum());
//			ps.setDate(3, order.getTime());
			ps.setString(3, order.getType());
			ps.execute();
			System.out.println("在表order_info中修改了id = " + order.getId() + " 的一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public Order select(int id, String col) {
		Order order = null;
		String sql = "select * from order_info where " + col + "id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		ResultSet rs = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				order = new Order(rs);
			}		
			System.out.println(order.toString());
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
		return order;
	}

	public List<Order> retrive() {
		List<Order> orderList = new ArrayList<Order>();
		Order order = null;
		String sql = "select * from order_info";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		ResultSet rs = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				order = new Order(rs);
				orderList.add(order);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	return orderList;
	}
}
