//package com.springboot.config;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//@Configuration
//@PropertySource("classpath:config/esConfig.properties")
//public class ElasticSearchConfig {
//
//    @Value("${ES_ADDRESSES}")
//    private String esHosts;
//
//    @Value("${ES_CLUSTER_NAME}")
//    private String clusterName;
//
//    @Bean
//    public TransportClient geTransportClient() throws UnknownHostException {
//        Settings settings= Settings.builder().put("cluster.name",clusterName).build();
//        TransportClient client=new PreBuiltTransportClient(settings);
//        String[] hosts=esHosts.split(",");
//        for(String host:hosts){
//            // 如果我们配置多个host，自动识别配置多个节点
//            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host),9500));
//        }
//        return client;
//    }
//
//}
//
