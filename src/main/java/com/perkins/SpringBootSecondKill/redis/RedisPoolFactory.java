package com.perkins.SpringBootSecondKill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {

	@Autowired
	RedisConfig redisConfig;
	
	@Bean
	public JedisPool jPoolFactory() {
		JedisPoolConfig jPoolConfig = new JedisPoolConfig();
		jPoolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
		jPoolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
		jPoolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);
		JedisPool jp = new JedisPool(jPoolConfig, redisConfig.getHost(),redisConfig.getPort(), redisConfig.getTimeout()*1000,
								redisConfig.getPassword(), 0);//0是database
		return jp;
		
	}
}
