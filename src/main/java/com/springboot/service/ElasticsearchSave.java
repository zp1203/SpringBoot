package com.springboot.service;

import java.util.Map;

public interface ElasticsearchSave {

    public String saveIndex(String indexName,String type,String data);

    public Map<String, Object> getIndex(String indexName, String type, String id);

    public String updateById(String indexName, String type, String id, String sources);

}
