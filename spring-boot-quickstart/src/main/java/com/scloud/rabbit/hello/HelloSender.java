package com.scloud.rabbit.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Create by andy on 2018/5/22
 * hello 队列 发送者
 */
@Slf4j
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String message = "hello " + new Date();
        System.out.println("Sender : " + message);
        this.rabbitTemplate.convertAndSend("hello", message);
    }

}
