package com.scloud.log;

import com.scloud.AbstractCommonJunitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Create by andy on 2018/5/13
 */
@Slf4j
public class LogTest extends AbstractCommonJunitTest {

    @Test
    public void test() {
        log.info("输出 info");
        log.debug("输出 debug");
        log.error("输出 error");
    }




}
