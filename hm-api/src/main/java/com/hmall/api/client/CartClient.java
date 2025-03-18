package com.hmall.api.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2025/3/10 11:08
 * @Version 1.0
 */
@FeignClient(name = "cart-service",path = "/carts")
public interface CartClient {

    @DeleteMapping
    public void deleteCartItemByIds(@RequestParam("ids") List<Long> ids);
}
