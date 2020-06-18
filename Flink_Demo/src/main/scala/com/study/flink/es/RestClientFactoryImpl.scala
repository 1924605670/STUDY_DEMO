package com.study.flink.es

import org.apache.flink.streaming.connectors.elasticsearch6.RestClientFactory
import org.apache.http.message.BasicHeader
import org.elasticsearch.client.RestClientBuilder

class RestClientFactoryImpl extends RestClientFactory{
  override def configureRestClientBuilder(restClientBuilder: RestClientBuilder): Unit = {
    restClientBuilder.setDefaultHeaders(Array{new BasicHeader("Content-Type", "application/json")}) //以数组的形式可以添加多个header
    restClientBuilder.setMaxRetryTimeoutMillis(90000)
    restClientBuilder.build()
  }
}
