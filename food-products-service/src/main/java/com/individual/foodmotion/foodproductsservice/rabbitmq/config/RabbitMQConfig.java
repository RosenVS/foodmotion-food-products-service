package com.individual.foodmotion.foodproductsservice.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.update.recipe.status.exchange.json.name}")
    private String recipeStatusExchange;

    @Value("${rabbitmq.update.recipe.status.routing.json.key}")
    private String recipeStatusRoutingKey;

    @Value("${rabbitmq.update.recipe.status.queue.json.name}")
    private String recipeStatusQueueKey;

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue recipeStatusQueue() {
        return new Queue(recipeStatusQueueKey, true);
    }

    @Bean
    public TopicExchange recipeStatusExchange() {
        return new TopicExchange(recipeStatusExchange);
    }

    @Bean
    public Binding bindingRecipeStatusUpdate(Queue recipeStatusQueue, TopicExchange recipeStatusExchange) {
        return BindingBuilder.bind(recipeStatusQueue).to(recipeStatusExchange).with(recipeStatusRoutingKey);
    }
}
