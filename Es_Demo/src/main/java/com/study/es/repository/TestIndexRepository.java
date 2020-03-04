package com.study.es.repository;

import com.study.es.bean.TestIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestIndexRepository extends ElasticsearchRepository<TestIndex,Long> {
}
