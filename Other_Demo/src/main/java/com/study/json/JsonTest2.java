package com.study.json;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.bean.SubscribeListObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-06-28 11:47
 * @Description
 **/
@Slf4j
public class JsonTest2 {
    public static void main(String[] args) {
        String json = "{\"SubscribeListObject\":{\"SubscribeObject\":[{\"SubscribeID\":\"342349823408230489230854\",\"SubscribeDetail\":\"7\",\"Title\":\"设备订阅\",\"ResourceURI\":\"/VIID/search\",\"ApplicantName\":\"百诚慧通\",\"ApplicantOrg\":\"百诚慧通\",\"BeginTime\":\"2018-09-13 15:31:48.0\",\"EndTime\":\"2099-09-13 15:31:48.0\",\"ReceiveAddr\":\"http://10.20.81.215:1220/VIID/SubscribeNotifications\",\"ReportInterval\":0,\"Reason\":\"获取数据\",\"OperateType\":0,\"SubscribeStatus\":0,\"ResourceClass\":0,\"ResultFeatureDeclare\":0}]}}";


        Gson togson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
        SubscribeListObject subscribeListObject = togson.fromJson(json, SubscribeListObject.class);
        System.out.println(subscribeListObject);

        SubscribeListObject subscribeListObject1 = JSONObject.parseObject(json, SubscribeListObject.class);
        System.out.println(subscribeListObject1);

    }

}
