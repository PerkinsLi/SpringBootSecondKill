package com.perkins.SpringBootSecondKill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perkins.SpringBootSecondKill.dao.OrderDao;
import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.SecondKillOrder;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.exception.GlobalException;
import com.perkins.SpringBootSecondKill.redis.OrderKey;
import com.perkins.SpringBootSecondKill.redis.RedisService;
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
	
	@Autowired
	RedisService redisService;
	
	@Override
	public SecondKillOrder getSecondOrderByUserIdGoodsId(Long userId, Long goodsId) {
		
		return orderDao.getSecondKillOrderByUserIdGoodsId(userId, goodsId);
	}
	
	/**
	 * 从redis获取秒杀订单
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	public OrderInfo getSecondOrderByUserIdGoodsIdFromRedis(Long userId, Long goodsId) {
		
		return redisService.get(OrderKey.getSKOrderByUidGid, ""+userId+"_"+goodsId, OrderInfo.class);
	}

	/**
	 * 创建订单
	 */
	@Override
	@Transactional
	public String createOrder(User user, String goodsInformation) {

		String[] goodsAndNumber = goodsInformation.split(",");
		String orderNumber = String.valueOf(new Date().getTime());
		
		for (String str : goodsAndNumber) {
			Map<String, Object> map = new HashMap<String, Object>();
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
	
	/**
	 * 创建秒杀订单
	 */
	@Transactional
	public String createSecondKillOrder(User user, SecondKillGoodsVo goods) {
		int goodsNumber = 1;	//秒杀商品数量
		String orderNumber = String.valueOf(new Date().getTime());
		OrderInfo orderInfo = new OrderInfo();

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
		
		SecondKillOrder sKillOrder = new SecondKillOrder();
		sKillOrder.setGoodsId(goods.getId());
		sKillOrder.setOrderId(orderInfo.getId());
		sKillOrder.setUserId(user.getId());
		orderDao.insertSecondKillOrder(sKillOrder);
		
		//存入redis，判断是否重复秒杀时使用，减少数据库访问
		redisService.set(OrderKey.getSKOrderByUidGid, ""+user.getId()+"_"+goods.getId(), orderInfo);
		
		return orderNumber;
		}

	/**
	 * 根据订单号获取订单
	 */
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
