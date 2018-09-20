package com.avalon.saeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SaEureka2Application {

    public static void main(String[] args) {

        SpringApplication.run(SaEureka2Application.class, args);
    }
}
