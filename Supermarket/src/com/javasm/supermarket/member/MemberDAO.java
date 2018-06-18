package com.javasm.supermarket.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javasm.supermarket.database.DataBaseConnection;


/**
 * ClassName: memberDAO 
 * @Description: 表member增删改查,遍历,会员充值
 * @author LiJi
 * @date 2018年6月13日
 */
public class MemberDAO{

	// 单例模式,保证数据库的准确性
	private MemberDAO(){}
	private static MemberDAO memberDAO = new MemberDAO();
	public static MemberDAO getInstance(){
		return memberDAO;
	}
	
	// id自增
	public void create(Member member){
		String sql = "insert into member(name, tel, credit, balance) values(?, ?, ?, ?)";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getTel());
			ps.setDouble(3, member.getCredit());
			ps.setDouble(4, member.getBalance());
			ps.executeUpdate();
			System.out.println("在表member中增加了一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public void delete(int id) {
		String sql = "delete from member where id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("在表member中删除了id = " + id + " 的一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public void update(Member member) {
		String sql = "update member set name = ?, tel = ?, credit = ?, balance = ? where id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(5, member.getId());
			ps.setString(1, member.getName());
			ps.setString(2, member.getTel());
			ps.setDouble(3, member.getCredit());
			ps.setDouble(4, member.getBalance());
			ps.execute();
			System.out.println("在表member中修改了id = " + member.getId() + " 的一行");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}

	public Member select(int id) {
		Member member = null;
		String sql = "select * from member where id = ?";
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
				member = new Member(rs);
			}			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
		return member;
	}

	public List<Member> retrive() {
		List<Member> memberList = new ArrayList<Member>();
		Member member = null;
		String sql = "select * from member";
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		ResultSet rs = null;
		try {
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				member = new Member(rs);
				memberList.add(member);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	return memberList;
	}
	
	/**
	 * 会员充值的方法,传入会员的id和充值金额,将会员(Member类)本身的余额加上充值金额赋给会员的余额
	 * @param @param cash
	 * @param @param id   
	 * @return void  
	 * @throws
	 */
	public void deposit(double cash, int id) {	
		String sql = "update member set balance = ? where id = ?";
		Member member = select(id);
		double balance = member.getBalance() + cash;
		PreparedStatement ps = null;
		Connection conn = null;
		DataBaseConnection dbc = null;
		try {			
			dbc = new DataBaseConnection();
			ps = dbc.getConnection().prepareStatement(sql);
			ps.setInt(2, member.getId());
			ps.setDouble(1, balance);
			ps.execute();
			member.setBalance(balance);
			System.out.println("充值成功!");
			System.out.println("会员余额 = " + member.getBalance());
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			dbc.close(conn, ps);
		}
	}
}

