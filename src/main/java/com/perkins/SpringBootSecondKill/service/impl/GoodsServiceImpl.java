package com.perkins.SpringBootSecondKill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perkins.SpringBootSecondKill.dao.GoodsDao;
import com.perkins.SpringBootSecondKill.domain.Goods;
import com.perkins.SpringBootSecondKill.domain.SecondKillGoods;
import com.perkins.SpringBootSecondKill.service.GoodsService;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Autowired
	GoodsDao goodsDao;
	
	@Override
	public List<SecondKillGoodsVo> listSecondKillGoods() {
		return goodsDao.listSecondKillGoods();
	}

	@Override
	public SecondKillGoodsVo getSecondKillGoodsVoById(Long id) {
		return goodsDao.getSecondKillGoodsVoById(id);
	}

	@Override
	public int reduceStock(SecondKillGoodsVo goods) {
		SecondKillGoods g = new SecondKillGoods();
		g.setGoodsId(goods.getId());
		int i = goodsDao.reduceStock(g);
		return i;
	}

}
