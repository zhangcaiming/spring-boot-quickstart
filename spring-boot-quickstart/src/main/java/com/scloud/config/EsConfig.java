package com.scloud.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Create by andy on 2018/7/30
 * ES 配置
 */
@Configuration
public class EsConfig {

    /**
     * 防止netty的bug
     * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
     */
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }



}
