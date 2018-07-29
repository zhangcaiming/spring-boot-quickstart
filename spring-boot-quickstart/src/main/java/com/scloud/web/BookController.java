package com.scloud.web;

import com.google.common.collect.Lists;
import com.scloud.domain.Book;
import com.scloud.repository.es.BookRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by andy on 2018/7/29
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookRepository bookRepository;

    @GetMapping("get/{id}")
    public Book getBook(@PathVariable String id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("put")
    public Book putBook(@RequestBody Book book) {
        if (book != null) {
            return bookRepository.save(book);
        }
        return new Book();
    }

    @GetMapping("all")
    public List<Book> getAll() {
        return Lists.newArrayList(bookRepository.findAll());
    }

}
