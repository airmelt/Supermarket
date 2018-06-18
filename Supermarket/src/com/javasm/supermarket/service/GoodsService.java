package com.javasm.supermarket.service;

import java.util.List;
import java.util.Scanner;

import com.javasm.supermarket.goods.Goods;
import com.javasm.supermarket.goods.GoodsDAO;

/**
 * ClassName: GoodsService 
 * @Description: 管理商品信息,商品管理拥有增删改查商品信息的方法
 * @author LiJi
 * @date 2018年6月13日
 */
public class GoodsService {
	
	Scanner sc = null;
	Goods goods = new Goods();
	int id;
	String name;
	int type;
	int num = 0;
	double price;
	double discount = 10;
	GoodsDAO goodsDAO = GoodsDAO.getInstance();
	
	// 按照输入的数字选择商品管理服务
	public void goodsService(int n) {
		switch (n) {
		case 1:
			createGoods();
			break;
		case 2:
			deleteGoods();
			break;
		case 3:
			updateGoods();
			break;
		case 4:
			selectGoods();
			break;
		case 5:
			retriveGoods();
			break;
		}
	}
	
	// 新建商品
	public void createGoods() {
		sc = new Scanner(System.in);
		try {
			System.out.println("新建商品信息");
			System.out.println("请输入商品名称:");
			name = sc.next();
			goods.setName(name);
			System.out.println("请输入商品种类:");
			type = Integer.parseInt(sc.next());
			goods.setType(type);
			System.out.println("请输入商品数量(默认为0):");
			num = Integer.parseInt(sc.next());
			goods.setNum(num);
			System.out.println("请输入商品单价:");
			price = Double.parseDouble(sc.next());
			goods.setPrice(price);
			System.out.println("请输入商品折扣(默认为10):");
			price = Double.parseDouble(sc.next());
			goods.setDiscount(discount);
			goodsDAO.create(goods);
		} catch (Exception e) {
			System.out.println("输入有误!请重新输入");
			createGoods();
		}	
	}
	
	// 删除商品
	public void deleteGoods() {
		sc = new Scanner(System.in);
		try {
			System.out.println("删除商品信息");
			System.out.println("请输入要删除的商品的编号:");
			id = Integer.parseInt(sc.next());
			goodsDAO.delete(id);
		} catch (Exception e) {
			System.out.println("删除失败!请重新输入");
			deleteGoods();
		}
	}
	
	// 修改属性,一次修改一条商品属性
	public void updateGoods() {
		sc = new Scanner(System.in);
		try {
			System.out.println("更新商品信息");
			System.out.println("请输入要更新的商品的编号:");
			id = Integer.parseInt(sc.next());
			goods = goodsDAO.select(id);
			System.out.println("请输入要更新的商品的信息(1.商品名 2.商品类型 3.商品数量 4.单价 5.折扣)");
			int j = Integer.parseInt(sc.next());
			switch (j) {
			case 1:
				System.out.println("请输入新的商品名");
				name = sc.next();
				goods.setName(name);
				break;
			case 2:
				System.out.println("请输入新的商品类型");
				type = Integer.parseInt(sc.next());
				goods.setType(type);
				break;
			case 3:
				System.out.println("请输入新的商品数量");
				num = Integer.parseInt(sc.next());
				goods.setNum(num);
				break;
			case 4:
				System.out.println("请输入新的商品单价");
				price = Double.parseDouble(sc.next());
				goods.setPrice(price);
				break;
			case 5:
				System.out.println("请输入新的商品折扣");
				discount = Double.parseDouble(sc.next());
				goods.setDiscount(discount);
				break;
			default:
				System.out.println("输入有误!请重新输入");
				updateGoods();
				break;
			}
			goodsDAO.update(goods);
		} catch (Exception e) {
			System.out.println("输入的商品编号有误!请重新输入");
			updateGoods();
		}
	}
	
	// 查询一条商品的信息
	public void selectGoods() {
		sc = new Scanner(System.in);
		try {
			System.out.println("显示商品信息");
			System.out.println("请输入商品编号:");
			id = Integer.parseInt(sc.next());
			goods = goodsDAO.select(id);
			System.out.println(goods.toString());
		} catch (Exception e) {
			System.out.println("输入有误!请重新输入");
			selectGoods();
		}	
	}
	
	// 显示所有的商品的信息
	public void retriveGoods() {
		System.out.println("显示所有的商品信息:");
		List<Goods> goodsList = goodsDAO.retrive();
		for (Goods goods : goodsList){
			System.out.println(goods.toString());
		}
	}
}
