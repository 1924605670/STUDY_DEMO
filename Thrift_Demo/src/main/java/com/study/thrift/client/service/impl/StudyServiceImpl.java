package com.study.thrift.client.service.impl;

import com.study.thrift.client.service.StudyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

/**
 * @author chengzhihua
 * @description
 * @date 2021/1/5
 */
@Slf4j
@Service
public class StudyServiceImpl implements StudyService.Iface {
    @Override
    public String thriftFun(String name) throws  TException {
        log.info("getStudentByName");

        return name;
    }


}
