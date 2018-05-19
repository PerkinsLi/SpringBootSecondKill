package com.perkins.SpringBootSecondKill.result;

public class CodeMsg {

	// 通用错误码5001
	public static final CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static final CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端错误");
	public static final CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
	
	//登陆模块5002
	public static final CodeMsg SESSION_ERROR = new CodeMsg(500200, "Session不存在或Session已失效");
	public static final CodeMsg MOBILE_EMPTY = new CodeMsg(500201, "手机号不能为空");
	public static final CodeMsg PASSWORD_EMPTY = new CodeMsg(500202, "登陆密码不能为空");
	public static final CodeMsg MOBILE_ERROR = new CodeMsg(500203, "手机号错误");
	public static final CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500204, "手机号不存在");
	public static final CodeMsg PASSWORD_ERROR = new CodeMsg(500205, "密码错误");
	
	//秒杀模块5003
	public static final CodeMsg SECOND_KILL_ERROR = new CodeMsg(500301, "秒杀失败");
	public static final CodeMsg REPEAT_SECOND_KILL = new CodeMsg(500302, "不能重复秒杀");
	
	// 地址模块5004
	public static final CodeMsg ADDRESS_INSERT_ERROR = new CodeMsg(500400, "新增地址成功");
	public static final CodeMsg ADDRESS_INSERT_SUCCESS = new CodeMsg(500401, "新增地址失败");
	public static final CodeMsg ADDRESS_UPDATE_ERROR = new CodeMsg(500402, "修改地址失败");
	public static final CodeMsg ADDRESS_UPDATE_SUCCESS = new CodeMsg(500403, "修改地址成功");
	public static final CodeMsg ADDRESS_DELETE_ERROR = new CodeMsg(500404, "删除地址失败");
	public static final CodeMsg ADDRESS_DELETE_SUCCESS = new CodeMsg(500405, "删除地址成功");
	
	//购物车模块
	public static final CodeMsg CART_INSERT_ERROR = new CodeMsg(500400, "添加购物车成功");
	public static final CodeMsg CART_INSERT_SUCCESS = new CodeMsg(500401, "添加购物车失败");
	public static final CodeMsg CART_UPDATE_ERROR = new CodeMsg(500402, "修改购物车失败");
	public static final CodeMsg CART_UPDATE_SUCCESS = new CodeMsg(500403, "修改购物车成功");
	public static final CodeMsg CART_DELETE_ERROR = new CodeMsg(500404, "删除失败");
	public static final CodeMsg CART_DELETE_SUCCESS = new CodeMsg(500405, "删除成功");
	
	//订单模块
	public static final CodeMsg ORDER_GET_LIST_ERROR = new CodeMsg(500405, "根据ids获取订单失败");
	
	private int code;
	private String msg; 
	
	public CodeMsg fillArgs(Object... args) {
		String message = String.format(this.msg, args);
		return new CodeMsg(this.code, message);
	}
	
	public CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	
}
