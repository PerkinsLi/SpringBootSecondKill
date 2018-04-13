package com.perkins.SpringBootSecondKill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.perkins.SpringBootSecondKill.domain.User;

@Mapper
public interface UserDao {

	@Select("Select * from user Where id = #{id}")
	public User getById(@Param("id") long id);
}
