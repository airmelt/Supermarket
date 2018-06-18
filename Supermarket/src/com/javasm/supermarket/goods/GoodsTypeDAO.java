package com.javasm.supermarket.goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javasm.supermarket.database.DataBaseConnection;

/**
 * ClassName: goodsTypeDAO 
 * @Description: 表goods_type增删改查,遍历
 * @author LiJi
 * @date 2018年6月13日
 */
public class GoodsTypeDAO{

	// 单例模式,保证数据库的准确性
	private GoodsTypeDAO(){}
	private static GoodsTypeDAO goodsTypeDAO = new GoodsTypeDAO();
	public static GoodsTypeDAO getInstance(){
		return goodsTypeDAO;
	}
	
	// id自增
	public void create(GoodsType goodsType){
		String sql = "insert into goods_type (name) values (?)";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setString(1, goodsType.getName());
			ps.executeUpdate();
			System.out.println("在表goods_type中增加了一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public void delete(int id) {
		String sql = "delete from goods_type where id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("在表goods_type中删除了id = " + id + " 的一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public void update(GoodsType goodsType) {
		String sql = "update goodsType_type set name = ? where id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(2, goodsType.getId());
			ps.setString(1, goodsType.getName());
			ps.execute();
			System.out.println("在表goods_type中修改了id = " + goodsType.getId() + " 的一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public GoodsType select(int id) {
		GoodsType goodsType = null;
		String sql = "select * from goods_type where id = ?";
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
				goodsType = new GoodsType(rs);
			}			
			System.out.println(goodsType.toString());
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
		return goodsType;
	}

	public List<GoodsType> retrive() {
		List<GoodsType> goodsTypeList = new ArrayList<GoodsType>();
		GoodsType goodsType = null;
		String sql = "select * from goods_type";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		ResultSet rs = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				goodsType = new GoodsType(rs);
				goodsTypeList.add(goodsType);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	return goodsTypeList;
	}
}

