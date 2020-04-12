package com.lzb.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderConsulController {
    @Resource
    private RestTemplate restTemplate;

    private static final String INVOME_URL = "http://consul-provider-payment";
    @GetMapping("/consumer/payment/consul")
    public String payment(){
        return restTemplate.getForObject(INVOME_URL+"/payment/consul", String.class);
    }
}
