package com.hmall.trade;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Lord_Bao
 * @Date 2025/3/8 10:03
 * @Version 1.0
 */
@MapperScan("com.hmall.trade.mapper")
@SpringBootApplication
@EnableFeignClients(value = "com.hmall.api.client")
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}