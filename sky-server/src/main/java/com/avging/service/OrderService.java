package com.avging.service;

import com.avging.dto.OrdersPaymentDTO;
import com.avging.dto.OrdersSubmitDTO;
import com.avging.vo.OrderPaymentVO;
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

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);
}
