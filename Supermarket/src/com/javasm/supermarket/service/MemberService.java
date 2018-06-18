package com.javasm.supermarket.service;

import java.util.List;
import java.util.Scanner;

import com.javasm.supermarket.member.Member;
import com.javasm.supermarket.member.MemberDAO;

/**
 * ClassName: memberService 
 * @Description: 管理会员信息,会员管理拥有增删改查会员信息及充值的方法
 * @author LiJi
 * @date 2018年6月13日
 */
public class MemberService {
	
	Scanner sc = null;
	Member member = new Member();
	int id;
	String name;
	String tel = null;
	double credit = 0;
	double balance = 0;
	MemberDAO memberDAO = MemberDAO.getInstance();
	
	// 按照输入的数字选择会员管理服务
	public void memberService(int n) {
		switch (n) {
		case 1:
			createMember();
			break;
		case 2:
			deleteMember();
			break;
		case 3:
			updateMember();
			break;
		case 4:
			selectMember();
			break;
		case 5:
			retriveMember();
			break;
		case 6:
			recharge();
			break;
		}
	}
	
	// 新建会员
	public void createMember() {
		sc = new Scanner(System.in);
		try {
			System.out.println("新建会员信息");
			System.out.println("请输入会员名字:");
			name = sc.next();
			member.setName(name);
			System.out.println("请输入会员联系方式(默认为空):");
			tel = sc.next();
			member.setTel(tel);
			System.out.println("请输入会员积分(默认为0):");
			credit = Double.parseDouble(sc.next());
			member.setCredit(credit);
			System.out.println("请输入会员余额(默认为0):");
			balance = Double.parseDouble(sc.next());
			member.setBalance(balance);	
			memberDAO.create(member);
		} catch (Exception e) {
			System.out.println("输入有误!请重新输入");
			createMember();
		}	
	}
	
	// 删除会员
	public void deleteMember() {
		sc = new Scanner(System.in);
		try {
			System.out.println("删除会员信息");
			System.out.println("请输入要删除的会员的编号:");
			id = Integer.parseInt(sc.next());
			memberDAO.delete(id);
		} catch (Exception e) {
			System.out.println("删除失败!请重新输入");
			deleteMember();
		}
	}
	
	// 修改属性,一次修改一条会员属性
	public void updateMember() {
		sc = new Scanner(System.in);
		try {
			System.out.println("更新会员信息");
			System.out.println("请输入要更新的会员的编号:");
			id = Integer.parseInt(sc.next());
			member = memberDAO.select(id);
			System.out.println("请输入要更新的会员的信息(1.会员名字 2.会员联系方式 3.会员积分 4.会员余额 )");
			int j = Integer.parseInt(sc.next());
			switch (j) {
			case 1:
				System.out.println("请输入新的会员名字");
				name = sc.next();
				member.setName(name);
				break;
			case 2:
				System.out.println("请输入新的会员联系方式");
				tel = sc.next();
				member.setTel(tel);
				break;
			case 3:
				System.out.println("请输入新的会员积分");
				credit = Double.parseDouble(sc.next());
				member.setCredit(credit);
				break;
			case 4:
				System.out.println("请输入新的会员余额");
				balance = Double.parseDouble(sc.next());
				member.setBalance(balance);
				break;
			default:
				System.out.println("输入有误!请重新输入");
				updateMember();
				break;
			}
			memberDAO.update(member);
		} catch (Exception e) {
			System.out.println("输入的会员编号有误!请重新输入");
			updateMember();
		}
	}
	
	// 查询一条会员的信息
	public void selectMember() {
		sc = new Scanner(System.in);
		try {
			System.out.println("显示会员信息");
			System.out.println("请输入会员编号:");
			id = Integer.parseInt(sc.next());
			member = memberDAO.select(id);
			System.out.println(member.toString());
		} catch (Exception e) {
			System.out.println("输入有误!请重新输入");
			selectMember();
		}	
	}
	
	// 查询所有会员的信息
	public void retriveMember() {
		System.out.println("显示所有的会员信息:");
		List<Member> memberList = memberDAO.retrive();
		for (Member member : memberList){
			System.out.println(member.toString());
		}
	}
	
	// 会员充值
	public void recharge(){
		sc = new Scanner(System.in);
		try {
			System.out.println("会员充值");
			System.out.println("请输入会员编号:");
			id = Integer.parseInt(sc.next());
			member = memberDAO.select(id);
			System.out.println("会员余额 = " + member.getBalance());
			System.out.println("请输入充值金额:");
			double cash = Double.parseDouble(sc.next());
			memberDAO.deposit(cash, id);
		} catch (Exception e) {
			System.out.println("输入有误!请重新输入");
			selectMember();
		}	
	}
}

