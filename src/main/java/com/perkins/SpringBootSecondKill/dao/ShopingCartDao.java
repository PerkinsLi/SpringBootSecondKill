package com.perkins.SpringBootSecondKill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.perkins.SpringBootSecondKill.domain.ShopingCart;

@Mapper
public interface ShopingCartDao {

	/**
	 * 加入购物车
	 * @param sCart
	 * @return
	 */
	@Insert("insert into shoping_cart(user_id, goods_id, goods_number, add_date) values (#{userId}, #{goodsId}, #{goodsNumber}, #{addDate})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	public int addCart(ShopingCart sCart);
	
	/**
	 * 将商品从购物车删除
	 * @param id
	 */
	@Delete("delete from shoping_cart where id = #{id}")
	public void delete(@Param("id") long id);
	
	/**
	 * 获取购物车商品
	 * @param map
	 * @return
	 */
	@Select("select * from shoping_cart where user_id = #{userId} order by add_date ${sort} LIMIT ${offset}, ${pageSize}")
	public List<ShopingCart> list(Map<String, Object> map);
	
	/**
	 * 获取购物车商品总数
	 * @return
	 */
	@Select("select count(id) as count from shoping_cart where user_id = #{userId}")
	public int count(@Param("userId") long userId);
	
	/**
	 * 更新购物车商品购买数量
	 * @param sCart
	 */
	@Update("update shoping_cart set goods_number = #{goodsNumber} where id = #{id}")
	public void update(ShopingCart sCart);
	
	/**
	 * 查询客户购物车中客户商品
	 * @param sCart
	 * @return
	 */
	@Select("select * from shoping_cart where user_id = #{userId} and goods_id = #{goodsId}")
	public ShopingCart getsCart(ShopingCart sCart);
	
	
}
