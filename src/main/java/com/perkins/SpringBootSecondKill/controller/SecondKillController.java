package com.perkins.SpringBootSecondKill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.SecondKillOrder;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.exception.GlobalException;
import com.perkins.SpringBootSecondKill.result.CodeMsg;
import com.perkins.SpringBootSecondKill.result.Result;
import com.perkins.SpringBootSecondKill.service.GoodsService;
import com.perkins.SpringBootSecondKill.service.OrderService;
import com.perkins.SpringBootSecondKill.service.SecondKillService;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@RestController
@RequestMapping("/sk")
public class SecondKillController {

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	SecondKillService skService;
	
	@RequestMapping("/do_second_kill")
	public JSONObject doSecondKill(User user,String goodsId) {
		if (user == null) {
			return (JSONObject) JSONObject.toJSON(CodeMsg.SESSION_ERROR);
		}
		
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
	}
}
