package com.perkins.SpringBootSecondKill.service;

import java.util.List;

import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

public interface GoodsService {

	List<SecondKillGoodsVo> listSecondKillGoods();

	SecondKillGoodsVo getSecondKillGoodsVoById(Long id);

	int reduceStock(SecondKillGoodsVo goods);
}
