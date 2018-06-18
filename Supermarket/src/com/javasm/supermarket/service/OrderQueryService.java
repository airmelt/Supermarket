package com.javasm.supermarket.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javasm.supermarket.database.DataBaseConnection;
import com.javasm.supermarket.order.Order;
import com.javasm.supermarket.order.OrderDAO;
import com.javasm.supermarket.order.OrderDetail;
import com.javasm.supermarket.order.OrderDetailDAO;

/**
 * 
 * ClassName: OrderQueryService 
 * @Description: 用户输入商品编号和会员编号查询出对应订单记录
 * 输入商品编号或是会员编号,在控制台显示简要订单记录及详细订单记录
 * @author LiJi
 * @date 2018年6月14日
 */
public class OrderQueryService {
	
	String sql;
	OrderDAO orderDAO = OrderDAO.getInstance();
	OrderDetailDAO orderDetailDAO = OrderDetailDAO.getInstance();
	PreparedStatement ps = null;
	Connection conn = null;
	DataBaseConnection dbc = null;
	ResultSet rs = null;
	Scanner sc = null;
	List<Order> orderList = new ArrayList<>();
	List<OrderDetail> orderDetailList = new ArrayList<>();
	Order order = new Order();
	
	// 根据数字选择按商品id或者按会员id查询账单信息
	public void orderQueryService(int n) {
		switch (n) {
		case 1:
			orderQueryByGoods();
			break;
		case 2:
			orderQueryByMember();
			break;
		}
	}
	
	// 按商品id查询订单信息
	public void orderQueryByGoods() {
		sc = new Scanner(System.in);
		try {
			System.out.println("按商品编号查询订单信息");
			System.out.println("请输入商品编号:");
			int goods_id = Integer.parseInt(sc.next());
			orderDetailList = orderDetailDAO.select(goods_id, "goods_");
			for (OrderDetail orderDetail : orderDetailList){
				order = orderDAO.select(orderDetail.getOrderId(), "");
				orderList.add(order);
			}
		} catch (Exception e) {
			System.out.println("输入有误!请重新输入");
			orderQueryByGoods();
		}	
	}
	
	// 按会员id查询订单信息
	public void orderQueryByMember() {
		sc = new Scanner(System.in);
		try {
			System.out.println("按会员编号查询订单信息");
			System.out.println("请输入会员编号:");
			int user_id = Integer.parseInt(sc.next());
			order = orderDAO.select(user_id, "user_");
			int order_id = order.getId();
			orderDetailList = orderDetailDAO.select(order_id, "order_");
		} catch (Exception e) {
			System.out.println("输入有误!请重新输入");
			orderQueryByGoods();
		}	
	}
}
