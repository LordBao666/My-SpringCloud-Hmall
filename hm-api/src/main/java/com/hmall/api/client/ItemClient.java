package com.hmall.api.client;


import com.hmall.api.dto.ItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2025/3/9 8:23
 * @Version 1.0
 */
@FeignClient("item-service")
public interface ItemClient {

    @GetMapping("items")
    public List<ItemDTO> queryItemByIds(@RequestParam("ids") List<Long> ids);
}
