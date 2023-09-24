package com.avging.mapper;

import com.avging.annotation.AutoFill;
import com.avging.dto.DishPageQueryDTO;
import com.avging.entity.Dish;
import com.avging.enumeration.OperationType;
import com.avging.vo.DishVO;
import com.github.pagehelper.Page;
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

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO DishPageQueryDTO
     * @return DishVO
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);
}