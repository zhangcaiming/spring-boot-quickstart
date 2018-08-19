package com.scloud.rabbitmq;

import com.scloud.AbstractCommonJunitTest;
import com.scloud.domain.User;
import com.scloud.rabbit.object.ObjectSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ObjectTest extends AbstractCommonJunitTest {

    @Autowired
    private ObjectSender objectSender;

    @Test
    public void test() throws Exception{
        User user = new User();
        user.setName("andy");
        user.setPassword("123456");
        objectSender.send(user);
    }

}
