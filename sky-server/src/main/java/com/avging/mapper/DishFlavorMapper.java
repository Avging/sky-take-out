package com.avging.mapper;

import com.avging.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {


    /**
     * 批量插入口味数据
     * @param flavors DishFlavor
     */
    void insertBatch(List<DishFlavor> flavors);
}
