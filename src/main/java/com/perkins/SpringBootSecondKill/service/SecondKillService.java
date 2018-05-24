package com.perkins.SpringBootSecondKill.service;

import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

public interface SecondKillService {

	String secondKill(User user, SecondKillGoodsVo goods, String goodsId);
	

}
