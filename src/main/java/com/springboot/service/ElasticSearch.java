package com.springboot.service;

import com.springboot.model.TestModel;

import java.util.Optional;

public interface ElasticSearch {

    void save(TestModel testmodel);

    Optional<TestModel> select(Long id);

}
