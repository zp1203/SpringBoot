package com.springboot.service.impl;

import com.springboot.config.ElasticSearchProperties;
import com.springboot.service.ElasticsearchSave;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;

@Service
public class ElasticSearchSaveImpl implements ElasticsearchSave {

    @Autowired
    private TransportClient client;

    @Override
    public String saveIndex(String indexName,String type,String data){
        try{
            IndexResponse indexResponse = client.prepareIndex(indexName,type).setId("1234777898775").setSource(data).get();
            if(indexResponse.status().getStatus()==201){
                return indexResponse.getId();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> getIndex(String indexName, String type, String id){
        try {
            GetResponse getResponse = client.prepareGet(indexName,type,id).get();
            return getResponse.getSource();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String updateById(String indexName, String type, String id, String sources){
        try {
            UpdateResponse updateResponse = client.prepareUpdate(indexName,type,id).setDoc(sources).get();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
