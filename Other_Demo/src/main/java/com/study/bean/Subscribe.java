package com.study.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-04-09 14:29
 * @Description
 **/
@Data
public class Subscribe implements Serializable {

    /**
     * string(33) 订阅标识符
     */
    private String SubscribeID;
    /**
     * 订阅标题
     */
    private String Title;
    /**
     * 订阅类别 7	视频卡口目录 13	自动采集的车辆信息
     */
    private String SubscribeDetail;
    /**
     * 订阅资源路径
     */
    private String ResourceURI;
    /**
     * 申请人
     */
    private String ApplicantName;
    /**
     * 申请单位
     */
    private String ApplicantOrg;
    /**
     * 订阅开始时间 格式：
     * YYYYMMDDhhmmss
     */
    private Date BeginTime;

    private Date EndTime;
    /**
     * 信息接收地址
     */
    private String ReceiveAddr;
    /**
     * 信息上报间隔时间
     */
    private int ReportInterval;
    /**
     * 理由
     */
    private String Reason;
    /**
     * 操作类型 0：订阅；1：取消订阅
     */
    private int OperateType;
    /**
     * 订阅执行状态 0：订阅中
     * 1：已取消订阅
     * 2：订阅到期
     * 9：未订阅
     * 该字段只读
     */
    private int SubscribeStatus;
    /**
     * 订阅取消单位
     */
    private String SubscribeCancelOrg;
    private String SubscribeCancelPerson;
    private Date CancelTime;
    private String CancelReason;

}
