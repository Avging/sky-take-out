package com.avging.mapper;

import com.avging.annotation.AutoFill;
import com.avging.entity.Dish;
import com.avging.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId Long
     * @return Integer
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入菜品数据
     * @param dish Dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);
}