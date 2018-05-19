package com.perkins.SpringBootSecondKill.service;

import java.util.List;

import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.SecondKillOrder;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

public interface OrderService {

	SecondKillOrder getSecondOrderByUserIdGoodsId(Long userId, Long goodsId);

	String createOrder(User user, String goodsInformation);
	
	List<OrderInfo> orderListByOrderNumber(String orderNumber);

}
