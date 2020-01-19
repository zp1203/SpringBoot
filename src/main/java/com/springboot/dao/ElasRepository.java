package com.springboot.dao;

import com.springboot.model.TestModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

public interface ElasRepository extends CrudRepository<TestModel,Long> {
}
