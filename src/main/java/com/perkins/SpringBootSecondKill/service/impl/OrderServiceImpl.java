package com.perkins.SpringBootSecondKill.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perkins.SpringBootSecondKill.dao.OrderDao;
import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.SecondKillOrder;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.service.OrderService;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderDao orderDao;
	
	@Override
	public SecondKillOrder getSecondOrderByUserIdGoodsId(Long userId, Long goodsId) {
		
		return orderDao.getSecondKillOrderByUserIdGoodsId(userId, goodsId);
	}

	@Override
	@Transactional
	public OrderInfo createOrder(User user, SecondKillGoodsVo goods, String goodsNumber, String addressId) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setAddrId(Long.parseLong(addressId));	
		orderInfo.setCreateDate(new Date());
		orderInfo.setGoodsCount(Integer.parseInt(goodsNumber)); 
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getGoodsPrice());
		orderInfo.setStatus(0);
		orderInfo.setUserId(user.getId());
		
		orderDao.insert(orderInfo);

		return orderInfo;
	}

}
