package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.sample.conf.Application;
import com.sample.conf.HttpWebClient;
import com.sample.exception.IntegrationRuntimeException;

@Component
public class HystrixWrappedService {

  @Value("${HYSTRIX_TEST_URL:url_not_accessiable}")
  private String HYSTRIX_TEST_URL;

  @Autowired
  @Qualifier(Application.DEFAULT_REST_TEMPLATE)
  protected RestTemplate restTemplate;

  @HystrixCommand(groupKey = HttpWebClient.GROUP, commandKey = HttpWebClient.COMMAND_GET,
      threadPoolKey = HttpWebClient.THREAD_POOL_KEY,
      ignoreExceptions = {IntegrationRuntimeException.class})
  @CacheResult
  public String getContent(@CacheKey String cacheKey) {
    System.out.println("getContent called");
    return cacheKey;
  }

}
