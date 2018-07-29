package com.scloud.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Create by andy on 2018/7/29
 */
@Document(indexName="books",type="library")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @Id
    private String id;
    private String name;
    private String author;
    private String content;
    private String publicTime;
    private String introduce;


}
