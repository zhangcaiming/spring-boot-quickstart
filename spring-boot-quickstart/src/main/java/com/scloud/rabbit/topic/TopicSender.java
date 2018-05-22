package com.scloud.rabbit.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create by andy on 2018/5/23
 */
@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String message = "hi, i am message all";
        System.out.println("Sender :" + message);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.1", message);

    }

    public void send1() {
        String message = "hi, i am message 1";
        System.out.println("Sender :" + message);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", message);
    }


    public void send2() {
        String message = "hi, i am messages 2";
        System.out.println("Sender : " + message);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", message);
    }





}
