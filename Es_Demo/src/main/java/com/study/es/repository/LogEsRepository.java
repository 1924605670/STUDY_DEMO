package com.study.es.repository;

import com.study.es.bean.LogBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogEsRepository extends ElasticsearchRepository<LogBean,String> {

  @Override
  Optional<LogBean> findById(String id);

}
