package com.avalon.safeignconsumer.service;

import com.avalon.sahelloserviceapi.service.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author jwwang on 2018/9/19
 */
@FeignClient(value = "HELLO-SERVICE")
public interface RefactorHelloService extends HelloService {
}
