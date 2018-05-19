package com.perkins.SpringBootSecondKill.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perkins.SpringBootSecondKill.dao.OrderDao;
import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.SecondKillOrder;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.exception.GlobalException;
import com.perkins.SpringBootSecondKill.result.CodeMsg;
import com.perkins.SpringBootSecondKill.service.GoodsService;
import com.perkins.SpringBootSecondKill.service.OrderService;
import com.perkins.SpringBootSecondKill.service.ShopingCartService;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderDao orderDao;
	@Autowired
	GoodsService goodsService;
	@Autowired
	ShopingCartService sCartService;
	
	@Override
	public SecondKillOrder getSecondOrderByUserIdGoodsId(Long userId, Long goodsId) {
		
		return orderDao.getSecondKillOrderByUserIdGoodsId(userId, goodsId);
	}

	@Override
	@Transactional
	public String createOrder(User user, String goodsInformation) {
		String[] goodsAndNumber = goodsInformation.split(",");
		String orderNumber = String.valueOf(new Date().getTime());
		
		for (String str : goodsAndNumber) {
			Map<String, Object> map = new HashMap<>();
			String[] ids = str.split(":");
			long goodsId = Long.valueOf(ids[0]);
			int goodsNumber = Integer.valueOf(ids[1]);
			OrderInfo orderInfo = new OrderInfo();
			
			SecondKillGoodsVo goods = goodsService.getGoodsById(goodsId);
			
			map.put("goodsId", goods.getId());
			map.put("userId", user.getId());

			orderInfo.setId(orderInfo.getId());
			orderInfo.setAddrId(2L);	
			orderInfo.setCreateDate(new Date());
			orderInfo.setGoodsCount(goodsNumber); 
			orderInfo.setGoodsId(goods.getId());
			orderInfo.setGoodsName(goods.getGoodsName());
			orderInfo.setGoodsPrice(goods.getGoodsPrice());
			orderInfo.setStatus(0);
			orderInfo.setUserId(user.getId());
			orderInfo.setOrderNumber(orderNumber);
			orderInfo.setGoodsImage(goods.getGoodsImg());
			
			orderDao.insert(orderInfo);
			sCartService.deleteByUserIdGoodsId(map);
			}
		
		return orderNumber;
	}

	@Override
	public List<OrderInfo> orderListByOrderNumber(String orderNumber) {
		try {
			List<OrderInfo> list = orderDao.orderListByOrderNumber(orderNumber);
			return list;
		} catch (Exception e) {
			throw new GlobalException(CodeMsg.ORDER_GET_LIST_ERROR);
		}
	}

}
