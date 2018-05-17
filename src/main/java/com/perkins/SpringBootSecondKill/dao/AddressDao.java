package com.perkins.SpringBootSecondKill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.junit.runners.Parameterized.Parameters;

import com.perkins.SpringBootSecondKill.domain.Address;

@Mapper
public interface AddressDao {
	
	@Insert("insert into address (user_id, consignee_name, mobile, address) values (#{userId}, #{consigneeName}, #{mobile}, #{address})")
	@SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
	public long insert(Address address);
	
	@Update("update address set consignee_name = #{consigneeName}, mobile = #{mobile}, address = #{address} where id = #{id}")
	public void update(Address address);
	
	@Delete("delete from address where id = #{id}")
	public void delete(@Param("id") long id);
	
	@Select("select count(id) as count from address where user_id = #{userId}")
	public int count(@Param("userId") long userId);
	
	@Select("select * from address where id = #{id}")
	public Address getAddressById(@Param("id") long id);
	
	@Select("select * from address where user_id = #{userId}")
	public List<Address> list(@Param("userId") long userId);
}
