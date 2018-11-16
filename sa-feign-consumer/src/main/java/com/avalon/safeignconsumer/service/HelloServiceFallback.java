package com.avalon.safeignconsumer.service;

import com.avalon.sahelloserviceapi.dto.User;
import com.avalon.sahelloserviceapi.service.HelloService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jwwang on 2018/9/21
 */
//@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello(@RequestParam("name") String name) {
        return "error "+name;
    }

    @Override
    public String hello(@RequestHeader("name") String name,@RequestHeader("age") Integer age) {
        return "error "+name+"|"+age;
    }

    @Override
    public String hello(@RequestBody User user) {
        return "error";
    }
}
