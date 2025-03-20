package com.hmall.cart.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Lord_Bao
 * @Date 2025/3/20 20:28
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties("hm.cart")
public class CartProperties {

    private int maxItems;
}
