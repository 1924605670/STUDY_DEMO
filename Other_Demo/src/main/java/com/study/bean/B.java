package com.study.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-06-24 10:51
 * @Description
 **/
@Data
public class B implements Serializable {

    private C valueC;

    private Set D;
}
