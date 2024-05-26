package com.individual.foodmotion.foodproductsservice.rabbitmq.producer;

import com.individual.foodmotion.foodproductsservice.dto.requests.StatusUpdateRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.update.recipe.status.exchange.json.name}")
    private String recipeStatusExchange;
    @Value("${rabbitmq.update.recipe.status.routing.json.key}")
    private String recipeStatusRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendRecipeStatusUpdateMessage(StatusUpdateRequest statusUpdateRequest){
        LOGGER.info(String.format("Recipe Status Update Message, send -> %s", statusUpdateRequest.toString()));
        rabbitTemplate.convertAndSend(recipeStatusExchange, recipeStatusRoutingKey, statusUpdateRequest);
    }
}