package com.javasm.supermarket.service;

import java.util.List;
import java.util.Scanner;

import com.javasm.supermarket.goods.GoodsType;
import com.javasm.supermarket.goods.GoodsTypeDAO;

/**
 * ClassName: GoodsTypeService 
 * @Description: 管理商品类型信息,商品类型管理拥有增删改查商品类型信息的方法
 * @author LiJi
 * @date 2018年6月13日
 */
public class GoodsTypeService {
	
	Scanner sc = null;
	GoodsType goodsType = new GoodsType();
	int id;
	String name;
	GoodsTypeDAO goodsTypeDAO = GoodsTypeDAO.getInstance();
	
	// 按照输入的数字选择商品类型管理服务
	public void goodsTypeService(int n) {
		switch (n) {
		case 1:
			createGoodsType();
			break;
		case 2:
			deleteGoodsType();
			break;
		case 3:
			updateGoodsType();
			break;
		case 4:
			selectGoodsType();
			break;
		case 5:
			retriveGoodsType();
			break;
		}
	}
	
	// 新建商品类型
	public void createGoodsType() {
		sc = new Scanner(System.in);
		try {
			System.out.println("新建商品类型信息");
			System.out.println("请输入商品类型名称:");
			name = sc.next();
			goodsType.setName(name);
			goodsTypeDAO.create(goodsType);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("输入有误!请重新输入");
			createGoodsType();
		}
	}
	
	// 删除商品类型
	public void deleteGoodsType() {
		sc = new Scanner(System.in);
		try {
			System.out.println("删除商品类型信息");
			System.out.println("请输入要删除的商品类型的编号:");
			id = Integer.parseInt(sc.next());
			goodsTypeDAO.delete(id);
		} catch (Exception e) {
			System.out.println("删除失败!请重新输入");
			deleteGoodsType();
		} 
	}
	
	// 修改属性
	public void updateGoodsType() {
		sc = new Scanner(System.in);
		try {
			System.out.println("更新商品类型信息");
			System.out.println("请输入要更新的商品类型的编号:");
			id = Integer.parseInt(sc.next());
			goodsType = goodsTypeDAO.select(id);
			System.out.println("请输入新的商品类型名");
			name = sc.next();
			goodsType.setName(name);
			goodsTypeDAO.update(goodsType);
		} catch (Exception e) {
			System.out.println("输入的商品类型编号有误!请重新输入");
			updateGoodsType();
		}
	}
	
	// 查询一条商品类型的信息
	public void selectGoodsType() {
		sc = new Scanner(System.in);
		try {
			System.out.println("显示商品类型信息");
			System.out.println("请输入商品类型编号:");
			id = Integer.parseInt(sc.next());
			goodsType = goodsTypeDAO.select(id);
			System.out.println(goodsType.toString());
		} catch (Exception e) {
			System.out.println("输入有误!请重新输入");
			selectGoodsType();
		}
	}
	
	// 查询所有的商品类型的信息
	public void retriveGoodsType() {
		System.out.println("显示所有的商品信息:");
		List<GoodsType> goodsTypeList = goodsTypeDAO.retrive();
		for (GoodsType goodsType : goodsTypeList){
			System.out.println(goodsType.toString());
		}
	}
}

