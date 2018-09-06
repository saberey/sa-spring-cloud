package com.avalon.saweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.List;

/**
 * @author jwwang
 */
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(){
        List<String> services = client.getServices();
        services.stream().forEach( each -> System.out.println(each));
        List<ServiceInstance> isList = client.getInstances("hello-service");
        isList.stream().forEach( instance -> System.out.println(instance.getHost()+"|"+instance.getUri()+"|"+instance.getServiceId()));
        return "hello world!";
    }
}
