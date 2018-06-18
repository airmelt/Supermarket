package com.javasm.supermarket.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javasm.supermarket.goods.Goods;
import com.javasm.supermarket.goods.GoodsDAO;
import com.javasm.supermarket.member.Member;
import com.javasm.supermarket.member.MemberDAO;
import com.javasm.supermarket.order.Order;
import com.javasm.supermarket.order.OrderDAO;
import com.javasm.supermarket.order.OrderDetail;
import com.javasm.supermarket.order.OrderDetailDAO;

/**
 * ClassName: PurchaseService 
 * @Description: 用户可以根据输入ID购买商品,并完成支付
 * @author LiJi
 * @date 2018年6月14日
 */
public class PurchaseService {
	
	Scanner sc = null;
	String confirm = "n";
	int goods_id;
	int num = 1;
	double sum = 0;
	GoodsDAO goodsDAO = GoodsDAO.getInstance();
	OrderDAO orderDAO = OrderDAO.getInstance();
	OrderDetailDAO orderDetailDAO = OrderDetailDAO.getInstance();
	MemberDAO memberDAO = MemberDAO.getInstance();
	Goods goods = new Goods();
	Order order = new Order();
	Date now = null;
	String type = "";
	Member member = null;
	int member_id = 0;
	int order_id;
	
	List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
	
	public void purchaseService(){
		sc = new Scanner(System.in);
		try {
			// 购买商品,直到确认购买
			while(!("y".equals(confirm) || "Y".equals(confirm))) {
				OrderDetail orderDetail = new OrderDetail();
				System.out.println("请输入商品编号:");
				goods_id = Integer.parseInt(sc.next());
				// 生成详细订单并存放到orderDetailList中
				goods = goodsDAO.select(goods_id);
				System.out.println("请输入购买数量(默认为1):");
				num = Integer.parseInt(sc.next());
				if (num > goods.getNum()) {
					System.out.println("商品数量不足");
				} else {
					goods.setNum(goods.getNum() - num);
					goodsDAO.update(goods);
					orderDetail.setNum(num);
					orderDetail.setGoodsId(goods_id);
					orderDetail.setPrice(goods.getPrice() * goods.getDiscount() / 10);
					orderDetailList.add(orderDetail);
					sum += num * goods.getPrice();
				}	
				System.out.println("确定购买?(y/n)");
				confirm = sc.next();
			}
			// 选择支付方式,如果用现金,默认会员id为0
			System.out.println("请输入支付方式:(1.会员卡/2.现金)");
			type = sc.next();
			if("1".equals(type)) {			
				try {
					System.out.println("请输入会员卡号");
					member_id = Integer.parseInt(sc.next());
					member = memberDAO.select(member_id);
					if (member.getBalance() >= sum) {
						member.setBalance(member.getBalance() - sum);
						memberDAO.update(member);
						System.out.println(member.toString());
					} else {
						System.out.println("余额不足");
						type = "2";
					}
				} catch (Exception e) {
					System.out.println("会员卡不存在");
					type = "2";
				}
			} else {
				type = "2";
			}
			// 按当前时间生成订单
			now = new Date(System.currentTimeMillis());
			order.setUserId(member_id);
			order.setSum(sum);
			order.setTime(now);
			order.setType(type);
			// 取得订单id
			order_id = orderDAO.create(order);
			order.setId(order_id);
			System.out.println(order);
			// 用goodList生成详细订单
			for (OrderDetail orderDetail : orderDetailList){
				orderDetail.setOrderId(order_id);
				System.out.println(orderDetail.toString());
				orderDetailDAO.create(orderDetail);
			}				
		} catch (Exception e) {
			System.out.println("输入错误!请重新输入");
			purchaseService();
		}
	}
}
