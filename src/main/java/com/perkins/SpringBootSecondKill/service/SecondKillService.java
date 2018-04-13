package com.perkins.SpringBootSecondKill.service;

import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

public interface SecondKillService {

	OrderInfo secondKill(User user, SecondKillGoodsVo goods);

}
