package com.hmall.api.client;


import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2025/3/9 8:23
 * @Version 1.0
 */
@FeignClient("item-service")
@RequestMapping("items")
public interface ItemClient {

    @GetMapping
    public List<ItemDTO> queryItemByIds(@RequestParam("ids") List<Long> ids);

    @PutMapping("/stock/deduct")
    public void deductStock(@RequestBody List<OrderDetailDTO> items);
}
