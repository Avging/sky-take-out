package com.avging.service.impl;

import com.avging.constant.MessageConstant;
import com.avging.constant.StatusConstant;
import com.avging.dto.DishDTO;
import com.avging.dto.DishPageQueryDTO;
import com.avging.entity.Dish;
import com.avging.entity.DishFlavor;
import com.avging.exception.DeletionNotAllowedException;
import com.avging.mapper.DishFlavorMapper;
import com.avging.mapper.DishMapper;
import com.avging.mapper.SetmealDishMapper;
import com.avging.result.PageResult;
import com.avging.service.DishService;
import com.avging.vo.DishVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 新增菜品和对应口味
     * @param dishDTO DishDTO
     */
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {

        Dish dish = new Dish();

        BeanUtils.copyProperties(dishDTO,dish);

        //向菜品表插入一条数据
        dishMapper.insert(dish);


        /**
         * 这里的id是 insert语句 执行完后返回的主键值，
         * 因为dish菜品的id是自增的，插入不需要输入，所以需要拿到dish id 的返回值，赋值给口味表的菜品id
         */
        Long dishId = dish.getId();

        //将口味信息存储到集合中
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            //遍历集合，给几个中的每个口味信息添加菜品id
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }

    }


    /**
     * 菜品分页查询
     * @param dishPageQueryDTO  dishPageQueryDTO
     * @return PageResult
     */
    public PageResult pageQuerry(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
    * 菜品
     * @param ids List<Long>
     *            开启事务，保证同成功同失败
     */
    @Transactional
    public void deleteBatch(List<Long> ids) {
        //判断当前菜品是否能够删除---是否存在启售中的菜品
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if(dish.getStatus() == StatusConstant.ENABLE){
                //当前菜品处于启售中，不能删除
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }


        //判断当前菜品是否能够删除---是否被套餐关联了
        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if(setmealIds != null && setmealIds.size() > 0){
            //当前菜品被套餐关联了，不能删除
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }


        //删除菜品表中的菜品数据
        for (Long id : ids) {
            dishMapper.deleteById(id);
            //删除菜品关联的口味数据
            dishFlavorMapper.deleteByDishId(id);
        }


    }
}
