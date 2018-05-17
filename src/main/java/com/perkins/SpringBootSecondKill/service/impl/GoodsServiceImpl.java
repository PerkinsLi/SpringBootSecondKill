package com.perkins.SpringBootSecondKill.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perkins.SpringBootSecondKill.dao.GoodsDao;
import com.perkins.SpringBootSecondKill.domain.Goods;
import com.perkins.SpringBootSecondKill.domain.SecondKillGoods;
import com.perkins.SpringBootSecondKill.service.GoodsService;
import com.perkins.SpringBootSecondKill.util.PagingUtil;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Autowired
	GoodsDao goodsDao;
	
	/**
	 * 获取多个秒杀商品信息
	 */
	@Override
	public List<SecondKillGoodsVo> listSecondKillGoods(String searchText, String sort, String pageSize, String currentPage,
			String minPrice, String maxPrice, PagingUtil pUtil) {
		int cpage = 0;
        int offset = 0;
        int pagesize = 0;
        List<SecondKillGoodsVo> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> countMap = new HashMap<>();
        countMap.put("searchText", searchText);
        countMap.put("minPrice", minPrice);
        countMap.put("maxPrice", maxPrice);
        
		pUtil.setTotalCount(goodsDao.skGoodsCount(countMap));
		 if (StringUtils.isEmpty(sort)) {
	            sort = "ASC";
	            pUtil.setSort(sort);
	        } else {
	        	pUtil.setSort(sort);
	        }


	      if (StringUtils.isEmpty(pageSize)) {
	            pagesize = pUtil.getPageSize();
	        } else {
	            int size = Integer.parseInt(pageSize);
	            pUtil.setPageSize(size);
	            pagesize = pUtil.getPageSize();
	        }

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
	        
	        offset = pUtil.getOffset();
	        map.put("searchText", searchText);
	        map.put("sort", sort);
	        map.put("offset", offset);
	        map.put("pageSize", pagesize);
	        map.put("minPrice", minPrice);
	        map.put("maxPrice", maxPrice);
	        list = goodsDao.listSecondKillGoods(map);
		return list;
	}

	/**
	 * 根据id获取秒杀商品信息
	 */
	@Override
	public SecondKillGoodsVo getSecondKillGoodsVoById(Long id) {
		return goodsDao.getSecondKillGoodsVoById(id);
	}

	/**
	 * 减少秒杀商品库存
	 */
	@Override
	public int reduceStock(SecondKillGoodsVo goods) {
		SecondKillGoods g = new SecondKillGoods();
		g.setGoodsId(goods.getId());
		int i = goodsDao.reduceStock(g);
		return i;
	}

	/**
	 * 添加商品
	 */
	@Override
	@Transactional
	public void insertGoods(Goods goods) {	
		goodsDao.insertGoods(goods);
	}

	/**
	 * 获取商品列表信息
	 */
	@Override
	public List<SecondKillGoodsVo> listGoods(String searchText, String sort, String pageSize, 
			String currentPage,String minPrice, String maxPrice, PagingUtil pUtil) {
		int cpage = 0;
        int offset = 0;
        int pagesize = 0;
        List<SecondKillGoodsVo> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> countMap = new HashMap<>();
        countMap.put("searchText", searchText);
        countMap.put("minPrice", minPrice);
        countMap.put("maxPrice", maxPrice);
       
		pUtil.setTotalCount(goodsDao.goodsCount(countMap));
		 if (StringUtils.isEmpty(sort)) {
	            sort = "ASC";
	            pUtil.setSort(sort);
	        } else {
	        	pUtil.setSort(sort);
	        }


	      if (StringUtils.isEmpty(pageSize)) {
	            pagesize = pUtil.getPageSize();
	        } else {
	            int size = Integer.parseInt(pageSize);
	            pUtil.setPageSize(size);
	            pagesize = pUtil.getPageSize();
	        }

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
	        
	        offset = pUtil.getOffset();
	        map.put("searchText", searchText);
	        map.put("sort", sort);
	        map.put("offset", offset);
	        map.put("pageSize", pagesize);
	        map.put("minPrice", minPrice);
	        map.put("maxPrice", maxPrice);
	        list = goodsDao.listGoods(map);
		
		return list;
	}


	/**
	 * 根据id获取商品
	 */
	@Override
	public SecondKillGoodsVo getGoodsById(Long id) {
		return goodsDao.getGoodsById(id);
	}
	
	/**
	 * 根据id获取秒杀商品
	 */

	@Override
	public SecondKillGoodsVo getSecondKillGoodsById(Long id) {
		return goodsDao.getSecondKillGoodsVoById(id);
	}
	

}
