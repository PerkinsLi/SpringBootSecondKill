package com.perkins.SpringBootSecondKill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.perkins.SpringBootSecondKill.domain.Address;
import com.perkins.SpringBootSecondKill.domain.Goods;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.service.AddressService;
import com.perkins.SpringBootSecondKill.service.GoodsService;
import com.perkins.SpringBootSecondKill.service.OrderService;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	AddressService addressService;
	
	@RequestMapping("/create_order")
	public JSONObject createOrder(User user, String goodsId, String goodsNumber, String addressId) {
		SecondKillGoodsVo goods = goodsService.getGoodsById(Long.parseLong(goodsId));
		orderService.createOrder(user, goods, goodsNumber, addressId);
		return null;
	}
	
	@GetMapping("/get_order_information")
	public JSONObject getOrderInformation(User user, String goodsId) {
		Map<String, Object> map = new HashMap<>();
		SecondKillGoodsVo goods = goodsService.getGoodsById(Long.parseLong(goodsId));
		List<Address> addresses = addressService.list(user.getId());
		
		map.put("user", user);
		map.put("goods", goods);
		map.put("addresses", addresses);
		return (JSONObject) JSONObject.toJSON(map);
	}
}
