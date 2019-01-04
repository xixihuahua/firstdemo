package com.hmall.firstdemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-provider")
public interface HomeClient {

    @GetMapping("/")
    String consumer();

}
