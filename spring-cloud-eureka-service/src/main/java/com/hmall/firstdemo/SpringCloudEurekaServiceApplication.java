package com.hmall.firstdemo;

import ch.qos.logback.classic.net.SyslogAppender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaServiceApplication.class, args);
    }

    public void get(){
            System.out.println("111111");
    }

}

