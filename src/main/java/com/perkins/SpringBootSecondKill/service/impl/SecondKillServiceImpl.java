package com.perkins.SpringBootSecondKill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.redis.RedisService;
import com.perkins.SpringBootSecondKill.redis.SecondKillKey;
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
	
	@Autowired
	RedisService redisService;
	
	@Override
	@Transactional
	public String secondKill(User user,SecondKillGoodsVo goods,String goodsId) {
		
		boolean isSuccess = goodsService.reduceStock(goods);
		if (isSuccess) {
			return orderService.createSecondKillOrder(user, goods);
		} else {
			setGoodsOver(goods.getId());
			return null;
		}
	}

	/**
	 * 查询秒杀状态 成功返回订单号orderNumber, 失败返回-1， 排队返回0
	 */
	@Override
	public String getSKResult(Long userId, String goodsId) {
		
		OrderInfo oInfo = orderService.getSecondOrderByUserIdGoodsIdFromRedis(userId, Long.valueOf(goodsId));
		if (oInfo != null) {
			return oInfo.getOrderNumber();
		}else {
			boolean isOver = getGoodsOver(Long.valueOf(goodsId));
			if(isOver) {
				return "" + -1;
			} else {
				return "" + 0;
			}
		}
	}

	/**
	 * 查询某个商品是否卖完
	 * @param goodsId
	 * @return
	 */
	private boolean getGoodsOver(Long goodsId) {
		return redisService.exists(SecondKillKey.goodsOver, ""+goodsId);
	}
	
	/**
	 * 当一个秒杀商品卖完了设置数据
	 * @param goodsId
	 */
	private void setGoodsOver(Long goodsId) {
		redisService.set(SecondKillKey.goodsOver, ""+goodsId, true);
	}

}
