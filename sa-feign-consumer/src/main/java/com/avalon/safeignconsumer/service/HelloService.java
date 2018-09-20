package com.avalon.safeignconsumer.service;

import com.avalon.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author jwwang on 2018/9/12
 */
@FeignClient("hello-service")
public interface HelloService {

    @RequestMapping("/hello")
    String hello();
    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello1(@RequestParam("name") String name);
    @RequestMapping(value = "/hello2",method = RequestMethod.GET,consumes = "application/json")
    String hello2(@RequestHeader("name") String name, @RequestHeader("age") Integer age);
    @RequestMapping(value = "/hello3",method = RequestMethod.POST,consumes = "application/json")
    String hello3(@RequestBody User user);
}
