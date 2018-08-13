package com.scloud.es;

import com.scloud.CommonTest;
import com.scloud.domain.Book;
import com.scloud.repository.es.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Create by andy on 2018/7/30
 */
@Slf4j
public class EsTest extends CommonTest {

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

    @Test
    public void findByName() {
        Book book = bookRepository.findByName("《Elasticsearch 技术解析与实战》");
        log.info("book content : {}", book.getContent());

        System.out.println("---------------------------------------");
        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> page = bookRepository.findAll(pageable);
        log.info("book size: {}",page.getTotalElements());
    }

    @Test
    public void testFindByContent() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> page = bookRepository.queryBooksByContent("Elasticsearch是一个强大的搜索引擎", pageable);
        log.info("book size {} ",page.getTotalElements());
    }

    @Test
    public void testBatchSave() {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setAuthor("zhangcm");
        book.setName("《Java 8实战》");
        book.setPublicTime("2016年04月");
        book.setContent("本书全面介绍了Java 8 这个里程碑版本的新特性，包括Lambdas、流和函数式编程。有了函数式的编程特性，可以让代码更简洁，同时也能自动化地利用多核硬件。全书分四个部分：基础知识、函数式数据处理、高效Java 8 编程和超越Java 8，清晰明了地向读者展现了一幅Java 与时俱进的现代化画卷。");
        book.setIntroduce("本书全面介绍了Java 8 这个里程碑版本的新特性，包括Lambdas、流和函数式编程。有了函数式的编程特性，可以让代码更简洁");

        Book book1 = new Book();
        book1.setId(UUID.randomUUID().toString());
        book1.setAuthor("沃尔斯");
        book1.setName("Spring实战");
        book1.setPublicTime("2016年04月");
        book1.setContent("《Spring实战（第4版）》是经典的、畅销的Spring学习和实践指南。 第4版针对Spring 4进行了全面更新。全书分为四部分。第1部分介绍Spring框架的核心知识。第二部分在此基础上介绍了如何使用Spring构建Web应用程序。第三部分告别前端，介绍了如何在应用程序的后端使用Spring。第四部分描述了如何使用Spring与其他的应用和服务进行集成。 《Spring实战（第4版）》适用于已具有一定Java 编程基础的读者，以及在Java 平台下进行各类软件开发的开发人员、测试人员，尤其适用于企业级Java 开发人员。本书既可以被刚开始学习Spring 的读者当作学习指南，也可以被那些想深入了解Spring 某方面功能的专业用户作为参考用书。");
        book1.setIntroduce("《Spring实战（第4版）》是经典的、畅销的Spring学习和实践指南。 第4版针对Spring 4进行了全面更新。");

        List<Book> bookList = Arrays.asList(book, book1);
        bookRepository.saveAll(bookList);

    }

    @Test
    public void testSearchQuery() {
        Pageable pageable = PageRequest.of(0, 10);
        QueryBuilder queryBuilder = QueryBuilders.termQuery("author", "andy");
        Page<Book> books =  bookRepository.search(queryBuilder,pageable);
        log.info("total size:{}",books.getTotalElements());
        log.info("first book's name is {}", books.getContent().get(0).getName());
    }

    @Test
    public void testFindByAuthor() {
        List<Book> books = bookRepository.findAllByAuthor("andy");
        if (CollectionUtils.isEmpty(books)) {
            log.info("search no data ");
        }else{
            log.info("books size = {}", books.size());
            log.info(books.get(0).getName());
        }
    }



}
