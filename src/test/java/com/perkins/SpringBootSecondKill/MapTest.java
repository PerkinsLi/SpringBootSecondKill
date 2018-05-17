package com.perkins.SpringBootSecondKill;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.perkins.SpringBootSecondKill.domain.User;

public class MapTest {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		String string = "测试字符";
		
		map.put("user", user);
		user.setId(1L);
		user.setLoginCount(10);
		user.setName("ceshi");
		user.setPassword("134521");
		
		map.put("str", string);
		
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		System.out.println(jsonObject);
	}
}
