package com.example.rabbitmqcilent.publisher;

import com.example.rabbitmqcilent.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {


    @Value("${rabbitmq.routing.json.key}")
    private String routinJsonKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    private RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER=  LoggerFactory.getLogger(RabbitMQJsonProducer.class);


    private RabbitMQJsonProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
    }


    public void sendJsonMessage(User user){
        LOGGER.info(String.format("Json message sent ->%s",user.toString()));
        rabbitTemplate.convertAndSend(exchange,routinJsonKey,user);

    }
}
