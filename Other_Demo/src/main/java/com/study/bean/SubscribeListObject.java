package com.study.bean;


import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class SubscribeListObject implements Serializable {

    private SubscribeObject SubscribeListObject;

}
