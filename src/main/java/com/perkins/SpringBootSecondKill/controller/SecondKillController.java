package com.perkins.SpringBootSecondKill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.SecondKillOrder;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.result.CodeMsg;
import com.perkins.SpringBootSecondKill.service.GoodsService;
import com.perkins.SpringBootSecondKill.service.OrderService;
import com.perkins.SpringBootSecondKill.service.SecondKillService;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@Controller
@RequestMapping("/sk")
public class SecondKillController {

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	SecondKillService skService;
	
	@RequestMapping("/do_second_kill")
	public String doSecondKill(Model model, User user,
			@RequestParam("goodsId") Long goodsId) {
		if (user == null) {
			return "login";
		}
		
		SecondKillGoodsVo goods = goodsService.getSecondKillGoodsVoById(goodsId);
		int stock = goods.getStockCount();
		
		if (stock <= 0) {
			model.addAttribute("errmsg", CodeMsg.SECOND_KILL_ERROR);
			return "goods/second_kill_fail";
		}
		
		SecondKillOrder sKillOrder = orderService.getSecondOrderByUserIdGoodsId(user.getId(), goodsId);
		
		if (sKillOrder != null) {
			model.addAttribute("errmsg", CodeMsg.REPEAT_SECOND_KILL);
			return "goods/second_kill_fail";
		}
		
		//OrderInfo orderInfo = skService.secondKill(user, goods);
//		model.addAttribute("orderInfo", orderInfo);
//		model.addAttribute("goods", goods);
		return "order/order_detail";
	}
}
