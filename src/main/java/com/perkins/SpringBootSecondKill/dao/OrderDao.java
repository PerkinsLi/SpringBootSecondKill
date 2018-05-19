package com.perkins.SpringBootSecondKill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.perkins.SpringBootSecondKill.domain.OrderInfo;
import com.perkins.SpringBootSecondKill.domain.SecondKillOrder;

@Mapper
public interface OrderDao {

	
	/**
	 * 根据订单号查询订单
	 * @param orderNumber
	 * @return
	 */
	@Select("select * from order_info where order_number = #{orderNumber}")
	public List<OrderInfo> orderListByOrderNumber(@Param("orderNumber")String orderNumber);

	/**
	 * 增加订单
	 * @param orderInfo
	 * @return
	 */
	@Insert("insert into order_info(id, user_id, goods_id, addr_id, goods_name, goods_count, goods_price, order_channel, status, create_date, pay_date, order_number, goods_image) "
			+ "values(#{id},#{userId}, #{goodsId}, #{addrId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status}, #{createDate}, #{payDate}, #{orderNumber}, #{goodsImage})")
	@SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
	public long insert(OrderInfo orderInfo);
	
	
	/**
	 * 根据userId, goodsId查询秒杀订单
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	@Select("select * from second_kill_order where user_id = #{userId} and goods_id = #{goodsId}")
	public SecondKillOrder getSecondKillOrderByUserIdGoodsId(@Param("userId")Long userId, @Param("goodsId")Long goodsId);

	/**
	 * 增加秒杀订单
	 * @param sKillOrder
	 * @return
	 */
	@Insert("insert into second_kill_order(user_id, order_id, goods_id) values(#{userId}, #{orderId}, #{goodsId})")
	@SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
	public long insertSecondKillOrder(SecondKillOrder sKillOrder);
	

}
