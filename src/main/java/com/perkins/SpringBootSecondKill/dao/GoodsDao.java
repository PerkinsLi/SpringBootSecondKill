package com.perkins.SpringBootSecondKill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
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
	 * 获取所有的秒杀商品信息
	 * @return
	 */
	@Select("select skg.second_kill_goods_price, skg.stock_count, skg.start_date, skg.end_date, g.* "
			+ "from second_kill_goods skg left join goods g on skg.goods_id = g.id")
	public List<SecondKillGoodsVo> allListGoods();

	/**
	 * 获取商品
	 * param:searchText, sort, offset, pageSize
	 * @return
	 */
	@Select("<script>"
			+"<![CDATA["
			+ "select * from goods where 1=1"
			+"]]>"
			+"<if test=\"minPrice != null and minPrice != ''\">"
			+"<![CDATA["
			+" AND goods_price >= #{minPrice}"
			+"]]>"
			+ "</if>"
			+"<if test=\"maxPrice != null and maxPrice != ''\">"
			+"<![CDATA["
			+" AND goods_price <= #{maxPrice}"
			+"]]>"
			+ "</if>"
			+"<if test=\"searchText != null and searchText != ''\">"
			+"<![CDATA["
			+" AND goods_name  LIKE '%${searchText}%' OR goods_title LIKE '%${searchText}%'"
			+"]]>"
			+ "</if>"
			+"<![CDATA["
			+"order by id ${sort} LIMIT ${offset}, ${pageSize}"
			+"]]>"
			+ "</script>")
	public List<SecondKillGoodsVo> listGoods(Map<String, Object> map);
	
	/**
	 * 获取秒杀goods
	 * @return
	 */
	@Select("<script>"
			+"<![CDATA["
			+ "select skg.second_kill_goods_price, skg.stock_count, skg.start_date, skg.end_date, g.* "
			+ "from second_kill_goods skg left join goods g on skg.goods_id = g.id where 1=1"
			+"]]>"
			+"<if test=\"minPrice != null and minPrice != ''\">"
			+"<![CDATA["
			+" AND skg.second_kill_goods_price >= #{minPrice}"
			+"]]>"
			+ "</if>"
			+"<if test=\"maxPrice != null and maxPrice != ''\">"
			+"<![CDATA["
			+" AND skg.second_kill_goods_price <= #{maxPrice}"
			+"]]>"
			+ "</if>"
			+"<if test=\"searchText != null and searchText != ''\">"
			+"<![CDATA["
			+" AND g.goods_name  LIKE '%${searchText}%' OR goods_title LIKE '%${searchText}%'"
			+"]]>"
			+ "</if>"
			+"<![CDATA["
			+"order by g.id ${sort} LIMIT ${offset}, ${pageSize}"
			+"]]>"
			+ "</script>")
	public List<SecondKillGoodsVo> listSecondKillGoods(Map<String, Object> map);
	
	/**
	 * 根据id获取秒杀商品
	 * @param id
	 * @return
	 */
	@Select("select skg.second_kill_goods_price, skg.stock_count, skg.start_date, skg.end_date, g.* from second_kill_goods skg left join goods g on skg.goods_id = g.id where g.id = #{id}")
	public SecondKillGoodsVo getSecondKillGoodsVoById(@Param("id") Long id);
	
	@Select("select * from goods where id = #{id}")
	public SecondKillGoodsVo getGoodsById(@Param("id") Long id);
	
	/**
	 * 减少秒杀商品库存
	 * @param g
	 * @return
	 */
	@Update("update second_kill_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
	public int reduceStock(SecondKillGoods g);
	
	/**
	 * 添加商品
	 * @param goods
	 * @return
	 */
	@Insert("INSERT INTO goods (goods_name, goods_title, goods_img, goods_detail, goods_price, goods_stock) VALUES (#{goodsName}, #{goodsName},#{goodsTitle}, #{goodsImg}, #{goodsDetail}, #{goodsPrice}, #{goodsStock})")
	public int insertGoods(Goods goods);
	
	/**
	 * 获取所有的商品数量
	 * @return
	 */
	@Select("<script>"
			+"<![CDATA["
			+ "select count(id) as count from goods where 1=1"
			+"]]>"
			+"<if test=\"minPrice != null and minPrice != ''\">"
			+"<![CDATA["
			+" AND goods_price >= #{minPrice}"
			+"]]>"
			+ "</if>"
			+"<if test=\"maxPrice != null and maxPrice != ''\">"
			+"<![CDATA["
			+" AND goods_price <= #{maxPrice}"
			+"]]>"
			+ "</if>"
			+"<if test=\"searchText != null and searchText != ''\">"
			+"<![CDATA["
			+" AND goods_name  LIKE '%${searchText}%' OR goods_title LIKE '%${searchText}%'"
			+"]]>"
			+ "</if>"
			+ "</script>")
	public int goodsCount(Map<String, Object> map);
	
	/**
	 * 获取所有秒杀商品数
	 * @return
	 */
	@Select("<script>"
			+"<![CDATA["
			+ "select count(skg.id) as count "
			+ "from second_kill_goods skg left join goods g on skg.goods_id = g.id where 1=1"
			+"]]>"
			+"<if test=\"minPrice != null and minPrice != ''\">"
			+"<![CDATA["
			+" AND skg.second_kill_goods_price >= #{minPrice}"
			+"]]>"
			+ "</if>"
			+"<if test=\"maxPrice != null and maxPrice != ''\">"
			+"<![CDATA["
			+" AND skg.second_kill_goods_price <= #{maxPrice}"
			+"]]>"
			+ "</if>"
			+"<if test=\"searchText != null and searchText != ''\">"
			+"<![CDATA["
			+" AND g.goods_name  LIKE '%${searchText}%' OR goods_title LIKE '%${searchText}%'"
			+"]]>"
			+ "</if>"
			+ "</script>")
	public int skGoodsCount(Map<String, Object> map);


	
	
}
