package com.scloud.repository.es;

import com.scloud.domain.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Create by andy on 2018/7/29
 */
public interface BookRepository extends ElasticsearchRepository<Book, String> {
    Book findByName(String id);
}
