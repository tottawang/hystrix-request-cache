package com.sample.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.service.HystrixWrappedService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/api")
public class RestResource {

  @Autowired
  private HystrixWrappedService service;

  @GET
  @Path("hystrix")
  public String getUserProjects() {
    String cacheKey = "myCacheKey";
    System.out.println("First call: " + service.getContent(cacheKey));
    System.out.println("Second call: " + service.getContent(cacheKey));
    return "done";
  }
}
