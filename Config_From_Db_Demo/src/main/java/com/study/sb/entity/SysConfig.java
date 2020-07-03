package com.study.sb.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-07-02 16:21
 * @Description
 **/
@Data
@Table(name = "sys_config")
@Entity
public class SysConfig implements Serializable {

    @Id
    private Long id;

    @Column
    private String code;

    @Column
    private String value;

    @Column
    private String name;

    @Column
    private String description;

}