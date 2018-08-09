package com.clj.spring.service.impl;

import com.clj.spring.mapper.TbItemMapper;
import com.clj.spring.model.Msg;
import com.clj.spring.model.TbItem;
import com.clj.spring.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Api(value = "Item",description = "商品管理")
@CacheConfig(cacheNames="userCache")
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
@RestController
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    @ApiOperation("查询所有商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",defaultValue = "1",value = "pageNum",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",defaultValue = "1",value = "pageSize",required = true,dataType = "int",paramType = "query"),
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
    @Cacheable(key="#p0")
    public Msg selectAll(Integer pageNum,Integer pageSize) {
      Page<TbItem> page= PageHelper.startPage(pageNum, pageSize);
        tbItemMapper.selectAll();
    return Msg.success().add("success",page.toPageInfo());
    }

    @Override
    @ApiOperation("商品详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",value = "name",required = true,dataType = "int",paramType = "query")
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
    @Cacheable(key="#id")
    public Msg selectById(Long id) {
        return Msg.success().add("success",tbItemMapper.selectById(id));
    }

    @Override
    @ApiOperation("更新库存")
    @CachePut(key = "#tbItem.id")
    public Msg update(TbItem tbItem) {
        tbItemMapper.update(tbItem);
        return Msg.success().add("success",tbItemMapper.selectById(tbItem.getId()));
    }

    @Override
    @ApiOperation("清除缓存")
    @CacheEvict(allEntries = true)
    public void deleteAll() {

    }
}
