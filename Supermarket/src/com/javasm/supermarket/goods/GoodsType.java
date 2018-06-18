package com.javasm.supermarket.goods;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javasm.supermarket.database.SuperMarket;

/**
 * ClassName: GoodsType 
 * @Description: 商品类型类,对应goods_type表中的字段,每个实例对象对应一行记录
 * @author LiJi
 * @date 2018年6月12日
 */
public class GoodsType extends SuperMarket{

	// 属性:类型编号,类型名称
	private int id;
	private String name;	
	
	public GoodsType() {
		super();
	}

	public GoodsType(ResultSet rs) {
		super();
		try {
			this.id = rs.getInt("id");
			this.name = rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		;
	}

	public GoodsType(String name) {
		super();
		this.name = name;
	}

	public GoodsType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String toString() {
		return "GoodsType [类型编号=" + id + ", 类型名称=" + name + "]";
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
}
