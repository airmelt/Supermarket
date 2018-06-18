package com.javasm.supermarket.order;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javasm.supermarket.database.SuperMarket;

/**
 * ClassName: Order 
 * @Description: 订单信息类,对应order表中的字段,每个实例对象对应一行记录
 * @author LiJi
 * @date 2018年6月12日
 */
public class Order extends SuperMarket{
	
	// 属性:订单编号,会员编号,订单总金额,下单时间,支付类型(现金or余额)
	private int id;
	private int userId = 0;
	private double sum;
	private Date time;
	private String type;
	
	public Order() {
		super();
	}

	public Order(ResultSet rs) {
		super();
		try {
			this.id = rs.getInt("id");
			this.userId = rs.getInt("user_id");
			this.sum = rs.getDouble("sum");
			this.time = rs.getDate("time");
			this.type = rs.getString("type");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Order(int id, int userId, double sum, Date time, String type) {
		super();
		this.id = id;
		this.userId = userId;
		this.sum = sum;
		this.time = time;
		this.type = type;
	}

	public String toString() {
		return "Order [订单编号=" + id + ", 会员编号=" + userId + ", 订单总金额=" + sum + ", 下单时间=" + time + ", 支付类型=" + type + "]";
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public double getSum() {
		return sum;
	}
	
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
