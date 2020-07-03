package com.study.sb.repository;

import com.study.sb.entity.SysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface SystemConfigRepository extends JpaRepository<SysConfig, Long> {

}
