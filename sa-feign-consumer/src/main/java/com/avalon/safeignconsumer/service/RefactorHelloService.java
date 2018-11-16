package com.avalon.safeignconsumer.service;

import com.avalon.safeignconsumer.config.DisableHystrixConfiguration;
import com.avalon.sahelloserviceapi.service.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author jwwang on 2018/9/19
 */
//禁用hystrix 指定fallback
@FeignClient(value = "HELLO-SERVICE",configuration = DisableHystrixConfiguration.class,fallback = HelloServiceFallback.class)
public interface RefactorHelloService extends HelloService {
}
