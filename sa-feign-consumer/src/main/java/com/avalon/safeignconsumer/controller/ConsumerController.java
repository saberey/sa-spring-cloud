package com.avalon.safeignconsumer.controller;

import com.avalon.bean.User;
import com.avalon.safeignconsumer.service.HelloService;
import com.avalon.safeignconsumer.service.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jwwang on 2018/9/12
 */
@RestController
public class ConsumerController {
    @Autowired
    HelloService helloService;
    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        return helloService.hello();
    }
    @RequestMapping(value = "/feign-consumer2",method = RequestMethod.GET)
    public String helloConsumer2(){
        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append(helloService.hello()).append("\n");
        stringBuilder.append(helloService.hello1("DIDI")).append("\n");
        //stringBuilder.append(helloService.hello2("DIDI",30)).append("\n");
        //stringBuilder.append(helloService.hello3(new User("DIDI",30))).append("\n");
        return stringBuilder.toString();
    }
    @Autowired
    RefactorHelloService refactorHelloService;
    @GetMapping(value = "/feign-consumer3")
    public String helloConsumer3(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(refactorHelloService.hello("test")).append("\n");
        stringBuilder.append(refactorHelloService.hello("test",123)).append("\n");
        stringBuilder.append(refactorHelloService.hello(new com.avalon.sahelloserviceapi.dto.User("test",123))).append("\n");
        return stringBuilder.toString();
    }
}
