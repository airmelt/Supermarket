package com.javasm.supermarket.goods;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javasm.supermarket.database.SuperMarket;

/**
 * ClassName: Goods 
 * @Description: 商品信息类,对应goods_info表中的字段,每个实例对象对应一行记录
 * @author LiJi
 * @date 2018年6月12日
 */
public class Goods extends SuperMarket {
	
	// 属性:商品编号,商品名,商品类型(外键指向类型ID),商品数量,单价,折扣
	private int id;
	private String name;
	private int type;
	private int num = 0;
	private double price;
	private double discount = 10;
	
	public Goods() {
		super();
	}

	public Goods(ResultSet rs) {
		super();
		try {
			this.id = rs.getInt("id");
			this.name = rs.getString("name");
			this.type = rs.getInt("type");
			this.num = rs.getInt("num");
			this.price = rs.getDouble("price");
			this.discount = rs.getDouble("discount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Goods(String name, int type, int num, double price, double discount) {
		super();
		this.name = name;
		this.type = type;
		this.num = num;
		this.price = price;
		this.discount = discount;
	}

	public Goods(int id, String name, int type, int num, double price, double discount) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.num = num;
		this.price = price;
		this.discount = discount;
	}

	public String toString() {
		return "Goods [商品编号=" + id + ", 商品名=" + name + ", 商品类型=" + type + ", 商品数量=" + num + ", 单价=" + price
				+ ", 折扣=" + discount + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
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
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}	
}
