package com.javasm.supermarket;

import java.util.Scanner;

import com.javasm.supermarket.service.GoodsService;
import com.javasm.supermarket.service.GoodsTypeService;
import com.javasm.supermarket.service.MemberService;
import com.javasm.supermarket.service.OrderQueryService;
import com.javasm.supermarket.service.PurchaseService;
import com.javasm.supermarket.service.RankService;
import com.javasm.supermarket.user.Admin;
import com.javasm.supermarket.user.Cashier;

// 程序主入口
public class Menu {
	
	static int menuIndex;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// 循环显示菜单
		while(true) {
		System.out.println("欢迎登陆超市管理系统");
		// 显示一级菜单的方法
		show();
		}
	}

	public static void show() {
		System.out.println("请选择功能:");
		System.out.println("1.商品类型管理/2.商品管理/3.会员管理/4.购买管理/5.订单查询/6.排行统计");	
		
		// 选择二级菜单
		try {
			menuIndex = Integer.parseInt(sc.next());
			selectMenu(menuIndex);
		} catch (NumberFormatException e) {
			System.out.println("请输入数字!");
			show();
		}
	}
	
	// 二级菜单选择方法
	public static void selectMenu(int munuIndex) {
		// 退出标记
		String exit = "y";
		switch (menuIndex) {
		case 1:
		if(isAdmin()){
			while ("y".equals(exit) || "Y".equals(exit)) {
				goodsTypeService();
				System.out.println("是否继续(y/n)");
				exit = sc.next();
			}				
		} else {
			System.out.println("登陆失败!");
			selectMenu(1);
		}
			break;
		case 2:
		if(isAdmin()){
			while ("y".equals(exit) || "Y".equals(exit)) {
				goodsService();
				System.out.println("是否继续(y/n)");
				exit = sc.next();
			}				
		} else {
			System.out.println("登陆失败!");
			selectMenu(2);
		}
			break;
		case 3:
		if(isAdmin()){
			while ("y".equals(exit) || "Y".equals(exit)) {
				memberService();
				System.out.println("是否继续(y/n)");
				exit = sc.next();
			}				
		} else {
			System.out.println("登陆失败!");
			selectMenu(3);
		}
			break;
		case 4:
		if(isCashier()){
			while ("y".equals(exit) || "Y".equals(exit)) {
				purchaseService();
				System.out.println("是否继续(y/n)");
				exit = sc.next();
			}
		} else {
			System.out.println("登陆失败!");
			selectMenu(4);
		}
			break;
		case 5:
		if(isCashier()){
			while ("y".equals(exit) || "Y".equals(exit)) {
				orderQueryService();
				System.out.println("是否继续(y/n)");
				exit = sc.next();
			}
		} else {
			System.out.println("登陆失败!");
			selectMenu(5);
		}
			break;
		case 6:
		if(isCashier()){
			while ("y".equals(exit) || "Y".equals(exit)) {
				rankService();
				System.out.println("是否继续(y/n)");
				exit = sc.next();
			}
		} else {
			System.out.println("登陆失败!");
			selectMenu(6);
		}
			break;
		default:
			System.out.println("输入错误!请重新输入");
			show();
			break;
		}
		show();
	}
		
	
	// 判断管理员
	public static boolean isAdmin() {
		System.out.println("该功能需要管理员权限");
		System.out.println("请输入管理员账户:");
		String userId = sc.next();
		System.out.println("请输入管理员密码:");
		String password = sc.next();
		return (Admin.getUserId().equals(userId) && Admin.getPassword().equals(password));
	}
	
	//判断收银员
	public static boolean isCashier() {
		System.out.println("该功能需要收银员权限");
		System.out.println("请输入收银员账户:");
		String userId = sc.next();
		System.out.println("请输入收银员密码:");
		String password = sc.next();
		return (Cashier.getUserId().equals(userId) && Cashier.getPassword().equals(password));
	}
	
	// 商品类型管理
	public static void goodsTypeService() {
		System.out.println("请输入需要进行的商品类型管理功能:");
		System.out.println("(1.添加/2.删除/3.更新/4.查询单个/5.查询所有/6.返回上一级)");	
		// 选择功能
		int n = 0;
		try {
			n = Integer.parseInt(sc.next());
		} catch (NumberFormatException e) {
			System.out.println("请输入数字!");
			goodsTypeService();
		}
		if (n < 6 && n > 0) {
			GoodsTypeService goodsTypeService = new GoodsTypeService();
			goodsTypeService.goodsTypeService(n);
		} else if (n == 6) {
			show();
		} else {
			System.out.println("输入错误!请重新输入");
			goodsTypeService();
		}
	}
	
	// 商品管理
	public static void goodsService() {
		System.out.println("请输入需要进行的商品管理功能:");
		System.out.println("(1.添加/2.删除/3.更新/4.查询单个/5.查询所有/6.返回上一级)");		
		// 选择功能
		int n = 0;
		try {
			n = Integer.parseInt(sc.next());
		} catch (NumberFormatException e) {
			System.out.println("请输入数字!");
			goodsService();
		}
		if (n < 6 && n > 0) {
			GoodsService goodsService = new GoodsService();
			goodsService.goodsService(n);
		} else if (n == 6) {
			show();
		} else {
			System.out.println("输入错误!请重新输入");
			goodsService();
		}
	}
	
	// 会员管理
	public static void memberService() {
		System.out.println("请输入需要进行的会员管理功能:");
		System.out.println("(1.添加/2.删除/3.更新/4.查询单个/5.查询所有/6.会员充值/7.返回上一级)");	
		// 选择功能
		int n = 0;
		try {
			n = Integer.parseInt(sc.next());
		} catch (NumberFormatException e) {
			System.out.println("请输入数字!");
			memberService();
		}
		if (n < 7 && n > 0) {
			MemberService memberService = new MemberService();
			memberService.memberService(n);
		} else if (n == 7) {
			show();
		} else {
			System.out.println("输入错误!请重新输入");
			memberService();
		}
	}
	
	// 购买管理
	public static void purchaseService() {
		try {
			PurchaseService purchaseService = new PurchaseService();
			purchaseService.purchaseService();
		} catch (Exception e) {
			System.out.println("输入错误!请重新输入");
			purchaseService();
		}			
	}
	
	//订单查询功能
	public static void orderQueryService() {
		System.out.println("请输入需要进行的订单管理功能:");
		System.out.println("(1.按商品编号查询订单信息/2.按会员编号查询订单信息/3.返回上一级)");		
		// 选择功能
		int n = 0;
		try {
			n = Integer.parseInt(sc.next());
		} catch (NumberFormatException e) {
			System.out.println("请输入数字!");
			orderQueryService();
		}
		if (n == 1 || n == 2) {
			OrderQueryService orderQueryService = new OrderQueryService();
			orderQueryService.orderQueryService(n);
		} else if (n == 3) {
			show();
		} else {
			System.out.println("输入错误!请重新输入");
			orderQueryService();
		}
	}
	
	// 排行查询
	public static void rankService() {	
		System.out.println("请输入需要进行的排行功能:");
		System.out.println("(1.按月份查询/2.按商品类型编号查询订单信息/3.返回上一级)");		
		// 选择功能
		int n = 0;
		try {
			n = Integer.parseInt(sc.next());
		} catch (NumberFormatException e) {
			System.out.println("请输入数字!");
			orderQueryService();
		}
		if (n == 1 || n == 2) {
			RankService rankService = new RankService();
			rankService.rankService(n);
		} else if (n == 3) {
			show();
		} else {
			System.out.println("输入错误!请重新输入");
			rankService();
		}
	}
}
