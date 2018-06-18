package com.javasm.supermarket.member;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javasm.supermarket.database.SuperMarket;

/**
 * 
 * ClassName: Member 
 * @Description: 会员类,对应member表中的字段,每个实例对象对应一行记录
 * @author LiJi
 * @date 2018年6月12日
 */
public class Member extends SuperMarket{
	
	// 属性:会员编号,会员名,联系方式,积分,余额
	private int id;
	private String name;
	private String tel = null;
	private double credit = 0;
	private double balance = 0;
	
	public Member() {
		super();
	}
	
	public Member(ResultSet rs) {
		super();
		try {
			this.id = rs.getInt("id");
			this.name = rs.getString("name");
			this.tel = rs.getString("tel");
			this.credit = rs.getDouble("credit");
			this.balance = rs.getDouble("balance");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public Member(String name) {
		super();
		this.name = name;
	}

	public Member(String name, String tel) {
		super();
		this.name = name;
		this.tel = tel;
	}

	public Member(int id, String name, String tel, float credit, float balance) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.credit = credit;
		this.balance = balance;
	}
	
	public String toString() {
		return "Member [会员编号=" + id + ", 会员名=" + name + ", 联系方式=" + tel + ", 积分=" + credit + ", 余额=" + balance
				+ "]";
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
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public double getCredit() {
		return credit;
	}
	
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
