package com.perkins.SpringBootSecondKill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.perkins.SpringBootSecondKill.domain.Goods;
import com.perkins.SpringBootSecondKill.domain.SecondKillGoods;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@Mapper
public interface GoodsDao {

	/**
	 * 获取所有的goods
	 * @return
	 */
	@Select("select skg.second_kill_goods_price, skg.stock_count, skg.start_date, skg.end_date, g.* from second_kill_goods skg left join goods g on skg.goods_id = g.id")
	public List<SecondKillGoodsVo> listSecondKillGoods();
	
	@Select("select skg.second_kill_goods_price, skg.stock_count, skg.start_date, skg.end_date, g.* from second_kill_goods skg left join goods g on skg.goods_id = g.id where g.id = #{id}")
	public SecondKillGoodsVo getSecondKillGoodsVoById(@Param("id") Long id);

	@Update("update second_kill_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
	public int reduceStock(SecondKillGoods g);


	
	
}
