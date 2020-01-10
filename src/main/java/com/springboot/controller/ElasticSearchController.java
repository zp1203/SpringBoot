package com.springboot.controller;

import com.springboot.config.ElasticSearchProperties;
import com.springboot.model.EsDocModel;
import com.springboot.service.ElasticsearchSave;
import com.springboot.service.impl.ElasticSearchSaveImpl;
import com.springboot.utils.EsUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/es")
@Slf4j
public class ElasticSearchController {

    @Autowired
    private ElasticsearchSave elasRepository;
    @Autowired
    private ElasticSearchProperties elasticSearchProperties;

    @RequestMapping(value = "/postMsg")
    @ResponseBody
    public String show(){
        EsDocModel esDocModel = new EsDocModel();
        esDocModel.setId(12000);
        esDocModel.setDocumentName("11111");
        esDocModel.setDocumentUrl("22");
        esDocModel.setEncryptionDocumentName("33");
        //获取连接
        try {
            String s = elasRepository.saveIndex(elasticSearchProperties.getEsMainIndex(), elasticSearchProperties.getEsMainType(), esDocModel.toString());
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public Map<String, Object> getHits(){
        try {
            Map<String, Object> sources = elasRepository.getIndex(elasticSearchProperties.getEsMainIndex(), elasticSearchProperties.getEsMainType(), "12345");
            System.out.println("111");
            return sources;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public String update(){
        EsDocModel esDocModel = new EsDocModel();
        esDocModel.setId(1234);
        esDocModel.setDocumentName("1111ceshi");
        esDocModel.setDocumentUrl("22test");
        esDocModel.setEncryptionDocumentName("33test");
        try {
            elasRepository.updateById(elasticSearchProperties.getEsMainIndex(), elasticSearchProperties.getEsMainType(), "12345",esDocModel.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }












}
