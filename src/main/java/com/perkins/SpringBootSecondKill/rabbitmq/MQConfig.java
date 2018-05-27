package com.perkins.SpringBootSecondKill.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
	
	public static final String SK_QUEUE = "sk.queue";
	
	@Bean
	public Queue queue() {
		return new Queue(SK_QUEUE, true);
	}
	

}
