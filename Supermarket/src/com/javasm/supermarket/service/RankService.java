package com.javasm.supermarket.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.javasm.supermarket.database.DataBaseConnection;
import com.javasm.supermarket.goods.Goods;
import com.javasm.supermarket.goods.GoodsDAO;
import com.javasm.supermarket.goods.GoodsType;
import com.javasm.supermarket.goods.GoodsTypeDAO;
import com.javasm.supermarket.order.OrderDAO;
import com.javasm.supermarket.order.OrderDetailDAO;

/**
 * ClassName: RankService 
 * @Description: 按月份和按商品类别统计销量前10的商品及总销量
 * @author LiJi
 * @date 2018年6月13日
 */
public class RankService {
	
	GoodsDAO goodsDAO = GoodsDAO.getInstance();
	OrderDAO orderDAO = OrderDAO.getInstance();
	OrderDetailDAO orderDetailDAO = OrderDetailDAO.getInstance();
	GoodsTypeDAO goodsTypeDAO = GoodsTypeDAO.getInstance();
	PreparedStatement ps = null;
	Connection conn = null;
	DataBaseConnection dbc = null;
	ResultSet rs = null;
	Scanner sc = null;
	Goods goods = new Goods();
	GoodsType goodsType = new GoodsType();
	
	// 根据数字选择按商品id或者按会员id查询账单信息
	public void rankService(int n) {
		sc = new Scanner(System.in);
		switch (n) {
		case 1:
			try {
				System.out.println("请输入要查询的月份:");
				int month = Integer.parseInt(sc.next());
				rankByMonth(month);
			} catch (Exception e) {
				rankService(1);
			}			
			break;
		case 2:
			try {
				System.out.println("请输入要查询的商品种类编号:");
				int type = Integer.parseInt(sc.next());
				rankByGoodsType(type);
			} catch (Exception e) {
				rankService(2);
			}			
			break;
		}
	}
	// 按照月份和商品id输出销量排行
	public void rankByMonth(int month) {
		String sql = "select goods_id, sum(num) from order_detail join order_info "
				+ "on order_info.id = order_detail.order_id where (month(time) = '" + month
				+ "' && year(time) = '2018') group by date_format(time, '%Y-%m'),goods_id "
				+ "order by sum(num) desc limit 10";
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			int i = 1;
			// 判断是否有销量数据
			if(rs.next()) {
				// 有销量数据进入循环输出销量排行
				do {
					goods = goodsDAO.select(rs.getInt("goods_id"));
					System.out.printf("销量第%s名 : %s \t\t总销量 : %s" , i, goods.getName(), rs.getInt("sum(num)"));
					System.out.println();
					i++;
				} while(rs.next());
			} else {
				System.out.println("本月无销量数据!");
			}
		} catch (Exception e){
			System.out.println("输入错误!请重新输入");
			rankService(1);
		} finally {
			dbc.close(conn, ps, rs);
		}
	}

	public void rankByGoodsType(int type) {
		String sql = "select id, sum(order_detail.num) as sum from order_detail join goods_info "
				+ "on goods_info.id = order_detail.goods_id where type = " + type 
				+ " group by id order by sum desc limit 10";
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			int i = 1;
			// 判断是否有销量数据
			if(rs.next()) {
				// 有销量数据进入循环输出销量排行
				do {
					goods = goodsDAO.select(rs.getInt("id"));
					System.out.printf("销量第%s名 : %s \t\t总销量 : %s" , i, goods.getName(), rs.getInt("sum"));
					System.out.println();
					i++;
				} while(rs.next());
			} else {
				System.out.println("本商品种类无销量数据!");
			}
		} catch (Exception e){
			System.out.println("输入错误!请重新输入");
			rankService(2);
		} finally {
			dbc.close(conn, ps, rs);
		}
	}
}
