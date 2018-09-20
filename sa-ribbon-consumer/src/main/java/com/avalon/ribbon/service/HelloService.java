package com.avalon.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalTime;

/**
 * @author jwwang on 2018/9/11
 */
@Service
public class HelloService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        LocalTime start = LocalTime.now();
        String result = restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
        LocalTime end = LocalTime.now();
        logger.info("service cost times :{}", Duration.between(start,end).toMillis());
        return result;
    }

    public String helloFallback(){
        return "error";
    }
}
