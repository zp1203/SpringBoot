package com.springboot.utils;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class EsUtils {

    //读取esConfig配置文件
    public static Map<String,String> getPro() throws IOException {
        Map<String,String> mapPro = new HashMap<>(5);
        // 避免netty冲突
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        //读取es 配置文件
        InputStream esConfig = EsUtils.class.getClassLoader().getResourceAsStream("config/".concat("esConfig.properties"));
        Properties prop = new Properties();
        prop.load(esConfig);
        String clusterName = prop.getProperty("ES_CLUSTER_NAME");
        String addresses = prop.getProperty("ES_ADDRESSES");
        String port = prop.getProperty("ES_PORT");
        String index = prop.getProperty("ES_INDEX_NAME");
        String type = prop.getProperty("ES_TYPE");
        mapPro.put("clusterName",clusterName);
        mapPro.put("addresses",addresses);
        mapPro.put("port",port);
        mapPro.put("index",index);
        mapPro.put("type",type);
        return mapPro;
    }

    //获取连接
    public static TransportClient getClient() throws IOException {
        Map<String, String> pro = getPro();
        //获取地址
        String addresses = pro.get("addresses");
        //获取端口号
        String port = pro.get("port");
        // on startup
        Settings settings = Settings.builder()
                                    .put("cluster.name",pro.get("clusterName"))
                                    .build();
        return new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(addresses), Integer.parseInt(port)));
    }

    //关闭链接
    public static void close(TransportClient client){
        if (client != null) {
            client.close();
        }
    }



}
