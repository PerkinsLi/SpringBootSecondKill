package com.perkins.SpringBootSecondKill.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perkins.SpringBootSecondKill.dao.ShopingCartDao;
import com.perkins.SpringBootSecondKill.domain.ShopingCart;
import com.perkins.SpringBootSecondKill.service.ShopingCartService;
import com.perkins.SpringBootSecondKill.util.PagingUtil;
import com.perkins.SpringBootSecondKill.vo.ShopingCartVo;

@Service
public class ShopingCartServiceImpl implements ShopingCartService{

	@Autowired
	ShopingCartDao shopingCartDao;
	
	@Override
	@Transactional
	public long addCart(ShopingCart sCart) {
		if (sCart != null) {
			ShopingCart sCart2 = shopingCartDao.getsCart(sCart);
			if (sCart2 == null) {
				shopingCartDao.addCart(sCart);
				return  sCart.getId();
			} else {
				sCart2.setGoodsNumber(sCart.getGoodsNumber());
				shopingCartDao.update(sCart2);
				return  sCart2.getId();
			}
		}
		return 0;
	}

	@Override
	@Transactional
	public void delete(long id) {
		shopingCartDao.delete(id);	
	}

	@Override
	public List<ShopingCartVo> list(String currentPage, String userId, PagingUtil pUtil) {
		
		int cpage = 0;
		Map<String, Object> map = new HashMap<>();
		
		pUtil.setTotalCount(shopingCartDao.count(Long.parseLong(userId)));
		
		 if (StringUtils.isEmpty(currentPage)) {
	            cpage = pUtil.getCurrentPage();
	        } else {
	            cpage = Integer.parseInt(currentPage);
	        }
	        
	        if (cpage >= pUtil.getPageCount()) {
	        	pUtil.setCurrentPage(pUtil.getPageCount());
	        } else if (cpage <= 1) {
	        	pUtil.setCurrentPage(1);
	        } else {
	        	pUtil.setCurrentPage(cpage);
	        }
	        
	        map.put("userId", userId);
	        map.put("sort", pUtil.getSort());
	        map.put("offset", pUtil.getOffset());
	        map.put("pageSize", pUtil.getPageSize());
	        
		return shopingCartDao.list(map);
	}

	@Override
	public int count(long userId) {
		return shopingCartDao.count(userId);
	}

	@Override
	@Transactional
	public void update(ShopingCart sCart) {
		shopingCartDao.update(sCart);
		
	}

	@Override
	@Transactional
	public void deleteByUserIdGoodsId(Map<String, Object> map) {
		shopingCartDao.deleteByUserIdGoodsId(map);	
	}

}
