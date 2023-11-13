package com.avging.mapper;

import com.avging.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param orders Orders
     */
    void insert(Orders orders);
}
