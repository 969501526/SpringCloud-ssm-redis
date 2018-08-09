package com.clj.spring.mapper;

import com.clj.spring.model.TbUser;

public interface TbUserMapper {

    Integer selectByName(String username);
    int insert(TbUser tbUser);


}