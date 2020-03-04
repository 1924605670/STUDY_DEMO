package com.study.es.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "test_index", type = "test_type", createIndex = true, indexStoreType = "fs", shards = 3, replicas = 2)
public class TestIndex {

    public TestIndex() {
    }

    public TestIndex(Long id, String content, Date date, Double price) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.price = price;
    }

    @Id
    private Long id;

    @Field(index = true, type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String content;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date;

    @Field(type = FieldType.Double)
    private Double price;


}
