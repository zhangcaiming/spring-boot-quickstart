package com.scloud.rabbitmq;

import com.scloud.AbstractCommonJunitTest;
import com.scloud.rabbit.hello.HelloSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class HelloTest extends AbstractCommonJunitTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void hell() throws Exception {
        helloSender.send();
    }


}
