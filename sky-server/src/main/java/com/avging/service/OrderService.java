package com.avging.service;

import com.avging.dto.OrdersSubmitDTO;
import com.avging.vo.OrderSubmitVO;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    /**
     * 用户下单
     * @param ordersSubmitDTO OrdersSubmitDTO
     * @return OrderSubmitVO
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);
}
