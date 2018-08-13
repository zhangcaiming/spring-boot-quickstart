package com.scloud.rabbitmq;

import com.scloud.CommonTest;
import com.scloud.rabbit.fanout.FanoutSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class FanoutTest extends CommonTest{

    @Autowired
    private FanoutSender sender;

    @Test
    public void fanoutSend() throws Exception{
        sender.send();
    }



}
