package com.avalon.saeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SaEurekaApplication {

    public static void main(String[] args) {

        SpringApplication.run(SaEurekaApplication.class, args);
    }
}
