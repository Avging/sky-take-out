package com.avging.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询对应的套餐id
     * @param dishIds Long
     * @return Long
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
}
