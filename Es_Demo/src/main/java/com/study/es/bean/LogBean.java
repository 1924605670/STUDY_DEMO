package com.study.es.bean;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Date;
import java.util.UUID;

@Data
@Document(indexName = "log",type = "logbean",indexStoreType="fs", shards = 1, replicas = 0)
public class LogBean {

    public LogBean() {
    }

    public LogBean(String id, String content, String date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    public LogBean(String content) {
        this.content = content;
    }

    @Id
    private String id = UUID.randomUUID().toString();
    @Field(type = FieldType.Text,store = true)
    private String content;
    @Field(type = FieldType.Text,store = true)
    private String date = DateUtil.now();

}
