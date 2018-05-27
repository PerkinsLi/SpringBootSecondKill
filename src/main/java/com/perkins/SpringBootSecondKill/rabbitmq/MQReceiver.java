package com.perkins.SpringBootSecondKill.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.redis.RedisService;
import com.perkins.SpringBootSecondKill.service.GoodsService;
import com.perkins.SpringBootSecondKill.service.SecondKillService;
import com.perkins.SpringBootSecondKill.vo.SecondKillGoodsVo;

@Service
public class MQReceiver {
	
	private static Logger log = LoggerFactory.getLogger(MQReceiver.class);
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	SecondKillService skService;

	@RabbitListener(queues=MQConfig.SK_QUEUE)
	public void receive(String message) {
		log.info("MQreceive::"+message);
		SKMessage skMessage = RedisService.stringToBean(message, SKMessage.class);
		User user = skMessage.getSkUser();
		long goodsId = skMessage.getGoodsId();
		
		SecondKillGoodsVo goods = goodsService.getSecondKillGoodsVoById(Long.valueOf(goodsId));
		int stock = goods.getStockCount();
		
		if (stock <= 0) {
			return ;
		}
		
		skService.secondKill(user, goods, String.valueOf(goodsId));
	}
}
