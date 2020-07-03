package com.study.json;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-06-24 10:31
 * @Description
 **/
@Data
public class ObjectDemo {
    private String id;
    private int age;
    private double dValue;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date time;
}
