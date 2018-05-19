package com.perkins.SpringBootSecondKill.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perkins.SpringBootSecondKill.domain.Goods;
import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.service.GoodsService;
import com.perkins.SpringBootSecondKill.service.OrderService;
import com.perkins.SpringBootSecondKill.service.SecondKillService;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@Service
public class SecondKillServiceImpl implements SecondKillService{

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Override
	@Transactional
	public OrderInfo secondKill(User user, SecondKillGoodsVo goods, String goodsNumber, String addressId) {
		
//		String i = goodsService.reduceStock(goods) + "";
//		if (!StringUtils.isEmpty(i)) {
//			return orderService.createOrder(user, goods, goodsNumber, addressId);
//		}
		return null;
	}

}
