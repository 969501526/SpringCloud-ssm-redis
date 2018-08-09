package com.clj.spring.service.hystrix;

import com.clj.spring.model.Msg;
import com.clj.spring.model.TbUser;
import com.clj.spring.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserServiceHystrix implements UserService {

    @Override
    public Integer selectByName(String username) {
        System.out.println(1);
        return null;
    }

    @Override
    public Msg insert(TbUser tbUser) {
        System.out.println(2);
        return null;
    }
}
