package com.scloud.rabbit.object;

import com.scloud.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create by andy on 2018/5/22
 * RabbitMQ 对对象的支持
 */
@Component
public class ObjectSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(User user) {
        System.out.println("Sender object :" + user.toString());
        this.rabbitTemplate.convertAndSend("object", user);
    }


}
