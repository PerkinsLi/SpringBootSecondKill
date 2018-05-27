package com.perkins.SpringBootSecondKill.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.perkins.SpringBootSecondKill.domain.ShopingCart;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.result.CodeMsg;
import com.perkins.SpringBootSecondKill.result.Result;
import com.perkins.SpringBootSecondKill.service.ShopingCartService;
import com.perkins.SpringBootSecondKill.util.PagingUtil;
import com.perkins.SpringBootSecondKill.vo.ShopingCartVo;

@RestController
@RequestMapping("/cart")
public class ShopingCartController {
	
	@Autowired
	ShopingCartService sCartService;

	@PostMapping("/add_cart")
	public JSONObject addCart(String goodsId, String userId, String goodsNumber) {
		ShopingCart sCart = new ShopingCart();
		sCart.setUserId(Long.valueOf(userId));
		sCart.setGoodsId(Long.valueOf(goodsId));
		sCart.setGoodsNumber(Integer.valueOf(goodsNumber));
		sCart.setAddDate(new Date());
		
		long id = sCartService.addCart(sCart);
		Result<Long> result = Result.success(id);
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(result);
		
		return jsonObject;
	}
	
	@PostMapping("/delete")
	public JSONObject delete(String id) {
		sCartService.delete(Long.parseLong(id));
		Result<CodeMsg> result = Result.success(CodeMsg.CART_DELETE_SUCCESS);
		return (JSONObject) JSONObject.toJSON(result);
	}
	
	@PostMapping("/update")
	public JSONObject update(ShopingCart sCart) {
		sCartService.update(sCart);
		Result<CodeMsg> result = Result.success(CodeMsg.CART_DELETE_SUCCESS);
		return (JSONObject) JSONObject.toJSON(result);
	}
	
	@GetMapping("/list")
	public JSONObject list(String currentPage, User user) {
		PagingUtil pUtil = new PagingUtil();
		Map<String, Object> map = new HashMap<String, Object>();
		List<ShopingCartVo> list = sCartService.list(currentPage, String.valueOf(user.getId()), pUtil);
		map.put("list", list);
		return (JSONObject) JSONObject.toJSON(map);
	}
	
	
	
}
