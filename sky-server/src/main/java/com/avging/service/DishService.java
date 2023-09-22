package com.avging.service;

import com.avging.dto.DishDTO;

public interface DishService {

    /**
     * 新增菜品和对应口味
     * @param dishDTO DishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
