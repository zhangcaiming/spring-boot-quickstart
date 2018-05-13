package com.scloud.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by andy on 2018/5/13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {

    @Test
    public void test() {
        log.info("输出 info");
        log.debug("输出 debug");
        log.error("输出 error");
    }




}
