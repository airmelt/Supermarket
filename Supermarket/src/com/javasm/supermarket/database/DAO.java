package com.javasm.supermarket.database;

import java.util.*;

/**
 * ClassName: DAO 
 * @Description: 定义操作的接口,定义一系列数据库的原子性操作,增删改查
 * @author LiJi
 * @date 2018年6月12日
 */
public interface DAO {
	
	// 数据库增删改查,查询所有记录
	public void create(SuperMarket supermarket) throws Exception;
	public void delete(int id) throws Exception;
	public void update(SuperMarket supermarket) throws Exception;
	public SuperMarket select(int id) throws Exception;
	public List<? extends SuperMarket> retrive() throws Exception;
}
