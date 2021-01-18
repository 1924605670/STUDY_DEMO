package com.study.anno.anno2;

import lombok.Data;

/**
 * @author chengzhihua
 * @description
 * @date 2021/1/15
 */
@Data
public class Bean1 {
    /**
     * 姓名
     */
    @BeanChangeAnno(sourceFieldName="xm",isNeedTrans = true,desc = "姓名")
    private String name;

    /**
     * 年龄
     */
    @BeanChangeAnno(sourceFieldName="nl",isNeedTrans = true,desc = "年龄")
    private int age;

    /**
     * 性别
     */
    @BeanChangeAnno(sourceFieldName="xb",isNeedTrans = true,desc = "性别",changeMap = "{\"1\":\"男\",\"2\":\"女\"}")
    private String sex;

    public static void main(String[] args) throws Exception {
        String json = "{\"xm\": \"zhangsan\",\"nl\": 4,\"xb\":\"1\"}";

//        String nl = (String) JSONUtil.parseObj(json).get("xm");
//        Integer age = (Integer) JSONUtil.parseObj(json).get("nl");
//
       Bean1 b = null;
//        Bean2 b2 = new Bean2();
//        b2.setXm("李四");
//        b2.setNl("9999");
//        b.setName(nl);
//        b.setAge(age);
        Bean1 trans = (Bean1) BeanChange.Trans(json,  Bean1.class);
        System.out.println(trans);

    }
}

