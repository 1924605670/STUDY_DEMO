package com.study.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-06-24 10:50
 * @Description
 **/
@Data
public class A implements Serializable {

    private String valueA;

    private List<B> valuesB;

    private C[] valuesC;



}
