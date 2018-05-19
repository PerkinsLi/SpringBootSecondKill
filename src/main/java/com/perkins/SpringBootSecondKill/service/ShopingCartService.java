package com.perkins.SpringBootSecondKill.service;

import java.util.List;
import java.util.Map;

import com.perkins.SpringBootSecondKill.domain.ShopingCart;
import com.perkins.SpringBootSecondKill.util.PagingUtil;
import com.perkins.SpringBootSecondKill.vo.ShopingCartVo;

public interface ShopingCartService {

	long addCart(ShopingCart sCart);
	
	void delete(long id);
	
	void deleteByUserIdGoodsId(Map<String, Object> map);
	
	void update(ShopingCart sCart);
	
	List<ShopingCartVo> list(String currentPage, String userId, PagingUtil pUtil);
	
	int count(long userId);
	
}
