package com.scloud.initRes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动时初始化资源
 * CommandLineRunner 接口的 Component 会在所有 Spring Beans 都初始化之后，SpringApplication.run() 之前执行，
 * 非常适合在应用程序启动之初进行一些数据初始化的工作。
 * @author andy
 * @create 2018/8/13 21:50
 **/
@Component
@Slf4j
public class InitResource implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        //@TODO you can init resource here
        log.info("正在初始化项目资源...");
    }
}
