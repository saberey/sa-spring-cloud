package com.avalon.sahelloserviceapi.service;

import com.avalon.sahelloserviceapi.dto.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author jwwang on 2018/9/12
 */
@RequestMapping("/refactor")
public interface HelloService {

    @RequestMapping(value = "/hello4",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);
    @RequestMapping(value = "/hello5",method = RequestMethod.GET,consumes = "application/json")
    String hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);
    @RequestMapping(value = "/hello6",method = RequestMethod.POST,consumes = "application/json")
    String hello(@RequestBody User user);
}
