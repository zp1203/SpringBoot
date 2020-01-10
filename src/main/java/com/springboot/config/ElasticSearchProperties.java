package com.springboot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@PropertySource("classpath:config/esConfig.properties")
public class ElasticSearchProperties {

    @Value("${ES_INDEX_NAME}")
    private String esMainIndex;			// 主索引【可能我们会建立多张索引库】

    @Value("${ES_TYPE}")
    private String esMainType;			// 主索引的type

}
