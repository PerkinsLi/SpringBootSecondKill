package com.perkins.SpringBootSecondKill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.perkins.SpringBootSecondKill.domain.Goods;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.redis.RedisService;
import com.perkins.SpringBootSecondKill.result.Result;
import com.perkins.SpringBootSecondKill.service.GoodsService;
import com.perkins.SpringBootSecondKill.service.impl.UserServiceImpl;
import com.perkins.SpringBootSecondKill.util.PagingUtil;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	RedisService redisService;
	@Autowired
	GoodsService goodService;
	
	@RequestMapping("/to_list")
	public JSONObject toskGoodsList(String searchText,String sort,String pageSize,String currentPage,
			String minPrice, String maxPrice, String goods,  User user) {
		
		PagingUtil pUtil = new PagingUtil();
		Map<String, Object> map = new HashMap<>();
		
		if ("skgoods".equals(goods)) {
			List<SecondKillGoodsVo> goodsList = goodService.listSecondKillGoods(searchText, sort, pageSize, currentPage, minPrice, maxPrice, pUtil);
			map.put("goodsList", goodsList);
		} else {
			List<SecondKillGoodsVo> goodsList = goodService.listGoods(searchText, sort, pageSize, currentPage, minPrice, maxPrice, pUtil);
			map.put("goodsList", goodsList);
		}
		
		map.put("user", user);
		map.put("page", pUtil);
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		
		return jsonObject;
	}
	
	
	@GetMapping("/to_detail")
	public JSONObject toDetail(User user,String id, String goods) {
		
		int secondkillStatus = 0;	//秒杀状态 :0 未开始, 1 正在进行中, 2 秒杀已结束
		int remainSeconds = 0;	//离秒杀开始还剩多少秒
		Map<String, Object> map = new HashMap<>();
		
		if ("skgoods".equals(goods)) {
			SecondKillGoodsVo g = goodService.getSecondKillGoodsVoById(Long.valueOf(id));
			Long startDate = g.getStartDate().getTime();
			Long endDate = g.getEndDate().getTime();
			Long currentDate = System.currentTimeMillis();
			
			
			if (currentDate < startDate) {// 秒杀未开始
				secondkillStatus = 0;
				remainSeconds = (int)(startDate - currentDate)/1000;
			} else if (currentDate > endDate) {//秒杀已结束
				secondkillStatus = 2;
				remainSeconds = -1;
			}else {//秒杀正在进行中
				secondkillStatus = 1;
				remainSeconds = 0;
			}
			
			map.put("secondkillStatus", secondkillStatus);
			map.put("remainSeconds", remainSeconds);
			map.put("goods", g);
			map.put("user", user);
			
		} else {
			SecondKillGoodsVo g = goodService.getGoodsById(Long.valueOf(id));
			map.put("secondkillStatus", secondkillStatus);
			map.put("remainSeconds", remainSeconds);
			map.put("goods", g);
			map.put("user", user);
		}
		
		return (JSONObject) JSONObject.toJSON(map);
	}
	
	@PostMapping("/insert")
	public JSONObject insert(Goods goods) {
		goodService.insertGoods(goods);
		Result<String> result = Result.success("添加商品成功");
		JSONObject jsonResult = (JSONObject) JSONObject.toJSON(result);
		return jsonResult;
	}
}
