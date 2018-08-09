package com.clj.spring.mapper;

import com.clj.spring.model.TbItem;
import com.clj.spring.model.TbItemExample;
import java.util.List;

public interface TbItemMapper {


    List<TbItem> selectAll();

    TbItem selectById(Long id);

    Integer update(TbItem tbItem);

    void deleteAll();
}