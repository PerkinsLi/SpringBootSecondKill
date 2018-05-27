package com.perkins.SpringBootSecondKill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.rabbitmq.MQSender;
import com.perkins.SpringBootSecondKill.rabbitmq.SKMessage;
import com.perkins.SpringBootSecondKill.redis.GoodsKey;
import com.perkins.SpringBootSecondKill.redis.RedisService;
import com.perkins.SpringBootSecondKill.result.CodeMsg;
import com.perkins.SpringBootSecondKill.result.Result;
import com.perkins.SpringBootSecondKill.service.GoodsService;
import com.perkins.SpringBootSecondKill.service.OrderService;
import com.perkins.SpringBootSecondKill.service.SecondKillService;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@RestController
@RequestMapping("/sk")
public class SecondKillController implements InitializingBean{

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	SecondKillService skService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	MQSender sender;
	
	private Map<Long, Boolean> isOverGoods = new HashMap<Long, Boolean>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		List<SecondKillGoodsVo> list = goodsService.allListGoods();
		if(list == null) {
			return;
		}
		
		for (SecondKillGoodsVo goods : list) {
			redisService.set(GoodsKey.secondKillGoodsStock, ""+goods.getId(), goods.getStockCount());
			isOverGoods.put(goods.getId(), false);
		}
	}
	
	@RequestMapping("/do_second_kill")
	public JSONObject doSecondKill(User user,String goodsId) {
		if (user == null) {
			return (JSONObject) JSONObject.toJSON(CodeMsg.SESSION_ERROR);
		}
		
		//通过本地标记，减少redis的访问
		boolean goodsOver = isOverGoods.get(Long.valueOf(goodsId));
		if(goodsOver) {
			return (JSONObject) JSONObject.toJSON(CodeMsg.SECOND_KILL_ERROR);
		}
		
		//预减库存
		long stock = redisService.decr(GoodsKey.secondKillGoodsStock, ""+goodsId);
		
		if(stock < 0) {
			isOverGoods.put(Long.valueOf(goodsId), true);
			return (JSONObject) JSONObject.toJSON(CodeMsg.SECOND_KILL_ERROR);
		}
		
		//判断是否已秒杀过
		OrderInfo sKillOrder = orderService.getSecondOrderByUserIdGoodsIdFromRedis(user.getId(), Long.valueOf(goodsId));
		if(sKillOrder != null) {
			return (JSONObject) JSONObject.toJSON(CodeMsg.REPEAT_SECOND_KILL);
		}
		
		//消息入队
		SKMessage skm = new SKMessage();
		skm.setSkUser(user);
		skm.setGoodsId(Long.valueOf(goodsId));
		sender.sendSKMessage(skm);
		
		return (JSONObject) JSONObject.toJSON(Result.success("排队中"));
		
		/**
		SecondKillGoodsVo goods = goodsService.getSecondKillGoodsVoById(Long.valueOf(goodsId));
		int stock = goods.getStockCount();
		
		if (stock <= 0) {
			return (JSONObject) JSONObject.toJSON(CodeMsg.SECOND_KILL_ERROR);
		}
		
		SecondKillOrder sKillOrder = orderService.getSecondOrderByUserIdGoodsId(user.getId(), Long.valueOf(goodsId));
		
		if (sKillOrder != null) {
			return (JSONObject) JSONObject.toJSON(CodeMsg.REPEAT_SECOND_KILL);
		}
		
		String orderNumber = skService.secondKill(user, goods, goodsId);
		return (JSONObject) JSONObject.toJSON(Result.success(orderNumber));
		**/
		
	}
	
	/**
	 * 查询秒杀状态 成功返回订单号orderNumber, 失败返回-1， 排队返回0
	 * @param user
	 * @param goodsId
	 * @return
	 */
	@GetMapping("/result")
	public JSONObject skResult(User user,String goodsId) {
		if(user == null) {
			return (JSONObject) JSONObject.toJSON(Result.error(CodeMsg.SESSION_ERROR));
		}
		
		String orderNumber = skService.getSKResult(user.getId(), goodsId);
		return (JSONObject) JSONObject.toJSON(Result.success(orderNumber));
	}

	
}
