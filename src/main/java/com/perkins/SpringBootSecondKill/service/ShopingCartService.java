package com.perkins.SpringBootSecondKill.service;

import java.util.List;
import java.util.Map;

import com.perkins.SpringBootSecondKill.domain.ShopingCart;
import com.perkins.SpringBootSecondKill.util.PagingUtil;

public interface ShopingCartService {

	long addCart(ShopingCart sCart);
	
	void delete(long id);
	
	void update(ShopingCart sCart);
	
	List<ShopingCart> list(String currentPage, String userId, PagingUtil pUtil);
	
	int count(long userId);
	
}
