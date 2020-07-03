package com.study.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-04-09 14:24
 * @Description
 **/
@Data
public class SubscribeObject implements Serializable {
    @JSONField(name = "SubscribeObject")
    private List<Subscribe>  SubscribeObject;

}
