package com.clj.spring.service;

import com.clj.spring.model.Msg;
import com.clj.spring.model.TbItem;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "spring-item")
public interface ItemService {

    @PostMapping("item/getitem")
    Msg selectAll(@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(name = "pageSize",defaultValue = "1") Integer pageSize);

    @PostMapping("item/selectById")
    Msg selectById(@RequestParam("id") Long id);

    @PostMapping("item/update")
    Msg update(TbItem tbItem);

    @PostMapping("item/deleteAll")
    void deleteAll();
}
