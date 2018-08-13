package com.scloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;


@SpringBootApplication
@EnableScheduling
@EnableElasticsearchRepositories(basePackages = "com.scloud.repository.es")
public class QuickstartApplication {

	public static void main(String[] args) {
        System.out.println("项目启动中...");
        SpringApplication.run(QuickstartApplication.class, args);
        System.out.println("项目启动完成!");
    }

    /**
     * 配置文件上传设置
     */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大
        factory.setMaxFileSize("5MB");
        // 设置总上传数据总大小
        factory.setMaxRequestSize("50MB");
        return factory.createMultipartConfig();
    }



}
