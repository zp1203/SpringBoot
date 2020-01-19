package com.springboot.service.impl;

import com.springboot.dao.ElasRepository;
import com.springboot.model.TestModel;
import com.springboot.service.ElasticSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ElasticSearchImpl implements ElasticSearch {

    @Autowired
    private ElasRepository elasRepository;

    @Override
    public void save(TestModel testmodel) {
        TestModel save = elasRepository.save(testmodel);
        System.out.println(save.toString());
    }
    @Override
    public Optional<TestModel> select(Long id){
        Optional<TestModel> byId = elasRepository.findById(id);
        return byId;
    }

}
