package com.perkins.SpringBootSecondKill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.redis.RedisService;
import com.perkins.SpringBootSecondKill.service.GoodsService;
import com.perkins.SpringBootSecondKill.service.impl.UserServiceImpl;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	RedisService redisService;
	@Autowired
	GoodsService goodService;
	
	@RequestMapping("/to_list")
	public String toGoodsList(Model model,User user) {
		
		model.addAttribute("user", user);
		// 查询商品列表
		List<SecondKillGoodsVo> goodsList = goodService.listSecondKillGoods();
		model.addAttribute("goodsList", goodsList);
		return "/goods/goods_list";
	}
	
	@GetMapping("/to_detail/{id}")
	public String toDetail(Model model, User user,
			@PathVariable("id") Long id) {
		model.addAttribute("user", user);
		
		SecondKillGoodsVo goods = goodService.getSecondKillGoodsVoById(id);
		model.addAttribute("goods", goods);
		
		Long startDate = goods.getStartDate().getTime();
		Long endDate = goods.getEndDate().getTime();
		Long currentDate = System.currentTimeMillis();
		
		int secondkillStatus = 0;
		int remainSeconds = 0;
		
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
		
		model.addAttribute("secondkillStatus", secondkillStatus);
		model.addAttribute("remainSeconds", remainSeconds);
		return "/goods/goods_detail";
	}
}
