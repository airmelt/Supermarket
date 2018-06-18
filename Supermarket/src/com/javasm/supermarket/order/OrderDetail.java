package com.javasm.supermarket.order;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javasm.supermarket.database.SuperMarket;

/**
 * ClassName: OrderDetail 
 * @Description: 订单详情类,对应order_detail表中的字段,每个实例对象对应一行记录
 * @author LiJi
 * @date 2018年6月12日
 */
public class OrderDetail extends SuperMarket{
	
	// 属性:订单编号(对应订单信息表中的订单编号),商品编号(对应商品信息表中的商品编号),商品数量,商品单价
	private int orderId;
	private int goodsId;
	private int num;
	private double price;
	
	public OrderDetail() {
		super();
	}

	public OrderDetail(ResultSet rs) {
		super();
		try {
			this.orderId = rs.getInt("order_id");
			this.goodsId = rs.getInt("goods_id");
			this.num = rs.getInt("num");
			this.price = rs.getDouble("price");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public OrderDetail(int orderId, int goodsId, int num, double price) {
		super();
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.num = num;
		this.price = price;
	}

	public String toString() {
		return "OrderDetail [订单编号=" + orderId + ", 商品编号=" + goodsId + ", 商品数量=" + num + ", 商品单价=" + price + "]";
	}

	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getGoodsId() {
		return goodsId;
	}
	
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
