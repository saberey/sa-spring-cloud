package com.avalon.sazuul;

import com.avalon.sazuul.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class SaZuulApplication {

    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
    public static void main(String[] args) {
        SpringApplication.run(SaZuulApplication.class, args);
    }
}
