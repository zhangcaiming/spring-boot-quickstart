package com.scloud.es;

import com.scloud.domain.Book;
import com.scloud.repository.es.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Create by andy on 2018/7/30
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void save() {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setAuthor("andy");
        book.setName("《Elasticsearch 技术解析与实战》");
        book.setPublicTime("2017年01月");
        book.setContent("Elasticsearch是一个强大的搜索引擎，提供了近实时的索引、搜索、分析功能。本书作者根据自己多年的开发经验，总结了使用和开发Elasticsearch的实战经验。本书全面介绍Elasticsearch系统结构与功能配置，以及实际应用案例，包括工具、方法、原则和佳实践。主要内容包括Elasticsearch基本概念与配置，索引的基本概念、管理与设置，架构设计中的字段、对象、映射，搜索中的结构、各种查询方式，聚合中的数字聚合、桶聚合、管道聚合，集群中的监控方式、配置案例，分析模块中的中文分词器、过滤器，高级设置中的关键点，监控与安全方面的技巧，ELK综合示例等。不管你是全文检索和Elasticsearch的初学者，还是Elasticsearch用户，你都能从书中获益");
        book.setIntroduce("Elasticsearch是一个强大的搜索引擎，提供了近实时的索引、搜索、分析功能。本书作者根据自己多年的开发经验，总结了使用和开发Elasticsearch的实战经验。本书全面介绍Elasticsearch系统结构与功能配置...");
        Book newBook = bookRepository.save(book);
        System.out.println(newBook);
    }



}
