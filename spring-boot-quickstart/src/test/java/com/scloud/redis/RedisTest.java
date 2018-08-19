package com.scloud.redis;

import com.scloud.AbstractCommonJunitTest;
import com.scloud.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;



public class RedisTest extends AbstractCommonJunitTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() throws Exception{
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        User user=new User();
        user.setName("andy");
        user.setAge(23);
        user.setId(1L);
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.neox", user);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neox");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        Assert.assertEquals("andy", operations.get("com.neox").getName());
    }

    @Test
    public void testExist() {
        User user = (User) redisTemplate.opsForValue().get("com.neox");
        System.out.println(user);
        Assert.assertEquals("andy", user.getName());
    }





}
