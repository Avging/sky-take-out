package com.avging.mapper;

import com.avging.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {


    /**
     * 批量插入口味数据
     * @param flavors DishFlavor
     */
    void insertBatch(List<DishFlavor> flavors);


    /**
     * 根据菜品id删除对应的口味数据
     * @param dishId Long
     */
    @Delete("DELETE FROM dish_flavor WHERE dish_id = #{dishId}")
    void deleteByDishId(Long dishId);
}
