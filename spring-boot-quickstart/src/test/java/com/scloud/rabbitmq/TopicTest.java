package com.scloud.rabbitmq;

import com.scloud.CommonTest;
import com.scloud.rabbit.topic.TopicSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TopicTest extends CommonTest{

    @Autowired
    private TopicSender sender;

    @Test
    public void topic() throws Exception {
        sender.send();
    }

    @Test
    public void topic1() throws Exception {
        sender.send1();
    }

    @Test
    public void topic2() throws Exception {
        sender.send2();
    }

}
