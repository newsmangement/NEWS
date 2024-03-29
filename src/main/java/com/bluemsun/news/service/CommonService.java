package com.bluemsun.news.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by mafx on 2019/6/15.
 */
@Service
public class CommonService {
    @Autowired
    Jedis jedis;

    public void delCache(String key){
       jedis.del(key);

    }

    public void sendMessageKafka(String message){
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.116.129:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //生产者发送消息
        String topic = "message";
        Producer<String, String> procuder = new KafkaProducer<String,String>(props);
        ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic, message);
        procuder.send(msg);

        //列出topic的相关信息
        List<PartitionInfo> partitions = new ArrayList<PartitionInfo>() ;
        partitions = procuder.partitionsFor(topic);
        for(PartitionInfo p:partitions)
        {
            System.out.println(p);
        }

        System.out.println("send message over.");
        procuder.close(100, TimeUnit.MILLISECONDS);
    }
}


