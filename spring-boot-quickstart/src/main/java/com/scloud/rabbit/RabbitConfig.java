package com.scloud.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Create by andy on 2018/5/22
 * RabbitMQ Queue 配置
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    @Bean
    public Queue andyQueue(){
        return new Queue("andy");
    }

    @Bean
    public Queue objectQueue(){
        return new Queue("object");
    }



}
