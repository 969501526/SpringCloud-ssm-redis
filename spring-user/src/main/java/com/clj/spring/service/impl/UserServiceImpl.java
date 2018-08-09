package com.clj.spring.service.impl;

import com.clj.spring.mapper.TbUserMapper;
import com.clj.spring.model.Msg;
import com.clj.spring.model.TbUser;
import com.clj.spring.service.UserService;
import com.clj.spring.util.Check;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="API - User",description = "用户管理")
@RefreshScope
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public Integer selectByName(String username) {
        return tbUserMapper.selectByName(username);
    }

    @Override
    @ApiOperation("添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "username",required = true, dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "password",required = true, dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "email", value = "email",required = true, dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "phone",required = true, dataType = "string",paramType = "query"),
    })
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "Successful — 请求已完成"),
                    @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
                    @ApiResponse(code = 401, message = "未授权客户机访问数据"),
                    @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
                    @ApiResponse(code = 500, message = "服务器不能完成请求")
            }
    )
    public Msg insert(TbUser tbUser) {
        Integer count= tbUserMapper.selectByName(tbUser.getUsername());
        if(count>0){
            return Msg.fail().add("error","张号已存在");
        }
        if(Check.userCheck(tbUser)==false){
            return Msg.fail().add("error","参数异常");
        }
        tbUserMapper.insert(tbUser);
        return  Msg.success().add("success","注册成功");
    }
}
