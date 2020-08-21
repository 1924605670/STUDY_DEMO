package com.study.bean;

import com.google.gson.Gson;
import lombok.Data;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-08-17 16:01
 * @Description
 **/
@Data
public class NoGetBean {

    private String HostName;

    private String Ip;


    public static void main(String[] args) {
        NoGetBean noGetBean = new NoGetBean();
        noGetBean.HostName="11";
        noGetBean.Ip="22";
        Gson gson = new Gson();
        System.out.println(gson.toJson(noGetBean));
    }

}
