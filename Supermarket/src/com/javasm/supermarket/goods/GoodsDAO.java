package com.javasm.supermarket.goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javasm.supermarket.database.DataBaseConnection;

/**
 * ClassName: GoodsDAO 
 * @Description: 表goods_info增删改查,遍历;对应goods类
 * @author LiJi
 * @date 2018年6月13日
 */
public class GoodsDAO{

	// 单例模式,保证数据库的准确性
	private GoodsDAO(){}
	private static GoodsDAO goodsDAO = new GoodsDAO();
	public static GoodsDAO getInstance(){
		return goodsDAO;
	}
	
	// id自增
	public void create(Goods goods) {
		String sql = "insert into goods_info(name, type, num, price, discount) values(?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setString(1, goods.getName());
			ps.setInt(2, goods.getType());
			ps.setInt(3, goods.getNum());
			ps.setDouble(4, goods.getPrice());
			ps.setDouble(5, goods.getDiscount());
			ps.executeUpdate();
			System.out.println("在表goods_info中增加了一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public void delete(int id) {
		String sql = "delete from goods_info where id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("在表goods_info中删除了id = " + id + " 的一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public void update(Goods goods) {
		String sql = "update goods_info set name = ?, type = ?, num = ?, price = ?, discount = ? where id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(6, goods.getId());
			ps.setString(1, goods.getName());
			ps.setInt(2, goods.getType());
			ps.setInt(3, goods.getNum());
			ps.setDouble(4, goods.getPrice());
			ps.setDouble(5, goods.getDiscount());
			ps.execute();
			System.out.println("在表goods_info中修改了id = " + goods.getId() + " 的一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public Goods select(int id) {
		Goods goods = null;
		String sql = "select * from goods_info where id = ?";
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
				goods = new Goods(rs);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
		return goods;
	}

	public List<Goods> retrive() {
		List<Goods> goodsList = new ArrayList<Goods>();
		Goods goods = null;
		String sql = "select * from goods_info";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		ResultSet rs = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				goods = new Goods(rs);
				goodsList.add(goods);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	return goodsList;
	}
}
