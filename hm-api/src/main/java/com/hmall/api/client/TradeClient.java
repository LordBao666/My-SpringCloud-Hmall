package com.hmall.api.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Lord_Bao
 * @Date 2025/3/10 11:08
 * @Version 1.0
 */
@FeignClient("trade-service")
@RequestMapping("/orders")
public interface TradeClient {

    @PutMapping("/{orderId}")
    public void markOrderPaySuccess(@PathVariable("orderId") Long orderId) ;

}
