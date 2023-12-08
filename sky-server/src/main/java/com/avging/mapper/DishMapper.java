package com.avging.mapper;

import com.avging.annotation.AutoFill;
import com.avging.dto.DishPageQueryDTO;
import com.avging.entity.Dish;
import com.avging.enumeration.OperationType;
import com.avging.vo.DishVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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


    /**
     * 根据主键查询菜品
     * @param id Long
     */
    @Select("SELECT * FROM dish WHERE id = #{id}")
    Dish getById(Long id);


    /**
     * 根据主键删除菜品数据
     * @param id Long
     */
    @Delete("DELETE FROM dish WHERE id = #{id}")
    void deleteById(Long id);


    /**
     * 根据菜品id集合批量删除菜品
     * @param ids Long
     */
    void deleteByIds(List<Long> ids);


    /**
     * 根据id动态修改菜品数据
     * @param dish Dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);


    /**
     * 根据套餐id查询菜品
     * @param setmealId
     * @return
     */
    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long setmealId);


    /**
     * 动态条件查询菜品       
     * @param dish Dish
     * @return List<Dish>
     */
    List<Dish> list(Dish dish);


    /**
     * 根据条件统计菜品数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}