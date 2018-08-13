package com.scloud.rabbitmq;

import com.scloud.CommonTest;
import com.scloud.rabbit.hello.HelloSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class HelloTest extends CommonTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void hell() throws Exception {
        helloSender.send();
    }


}
