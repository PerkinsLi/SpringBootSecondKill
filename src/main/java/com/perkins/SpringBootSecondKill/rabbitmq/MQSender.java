package com.perkins.SpringBootSecondKill.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perkins.SpringBootSecondKill.redis.RedisService;

@Service
public class MQSender {
	@Autowired
	private AmqpTemplate amqpTemplate ;
	
	private static Logger log = LoggerFactory.getLogger(MQSender.class);

	public void sendSKMessage(SKMessage skm) {
		String msg = RedisService.BeanToString(skm);
		log.info(msg);
		amqpTemplate.convertAndSend(MQConfig.SK_QUEUE, msg);
	}
}
