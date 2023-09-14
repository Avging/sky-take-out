package com.avging.service;

import com.avging.dto.CategoryDTO;
import com.avging.dto.CategoryPageQueryDTO;
import com.avging.entity.Category;
import com.avging.result.PageResult;
import java.util.List;

public interface CategoryService {

    /**
     * 新增分类
     * @param categoryDTO CategoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分页查询
     * @param categoryPageQueryDTO CategoryPageQueryDTO
     * @return PageResult
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id删除分类
     * @param id Long
     */
    void deleteById(Long id);

    /**
     * 修改分类
     * @param categoryDTO CategoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 启用、禁用分类
     * @param status Integer
     * @param id Long
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据类型查询分类
     * @param type Integer
     * @return List<Category>
     */
    List<Category> list(Integer type);
}