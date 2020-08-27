package com.ponomarev.service;

import com.ponomarev.web.dto.CreateOrderDto;
import com.ponomarev.web.dto.OrderDto;
import java.util.List;

public interface OrderService {

  Long createOrder(CreateOrderDto createOrderDto);

  OrderDto getOrder(Long id);

  List<OrderDto> getOrderList();

  boolean removeOrder(Long id);
}
