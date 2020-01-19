package com.springboot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


@Data
@Document(indexName = "testdoc" , type = "doc")
public class TestModel implements Serializable {

    public TestModel(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public TestModel() {
    }

    @Id
    private long id;

    private String name;

    private Integer age;



}
