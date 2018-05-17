package com.perkins.SpringBootSecondKill.service;

import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.SecondKillOrder;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

public interface OrderService {

	SecondKillOrder getSecondOrderByUserIdGoodsId(Long userId, Long goodsId);

	OrderInfo createOrder(User user, SecondKillGoodsVo goods, String goodsNumber, String addressId);

}
