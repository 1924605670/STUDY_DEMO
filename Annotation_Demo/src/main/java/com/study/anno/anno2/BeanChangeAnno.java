package com.study.anno.anno2;

import java.lang.annotation.*;
import java.util.Map;

/**
 * Bean之间字段名不同相互转换
 * 在实际应用中经常会出现两个类之间的属性相互赋值的情况，由于两个类的属性名不一致，但是含义相同，这个时候就会使用 a.setFieldA1(b.getFieldB1)这种方式
 * 这个时候就可以是用该注解，配合BeanChange工具类的trans方法来进行自动转换
 *
 * 好处：1、在target类中标明需要赋值source中的哪一个属性，可以一目了然两个类中的含义相同的字段
 * 好处：2、避免频繁的写set赋值方法
 * @author czh
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanChangeAnno {
    /**
     * 源字段名
     */
    String sourceFieldName() default "";

    /**
     * 是否需要转换
     */
    boolean isNeedTrans() default false;

    /**
     * 描述
     */
    String desc() default "";

    /**
     * 赋值规律，例如 元数据的 1需要转换成目标对象的男 2需要转换成目标对象的女
     * 采用json格式 {"1":"男","2":"女"}
     */
    String changeMap() default "";



}
