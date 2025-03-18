package com.hmall.api.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @Author Lord_Bao
 * @Date 2025/3/10 11:08
 * @Version 1.0
 */

@FeignClient(name = "trade-service",path = "/orders")
public interface TradeClient {

    @PutMapping("/{orderId}")
    public void markOrderPaySuccess(@PathVariable("orderId") Long orderId) ;

}
