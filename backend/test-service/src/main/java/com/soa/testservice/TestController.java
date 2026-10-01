package com.soa.testservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test/hello")
    public String hello() {
        return "Gateway -> Eureka -> Test Service is working!";
    }
}
