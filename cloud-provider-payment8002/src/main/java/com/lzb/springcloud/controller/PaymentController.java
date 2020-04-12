package com.lzb.springcloud.controller;

import com.lzb.springcloud.entities.CommonResult;
import com.lzb.springcloud.entities.Payment;
import com.lzb.springcloud.impl.PaymentServiceImpl;
import com.lzb.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult result(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("-----"+result);
        if (result>0){
            return new CommonResult(200,"插入数据成功 端口号为"+serverPort,result);
        }else {
            return new CommonResult(404,"插入数据失败",null);
        }
    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult select(@PathVariable("id")Long id){
        Payment payment=paymentService.getPaymentById(id);
        log.info("-----"+payment);
        if (payment!=null){
            return new CommonResult(200,"查询数据成功 端口号为"+serverPort,payment);
        }else {
            return new CommonResult(404,"查询数据"+id+"失败",null);
        }
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }


}
