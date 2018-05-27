package com.perkins.SpringBootSecondKill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

	@Autowired
	JedisPool jedisPool;
	
	/**
	 * 获取对象
	 * @param keyPrefix
	 * @param key
	 * @param clss
	 * @return
	 */
	public <T> T get(KeyPrefix keyPrefix, String key, Class<T> clss) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 生成真正的key值
			String reallyKey = keyPrefix.getPrefix() + key;
			String str = jedis.get(reallyKey);
			T t = stringToBean(str, clss);
			return t;
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 * 设置对象
	 * @param keyPrefix
	 * @param key
	 * @param value
	 * @return
	 */
	public <T> Boolean set(KeyPrefix keyPrefix, String key, T value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String str = BeanToString(value);
			
			if (str == null || str.length() <= 0) {
				return false;
			}
			
			String reallyKey = keyPrefix.getPrefix() + key;
			int seconds = keyPrefix.expireSeconds();
			
			if (seconds <= 0) {
				jedis.set(reallyKey, str);
			} else {
				jedis.setex(reallyKey, seconds, str);
			}
			
			return true;
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 * 删除对象
	 * @param prefix
	 * @param key
	 * @return
	 */
	public boolean delete(KeyPrefix prefix, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			String realKey  = prefix.getPrefix() + key;
			long ret =  jedis.del(realKey);
			return ret > 0;
		 }finally {
			  returnToPool(jedis);
		 }
	}
	
	/**
	 * 判断key是否存在
	 * */
	public <T> boolean exists(KeyPrefix prefix, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			 String realKey  = prefix.getPrefix() + key;
			return  jedis.exists(realKey);
		 }finally {
			  returnToPool(jedis);
		 }
	}
	
	/**
	 * 增加值
	 * */
	public <T> Long incr(KeyPrefix prefix, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			 String realKey  = prefix.getPrefix() + key;
			return  jedis.incr(realKey);
		 }finally {
			  returnToPool(jedis);
		 }
	}
	
	/**
	 * 减少值
	 * */
	public <T> Long decr(KeyPrefix prefix, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			 String realKey  = prefix.getPrefix() + key;
			return  jedis.decr(realKey);
		 }finally {
			  returnToPool(jedis);
		 }
	}
	
	
	/**
	 * 将bean类型转换为String类型
	 * @param value
	 * @return
	 */
	public static <T> String BeanToString(T value) {
		if (value == null) {
			return null;
		}
		
		Class<?> class1 = value.getClass();
		
		if (class1 == int.class || class1 == Integer.class) {
			return ""+value;
		}else if (class1 == String.class) {
			return (String)value;
		}else if (class1 == Long.class || class1 == long.class) {
			return ""+value;
		} else {
			return JSON.toJSONString(value);
		}
		
	}

	/**
	 * 将String类型转换为Bean类型
	 * @param str
	 * @param clas
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T stringToBean(String str, Class<T> clas) {
		if (str == null || str.length() <= 0 || clas == null) {
			return null;
		}
		
		if (clas == int.class || clas == Integer.class) {
			return (T) Integer.valueOf(str);
		}else if (clas == String.class) {
			return (T) str;
		}else if (clas == Long.class || clas == long.class) {
			return (T) Long.valueOf(str);
		} else {
			return JSON.toJavaObject(JSON.parseObject(str), clas);
		}
		
	}

	private void returnToPool(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
		
	}

}
