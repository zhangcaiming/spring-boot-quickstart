package com.scloud.rabbitmq;

import com.scloud.domain.User;
import com.scloud.rabbit.object.ObjectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectTest {

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
