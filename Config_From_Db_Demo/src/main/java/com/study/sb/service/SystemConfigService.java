package com.study.sb.service;

import com.study.sb.entity.SysConfig;
import com.study.sb.repository.SystemConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-07-02 16:19
 * @Description
 **/
@Service
@Component
public class SystemConfigService {

    @Autowired
    private SystemConfigRepository systemConfigRepository;

    public List<SysConfig> getAll() {
        return systemConfigRepository.findAll();
    }


}
