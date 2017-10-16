package com.sample.conf;


import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
@Component
@Priority(1)
public class HystrixContextFilter implements ContainerRequestFilter, ContainerResponseFilter {
  private static final Logger logger = LoggerFactory.getLogger(HystrixContextFilter.class);
  private static final String HYSTRIX_CONTEXT = "HYSTRIX_CONTEXT";

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    HystrixRequestContext context = HystrixRequestContext.initializeContext();
    requestContext.setProperty(HYSTRIX_CONTEXT, context);
    logger.debug("Hystrix Request Context initialized successfully");
  }

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext
      responseContext) throws IOException {
    HystrixRequestContext context = (HystrixRequestContext) requestContext.getProperty
        (HYSTRIX_CONTEXT);
    context.shutdown();
    logger.debug("Hystrix Request Context shutdown successfully");
  }
}