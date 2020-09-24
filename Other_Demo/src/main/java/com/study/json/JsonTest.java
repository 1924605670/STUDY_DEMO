package com.study.json;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.bean.A;
import com.study.bean.B;
import com.study.bean.C;
import com.study.bean.D;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-06-24 10:28
 * @Description
 **/
@Slf4j
public class JsonTest {

    public static void main(String[] args) {
        ObjectDemo objectDemo = new ObjectDemo();
        objectDemo.setId("1111");
        objectDemo.setAge(10);
        objectDemo.setDValue(1.2);
        objectDemo.setTime(new Date());
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();

        GsonBuilder gsonBuilder = new GsonBuilder();
        // 不导出实体类中没有用@Expose注解的属性
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        // 支持Map的key为复杂对象的形式
        gsonBuilder.enableComplexMapKeySerialization();
        // 格式化date型　　
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        // 会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
        Gson gson = gsonBuilder.create();
        // 属性值为空时，也会有对应值 null
        gson.serializeNulls();


        String json = gson.toJson(objectDemo);
        System.out.println(json);

        ObjectDemo objectDemo1 = JSONObject.parseObject(json, ObjectDemo.class);
        System.out.println(JSONObject.toJSONString(objectDemo1));


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "2222");
        jsonObject.put("age", 20);
        jsonObject.put("dValue", 2.3);
        jsonObject.put("time", DateUtil.now());
        System.out.println(jsonObject.toString());

        ObjectDemo objectDemo2 = JSONObject.parseObject(jsonObject.toString(), ObjectDemo.class);
        System.out.println(JSONObject.toJSONString(objectDemo2));

        System.out.println(DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_FORMAT));
        System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));


        A a = new A();
        B b1 = new B();
        B b2 = new B();
        C c1 = new C();
        C c2 = new C();
        c1.setId("222");
        b1.setValueC(c1);
        c2.setId("333");
        b2.setValueC(c2);
        List list = new ArrayList();
        list.add(b1);
        list.add(b2);
        D d = new D();
        d.setDValue("ddd");
        Set dSet = new HashSet();
        dSet.add(d);
        b1.setD(dSet);
        b2.setD(dSet);
        a.setValuesB(list);
        a.setValueA("111");
        a.setValuesC(new C[]{c1, c2});
        System.out.println(gson.toJson(a));
        A a1 = JSONObject.parseObject(JSONObject.toJSONString(a), A.class);
        System.out.println(gson.toJson(a1));


    }
}
