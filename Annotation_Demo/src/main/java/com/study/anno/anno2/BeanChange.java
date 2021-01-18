package com.study.anno.anno2;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.util.StringUtils;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author chengzhihua
 * @description Bean转换
 * @date 2021/1/15
 */
@Slf4j
public class BeanChange {

    /**
     * @param source
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Object Trans(Object source, Class clazz) throws Exception {
        Object target = clazz.getConstructor().newInstance();
        return Trans(source, target);
    }

    /**
     * 不同类不同字段转换
     *
     * @param source 元对象(json/Object对象)
     * @param target 目标对象
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object Trans(Object source, Object target) throws Exception {
        long start = System.currentTimeMillis();
        if (Objects.isNull(source) || StringUtils.isEmpty(source)) {
            log.error("source is null");
            return target;
        }

        if (Objects.isNull(target)) {
            log.error("target not get instance");
        }
        JSONObject jsonObject = null;
        jsonObject = JSONUtil.parseObj(source);
        if (Objects.isNull(jsonObject)) {
            log.error("source parse is null");
            return target;
        }

        Field[] declaredFields = target.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(BeanChangeAnno.class)) {
                BeanChangeAnno changeField = field.getAnnotation(BeanChangeAnno.class);
                if (changeField.isNeedTrans()) {
                    field.setAccessible(true);
                    if (!StringUtils.isEmpty(changeField.changeMap())) {
                        JSONObject changeJsonObject = JSONUtil.parseObj(changeField.changeMap());
                        Object value = changeJsonObject.get(String.valueOf(jsonObject.get(changeField.sourceFieldName())));
                        if (value.getClass() == field.getType()) {
                            field.set(target, value);
                        } else {
                            field.set(target, ConvertUtils.convert(value, field.getType()));
                        }
                    } else {
                        if (jsonObject.get(changeField.sourceFieldName()).getClass() == field.getType()) {
                            field.set(target, jsonObject.get(changeField.sourceFieldName()));
                        } else {
                            field.set(target, ConvertUtils.convert(jsonObject.get(changeField.sourceFieldName()), field.getType()));
                        }
                    }
                }
            }
        }
        log.info("{}",System.currentTimeMillis()-start);
        return target;
    }

}
