package com.avalon.saweb.controller;

import com.avalon.sahelloserviceapi.dto.User;
import com.avalon.sahelloserviceapi.service.HelloService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jwwang on 2018/9/19
 */
@RestController
public class RefactorHelloController implements HelloService {
    @Override
    public String hello(@RequestParam  String name) {
        return "hello "+name;
    }

    @Override
    public String hello(@RequestHeader("name") String name,@RequestHeader("age") Integer age) {
        return  name+age;
    }

    @Override
    public String hello(@RequestBody User user) {
        return "hello "+user.getName()+","+user.getAge();
    }
}
