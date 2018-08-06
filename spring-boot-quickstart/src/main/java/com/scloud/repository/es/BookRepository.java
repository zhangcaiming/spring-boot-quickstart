package com.scloud.repository.es;

import com.scloud.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Create by andy on 2018/7/29
 */
public interface BookRepository extends ElasticsearchRepository<Book, String> {
    Book findByName(String id);

    Page<Book> queryBooksByContent(String content, Pageable pageable);
    List<Book> findAllByAuthor(String author);
}
