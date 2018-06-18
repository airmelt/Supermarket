package com.javasm.supermarket.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javasm.supermarket.database.DataBaseConnection;

/**
 * ClassName: orderdetailDAO 
 * @Description: 表order_detail增删改查,遍历
 * @author LiJi
 * @date 2018年6月13日
 */
public class OrderDetailDAO{
	
	// 单例模式,保证数据库的准确性
	private OrderDetailDAO(){}
	private static OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
	public static OrderDetailDAO getInstance(){
		return orderDetailDAO;
	}
	
	public void create(OrderDetail orderdetail){
		String sql = "insert into order_detail(order_id, goods_id, num, price) values(?, ?, ?, ?)";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(1, orderdetail.getOrderId());
			ps.setInt(2, orderdetail.getGoodsId());
			ps.setInt(3, orderdetail.getNum());
			ps.setDouble(4, orderdetail.getPrice());
			ps.executeUpdate();
			System.out.println("在表order_detail中增加了一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public void delete(int order_id) {
		String sql = "delete from order_detail where order_id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(1, order_id);
			ps.executeUpdate();
			System.out.println("在表order_detail中删除了order_id = " + order_id + " 的一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public void update(OrderDetail orderdetail) {
		String sql = "update order_detail set goods_id = ?, num = ?, price = ? where order_id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(4, orderdetail.getId());
			ps.setInt(1, orderdetail.getGoodsId());
			ps.setInt(2, orderdetail.getNum());
			ps.setDouble(3, orderdetail.getPrice());
			ps.execute();
			System.out.println("在表order_detail中修改了id = " + orderdetail.getOrderId() + " 的一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public List<OrderDetail> select(int id, String col) {
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		OrderDetail orderdetail = null;
		String sql = "select * from order_detail where " + col + "id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		ResultSet rs = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				orderdetail = new OrderDetail(rs);
				System.out.println(orderdetail.toString());
				orderDetailList.add(orderdetail);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps, rs);
		}
	return orderDetailList;
	}

	public List<OrderDetail> retrive() {
		List<OrderDetail> orderdetailList = new ArrayList<OrderDetail>();
		OrderDetail orderdetail = null;
		String sql = "select * from order_detail";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		ResultSet rs = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				orderdetail = new OrderDetail(rs);
				orderdetailList.add(orderdetail);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	return orderdetailList;
	}
}
