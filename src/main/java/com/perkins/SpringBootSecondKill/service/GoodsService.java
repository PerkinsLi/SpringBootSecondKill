package com.perkins.SpringBootSecondKill.service;

import java.util.List;
import com.perkins.SpringBootSecondKill.domain.Goods;
import com.perkins.SpringBootSecondKill.util.PagingUtil;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

public interface GoodsService {
	
	List<SecondKillGoodsVo> allListGoods();

	List<SecondKillGoodsVo> listSecondKillGoods(String searchText, String sort, String pageSize, String currentPage,
			String minPrice, String maxPrice, PagingUtil pUtil);

	SecondKillGoodsVo getSecondKillGoodsVoById(Long id);

	boolean reduceStock(SecondKillGoodsVo goods);
	
	void insertGoods(Goods goods);
	
	List<SecondKillGoodsVo> listGoods(String searchText, String sort, String pageSize, String currentPage,
			String minPrice, String maxPrice, PagingUtil pUtil);
	
	SecondKillGoodsVo getGoodsById(Long id);
	
	SecondKillGoodsVo getSecondKillGoodsById(Long id);
}
