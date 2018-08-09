package com.clj.spring.service;

import com.clj.spring.model.Msg;
import com.clj.spring.model.TbUser;
import com.clj.spring.service.hystrix.UserServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "spring-user",fallback = UserServiceHystrix.class)
public interface UserService {


    Integer selectByName(@RequestParam("name") String username);

    @GetMapping("user/insert")
    Msg insert(@RequestBody TbUser tbUser);

}
