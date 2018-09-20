package com.avalon.saweb.controller;

import com.avalon.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author jwwang
 */
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index() throws InterruptedException {
        int sleeptime = new Random().nextInt(3000);
        logger.info("sleeptime:{}",sleeptime);
        TimeUnit.MILLISECONDS.sleep(sleeptime);
        List<String> services = client.getServices();
        services.stream().forEach( each -> System.out.println(each));
        List<ServiceInstance> isList = client.getInstances("hello-service");
        isList.stream().forEach( instance -> System.out.println(instance.getHost()+"|"+instance.getUri()+"|"+instance.getServiceId()));
        return "hello world!";
    }
    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    public String hello1(@RequestParam("name") String name ){
        return "hello"+name;
    }
    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    public User hello2(@RequestHeader String name,@RequestHeader Integer age){
        return new User(name,age);
    }
    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    public String hello3(@RequestBody User user){
        return "hello"+user.getName()+","+user.getAge();
    }
}
