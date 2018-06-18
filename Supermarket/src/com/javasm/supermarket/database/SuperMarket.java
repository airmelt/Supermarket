package com.javasm.supermarket.database;

/**
 * ClassName: SuperMarket 
 * @Description: 作为各数据库对应对象的基类,属性与表中字段对应,每一个对象表示表中的一条记录
 * @author LiJi
 * @date 2018年6月12日
 */
public class SuperMarket {
	
	// 属性:id
	protected int id;

	public String toString() {
		return "SuperMarket [id=" + id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
