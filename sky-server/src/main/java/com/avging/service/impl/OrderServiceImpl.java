package com.avging.service.impl;

import com.avging.constant.MessageConstant;
import com.avging.context.BaseContext;
import com.avging.dto.OrdersSubmitDTO;
import com.avging.entity.AddressBook;
import com.avging.entity.OrderDetail;
import com.avging.entity.Orders;
import com.avging.entity.ShoppingCart;
import com.avging.exception.AccountLockedException;
import com.avging.exception.ShoppingCartBusinessException;
import com.avging.mapper.AddressBookMapper;
import com.avging.mapper.OrderDetailMapper;
import com.avging.mapper.OrderMapper;
import com.avging.mapper.ShoppingCartMapper;
import com.avging.service.AddressBookService;
import com.avging.service.OrderService;
import com.avging.vo.OrderSubmitVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private AddressBookMapper addressBookMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 用户下单
     * @param ordersSubmitDTO OrdersSubmitDTO
     * @return OrderSubmitVO
     */
    @Transactional
    public OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO) {

        //1. 处理各种业务异常（地址簿为空，购物车数据为空）
        AddressBook addressBook = addressBookMapper.getById(ordersSubmitDTO.getAddressBookId());
        if (addressBook == null){
            //抛出业务异常
            throw new AccountLockedException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }

        //查询当前用户的购物车数据
        Long userId = BaseContext.getCurrentId();

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(userId);
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);

        if (shoppingCartList == null || shoppingCartList.size() == 0){
            //抛出业务异常
            throw  new ShoppingCartBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
        }

        //2. 向订单表插入1条数据
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersSubmitDTO,orders);
        orders.setOrderTime(LocalDateTime.now());
        orders.setPayStatus(Orders.UN_PAID);
        orders.setStatus(Orders.PENDING_PAYMENT);
        //当前时间的时间戳当作订单号
        orders.setNumber(String.valueOf(System.currentTimeMillis()));
        orders.setPhone(addressBook.getPhone());
        orders.setConsignee(addressBook.getConsignee());
        orders.setUserId(userId);

        orderMapper.insert(orders);//在SQL标签上设置了返回ordersId给orders对象

        List<OrderDetail> orderDetailList = new ArrayList<>();
        //3. 向订单明细表插入n条数据
        for (ShoppingCart cart : shoppingCartList){
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart,orderDetail);
            orderDetail.setOrderId(orders.getId());//设置当前订单明细关联的订单Id
            orderDetailList.add(orderDetail);
        }
        orderDetailMapper.insertBatch(orderDetailList);

        //4. 清空当前用户的购物车数据
        shoppingCartMapper.deleteByUserId(userId);

        //5. 封装VO返回结果
        OrderSubmitVO submitVO = OrderSubmitVO.builder()
                .id(orders.getId())
                .orderTime(orders.getOrderTime())
                .orderNumber(orders.getNumber())
                .orderAmount(orders.getAmount())
                .build();

        return new OrderSubmitVO();
    }
}
