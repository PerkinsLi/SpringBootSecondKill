package com.perkins.SpringBootSecondKill.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.perkins.SpringBootSecondKill.domain.Goods;


public class AddGoodsUtil {
	public static void test() throws Exception {

		int count = 50;
		List<Goods> lGoods = new ArrayList<Goods>(count);
		//生成用户
		for(int i=0;i<count;i++) {
			Goods goods = new Goods();
			goods.setId(1300L+i);
			goods.setGoodsDetail("真心 南瓜子168g*2袋原味南瓜子零食坚果炒货特产葵花籽休闲食品原味 分享装 撒发了沙发沙发上啊沙发上最后被隐藏的");
			goods.setGoodsImg("../img/20180323223837.jpg");
			goods.setGoodsName("南瓜子");
			goods.setGoodsPrice(6.0);
			goods.setGoodsStock(10);
			goods.setGoodsTitle("真心 南瓜子168g*2袋原味南瓜子零食坚果炒货特产葵花籽休闲食品原味 分享装 撒发了沙发沙发上啊沙发上最后被隐藏的");
			lGoods.add(goods);
		}
		
		
		
		
		
	System.out.println("create goods");
	//插入数据库
	Connection conn = DBUtil.getConn();
	String sql = "INSERT INTO goods (id,goods_name, goods_title, goods_img, goods_detail, goods_price, goods_stock) VALUES (?,?, ?,?, ?, ?,?)";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	for(int i=0;i<lGoods.size();i++) {
		Goods goods = lGoods.get(i);
		pstmt.setLong(1, goods.getId());
		pstmt.setString(2, goods.getGoodsName());
		pstmt.setString(3, goods.getGoodsTitle());
		pstmt.setString(4, goods.getGoodsImg());
		pstmt.setString(5, goods.getGoodsDetail());
		pstmt.setDouble(6, goods.getGoodsPrice());
		pstmt.setInt(7, goods.getGoodsStock());
		pstmt.addBatch();
	}
	pstmt.executeBatch();
	pstmt.close();
	conn.close();
	System.out.println("添加完成！");
	}

	public static void main(String[] args) throws Exception {
		test();
	}
}
