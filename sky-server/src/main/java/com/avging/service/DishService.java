package com.avging.service;

import com.avging.dto.DishDTO;
import com.avging.dto.DishPageQueryDTO;
import com.avging.entity.Dish;
import com.avging.result.PageResult;
import com.avging.vo.DishVO;

import java.util.List;

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

    /**
     * 菜品
     * @param ids List<Long>
     */
    void deleteBatch(List<Long> ids);


    /**
     * 根据id查询菜品和对应的口味数据
     * @param id Long
     * @return DishVo
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 根据id修改菜品基本信息和对应的口味信息
     * @param dishDTO DishDTO
     */
    void updateWithFlovor(DishDTO dishDTO);

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
