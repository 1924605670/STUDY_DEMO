package com.study.flink;

import com.study.flink.es.RestClientFactoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch.util.RetryRejectedExecutionFailureHandler;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.logging.Logger;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-06-18 14:24
 * @Description
 **/
@Slf4j
public class EsFlink {


    private static final String READ_TOPIC = "kafkatest";

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties props = new Properties();
        props.put("bootstrap.servers", "server1:9092");
        props.put("zookeeper.connect", "server1:2181");
        props.put("group.id", "student-group-1");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest");

        DataStreamSource<String> student = env.addSource(new FlinkKafkaConsumer011<>(
                //这个 kafka topic 需要和上面的工具类的 topic 一致
                READ_TOPIC,
                new SimpleStringSchema(),
                props)).setParallelism(1);
//                .map(string -> JSON.parseObject(string, Student.class)); //Fastjson 解析字符串成 student 对象
        student.print();
        log.info("student:" + student);
        List<HttpHost> esHttphost = new ArrayList<>();
        esHttphost.add(new HttpHost("dn3.bcht", 19200, "http"));

        ElasticsearchSink.Builder<String> esSinkBuilder = new ElasticsearchSink.Builder<>(
                esHttphost,
                new ElasticsearchSinkFunction<String>() {

                    public IndexRequest createIndexRequest(String element) {
                        Map<String, String> json = new HashMap<>();
                        json.put("data", element);
                        log.info("data:" + element);

                        return Requests.indexRequest()
                                .index("index-student")
                                .type("student")
                                .source(json);
                    }

                    @Override
                    public void process(String element, RuntimeContext ctx, RequestIndexer indexer) {
                        indexer.add(createIndexRequest(element));
                    }
                }
        );

        esSinkBuilder.setBulkFlushMaxActions(1);
//        esSinkBuilder.setRestClientFactory(
//                restClientBuilder -> {
//                    restClientBuilder.setDefaultHeaders()
//                }
//        );
        esSinkBuilder.setRestClientFactory(new RestClientFactoryImpl());
        esSinkBuilder.setFailureHandler(new RetryRejectedExecutionFailureHandler());

        student.addSink(esSinkBuilder.build());
        env.execute("flink learning connectors kafka");
    }

}
