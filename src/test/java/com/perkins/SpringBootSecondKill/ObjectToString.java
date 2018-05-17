package com.perkins.SpringBootSecondKill;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.perkins.SpringBootSecondKill.result.Result;

public class ObjectToString {

	public static void main(String[] args) {
		Result<String> result = Result.success("chenggong");
		JSONObject json = (JSONObject) JSONObject.toJSON(result);
		System.out.println(json);
	}
}
