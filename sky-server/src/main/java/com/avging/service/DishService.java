package com.avging.service;

import com.avging.dto.DishDTO;
import com.avging.dto.DishPageQueryDTO;
import com.avging.result.PageResult;

public interface DishService {

    /**
     * 新增菜品和对应口味
     * @param dishDTO DishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO  dishPageQueryDTO
     * @return PageResult
     */
    PageResult pageQuerry(DishPageQueryDTO dishPageQueryDTO);
}
