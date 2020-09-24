package com.study.rabbit.beans;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Table(name = "ds_tfcpass_all")
public class DsTfcpassAllEntity {
    @Id
    private String gcbh;
    private String sbxh;
    private Date gcsj;
    private String hphm;
    private String hpzl;
    private String hpys;
    private Double cwkc;
    private String clys;
    private String cllx;
    private Double sd;
    private Integer cdbh;
    private String fx;
    private String sjlx;
    private String wfdm;
    private String tpurl;
    private String tpurl1;
    private String tpurl2;
    private String platePosition;
    private String csbh;
    private Date acceptTime;
    private Date analysisTime;
    private Date sendToMqTime;
    private Integer sendToMqState;
    private Date getForMqTime;
    private Date storageTime;
    private Date sendToLdTime;
    private Date sendToRminfTime;
    private Date sendToTmriTime;
    private Date sendToLthTime;
    private Date saveToLthTime;


}
